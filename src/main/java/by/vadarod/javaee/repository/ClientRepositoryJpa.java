package by.vadarod.javaee.repository;

import by.vadarod.javaee.config.HibernateConnection;
import by.vadarod.javaee.entity.Client;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClientRepositoryJpa implements ClientRepository {

    public void addClient(Client client) {
        EntityManager entityManager = HibernateConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Client> getAll() {
        EntityManager entityManager = HibernateConnection.getEntityManager();
        @SuppressWarnings("unchecked")
        List<Client> clients = (List<Client>)entityManager.createQuery("select c from Client c").getResultList();
        entityManager.close();
        return clients;
    }

    @Override
    public void deleteClient(Client client) {
        EntityManager entityManager = HibernateConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Client deleteClient = entityManager.find(Client.class, client.getId());
        entityManager.remove(deleteClient);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Client findClientById(Long clientId) {
        EntityManager entityManager = HibernateConnection.getEntityManager();
        Client client = entityManager.find(Client.class, clientId);
        entityManager.close();
        return client;
    }

    @Override
    public void changeClient(Client client) {
        EntityManager entityManager = HibernateConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Client changeClient = entityManager.find(Client.class, client.getId());
        entityManager.persist(changeClient);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
