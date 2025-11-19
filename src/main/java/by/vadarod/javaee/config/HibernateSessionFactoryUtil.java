package by.vadarod.javaee.config;

import by.vadarod.javaee.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // JDBC настройки
                configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
                configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/sportclub");
                configuration.setProperty("hibernate.connection.username", "postgres");
                configuration.setProperty("hibernate.connection.password", "admin");

                // Кэш
                configuration.setProperty("hibernate.cache.use_second_level_cache", "true");
                configuration.setProperty("hibernate.cache.use_query_cache", "true");
                configuration.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");
                configuration.setProperty("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
                configuration.setProperty("hibernate.javax.cache.uri", "ehcache.xml");

                // Optional SQL parameters
                configuration.setProperty("hibernate.show_sql", "true");
                configuration.setProperty("hibernate.format_sql", "true");
                configuration.setProperty("hibernate.hbm2ddl.auto", "create");

                configuration.setProperty("hibernate.connection.isolation", String.valueOf(Connection.TRANSACTION_READ_COMMITTED));
                //configuration.setProperty("hibernate.generate_statistics", "true");

                // Отображение сущностей
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Activity.class);
                configuration.addAnnotatedClass(Room.class);
                configuration.addAnnotatedClass(RoomUnder15.class);
                configuration.addAnnotatedClass(Visit.class);
                configuration.addAnnotatedClass(Reservation.class);

                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                System.out.println("Ошибка подключения конфигурации Hibernate: " + e);
            }
        }
        return sessionFactory;
    }
}
