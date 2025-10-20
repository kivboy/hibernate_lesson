package by.vadarod.javaee.repository;

import by.vadarod.javaee.config.HibernateSessionFactoryUtil;
import by.vadarod.javaee.entity.Visit;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class VisitRepositoryImpl implements VisitRepository{

    private final SessionFactory sessionFactory;

    public VisitRepositoryImpl() {
        this.sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Long addVisit(Visit visit) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(visit);
        session.getTransaction().commit();
        session.close();

        return visit.getId();
    }

    @Override
    public List<Visit> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select v from Visit v", Visit.class);
        @SuppressWarnings("unchecked")
        List<Visit> visitList = (List<Visit>)query.getResultList();
        session.close();

        return visitList;
    }
}
