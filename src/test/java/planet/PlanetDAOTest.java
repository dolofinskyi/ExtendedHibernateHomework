package planet;

import db.HibernateService;
import db.MigrationService;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PlanetDAOTest {
    private static Session session;
    private static PlanetDAO dao;

    @BeforeAll
    public static void beforeAll() {
        MigrationService.migrate();
        session = HibernateService.INSTANCE.getSessionFactory().openSession();
        dao = new PlanetDAO();
    }

    @Test
    void testGetPlanet() {
        Planet last = dao.getLast(session);
        Planet actual = dao.get(session, last.getId());
        assertEquals(last.getId(), actual.getId());
    }

    @Test
    void testAddPlanet() {
        Planet planet = new Planet();
        planet.setId("PLA0");
        planet.setName("Planet");
        int size = dao.size(session);
        dao.add(session, planet);
        assertEquals(size + 1, dao.size(session));
    }

    @Test
    void testUpdatePlanet() {
        Planet last = dao.getLast(session);
        last.setName("Whatever");
        dao.update(session, last);
        assertEquals(last.getName(), dao.getLast(session).getName());
    }

    @Test
    void testDeletePlanet() {
        Planet last = dao.getLast(session);
        dao.delete(session, last);
        assertNotEquals(last.getId(), dao.getLast(session).getId());
    }

    @AfterAll
    public static void afterAll() {
        session.close();
        HibernateService.INSTANCE.close();
    }
}