package by.vadarod.javaee.repository;

import by.vadarod.javaee.config.HibernateSessionFactoryUtil;
import by.vadarod.javaee.entity.Client;
import by.vadarod.javaee.entity.PremiumClient;
import jakarta.persistence.Query;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {

    private final SessionFactory sessionFactory;

    public ClientRepositoryImpl() {
        this.sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Long addClient(Client client) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(client);
        session.getTransaction().commit();
        session.close();

        return client.getId();
    }

    @Override
    public List<Client> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select c from Client c", Client.class);
        @SuppressWarnings("unchecked")
        List<Client> clients = (List<Client>)query.getResultList();
        session.close();

        return clients;
    }

    @Override
    public void deleteClientById(Long clientId) {

        Session session = sessionFactory.openSession();
        Client clientDb = session.get(Client.class, clientId);
        if (clientDb != null) {
            session.beginTransaction();
            session.remove(clientDb);
            session.getTransaction().commit();
        }
        session.close();
    }

    @Override
    public Client findClientById(Long clientId) {
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class, clientId);
        session.close();

        return client;
    }

    @Override
    public void changeClient(@NonNull Client client) {

        Session session = sessionFactory.openSession();
        Client clientDb = session.get(Client.class, client.getId());
        if (clientDb != null) {
            session.beginTransaction();
            session.merge(client);
            session.getTransaction().commit();
        }
        session.close();
    }

    @Override
    public List<PremiumClient> findAllPremiumClients() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select p from PremiumClient p", PremiumClient.class);
        @SuppressWarnings("unchecked")
        List<PremiumClient> premiumClients = (List<PremiumClient>)query.getResultList();
        session.close();

        return premiumClients;
    }

    @Override
    public List<Client> findClientsByName(String lastName, String firstName) {
        Session session = sessionFactory.openSession();
        String queryParams = "";

        if ((lastName != null) && !lastName.isEmpty()) {
            queryParams = "c.lastName=:lastNameParam";
        }

        if ((firstName != null) && !firstName.isEmpty()) {
            if (queryParams.isEmpty()) {
                queryParams = "c.firstName=:firstNameParam";
            } else {
                queryParams = queryParams.concat(" AND c.firstName=:firstNameParam");
            }
        }

        Query query = session.createQuery("SELECT c FROM Client c WHERE ".concat(queryParams), Client.class);

        if ((lastName != null) && !lastName.isEmpty()) {
            query.setParameter("lastNameParam", lastName);
        }

        if ((firstName != null) && !firstName.isEmpty()) {
            query.setParameter("firstNameParam", firstName);
        }

        @SuppressWarnings("unchecked")
        List<Client> clients = (List<Client>)query.getResultList();
        session.close();

        return clients;
    }
}
