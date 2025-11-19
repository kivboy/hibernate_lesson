package by.vadarod.javaee.repository;

import by.vadarod.javaee.config.HibernateSessionFactoryUtil;
import by.vadarod.javaee.entity.Activity;
import by.vadarod.javaee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public Map<String, BigDecimal> getRoomsPricePerClient(Long activityId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT r.name,r.pricePerHour / r.capacity AS price_per_client FROM Activity a JOIN a.roomList r WHERE a.id=:activityId ORDER BY r.name", Object[].class);
        query.setParameter("activityId", activityId);
        @SuppressWarnings("unchecked")
        List<Objects[]> prices= (List<Objects[]>)query.getResultList();
        Map<String, BigDecimal> pricesMap = new HashMap<>();

        for (Object[] row : prices) {
           pricesMap.put((String) row[0],
                   new BigDecimal(String.valueOf(row[1])).setScale(2, RoundingMode.UP));
        }

        session.close();
        return pricesMap;
    }

    @Override
    public Activity getMinPriceActivity() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Activity> criteriaQuery = criteriaBuilder.createQuery(Activity.class);
        Root<Activity> activityRoot = criteriaQuery.from(Activity.class);
        criteriaQuery.select(activityRoot).orderBy(criteriaBuilder.asc(activityRoot.get("price")));

        TypedQuery<Activity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setMaxResults(1);
        List<Activity> activityList = typedQuery.getResultList();

        if (!activityList.isEmpty()) {
            return activityList.getFirst();
        } else {
            return null;
        }
    }

    @Override
    public Activity findL1CachedActivityById(Long activityId, boolean isCache) {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT a FROM Activity a", Activity.class);
        @SuppressWarnings("unchecked")
        List<Activity> activityList = (List<Activity>)query.getResultList();

        query = session.createQuery("SELECT e FROM Employee e", Employee.class);
        // additional query without sence
        @SuppressWarnings("unchecked")
        List<Employee> Employee = (List<Employee>)query.getResultList();

        if (!isCache) {
            session.clear();
        }

        Activity activity = session.get(Activity.class, activityId);

        session.close();
        return activity;
    }
}
