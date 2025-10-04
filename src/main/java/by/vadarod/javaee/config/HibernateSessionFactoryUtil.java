package by.vadarod.javaee.config;

import by.vadarod.javaee.entity.Activity;
import by.vadarod.javaee.entity.Client;
import by.vadarod.javaee.entity.Room;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Activity.class);
                configuration.addAnnotatedClass(Room.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Ошибка подключения конфигурации Hibernate: " + e);
            }
        }
        return sessionFactory;
    }
}
