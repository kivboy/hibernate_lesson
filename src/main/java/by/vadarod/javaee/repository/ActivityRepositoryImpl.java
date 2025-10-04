package by.vadarod.javaee.repository;

import by.vadarod.javaee.config.HibernateSessionFactoryUtil;
import by.vadarod.javaee.entity.Activity;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ActivityRepositoryImpl implements ActivityRepository{

    private final SessionFactory sessionFactory;

    public ActivityRepositoryImpl() {
        this.sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Long addActivity(Activity activity) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(activity);
        session.getTransaction().commit();
        session.close();

        return activity.getId();
    }

    @Override
    public List<Activity> getAll() {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select a from Activity a", Activity.class);
        @SuppressWarnings("unchecked")
        List<Activity> activityList = (List<Activity> )query.getResultList();
        session.close();

        return activityList;
    }

    @Override
    public Activity findActivityById(Long activityId) {
        Session session = sessionFactory.openSession();
        Activity activity = session.get(Activity.class, activityId);
        session.close();

        return activity;
    }
}
