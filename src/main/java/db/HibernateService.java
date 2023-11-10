package db;

import client.Client;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import planet.Planet;
import ticket.Ticket;

public enum HibernateService {
    INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    HibernateService() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    public void close() {
        sessionFactory.close();
    }
}
