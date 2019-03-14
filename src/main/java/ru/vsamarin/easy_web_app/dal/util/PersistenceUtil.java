package ru.vsamarin.easy_web_app.dal.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PersistenceUtil implements ServletContextListener {

    private static final String NAUMEN_PERSISTENCE_UNIT = "application-persistence-unit";

    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        emf = Persistence.createEntityManagerFactory(NAUMEN_PERSISTENCE_UNIT);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory did not initialized.");
        }
        return emf.createEntityManager();
    }

}

