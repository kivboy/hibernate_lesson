package by.vadarod.javaee.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateConnection {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("HibernateSportClubJPA");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
