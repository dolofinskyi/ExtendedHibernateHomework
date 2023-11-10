package client;

import db.HibernateService;
import db.MigrationService;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOTest {
    private static Session session;
    private static ClientDAO dao;

    @BeforeAll
    public static void beforeAll() {
        MigrationService.migrate();
        session = HibernateService.INSTANCE.getSessionFactory().openSession();
        dao = new ClientDAO();
    }

    @Test
    void testGetClient() {
        Client last = dao.getLast(session);
        Client actual = dao.get(session, last.getId());
        assertEquals(last.getId(), actual.getId());
    }

    @Test
    void testAddClient() {
        Client client = new Client();
        client.setName("Martin");
        int size = dao.size(session);
        dao.add(session, client);
        assertEquals(size + 1, dao.size(session));
    }

    @Test
    void testUpdateClient() {
        Client modified = dao.getLast(session);
        modified.setName("Bob");
        dao.update(session, modified);
        assertEquals(modified.getName(), dao.getLast(session).getName());
    }

    @Test
    void testDeleteClient() {
        // add a new client to avoid reference violation
        Client client = new Client();
        client.setName("Martin");
        dao.add(session, client);

        Client last = dao.getLast(session);
        dao.delete(session, last);
        assertNotEquals(client.getId(), dao.getLast(session).getId());
    }

    @AfterAll
    public static void afterAll() {
        session.close();
        HibernateService.INSTANCE.close();
    }
}