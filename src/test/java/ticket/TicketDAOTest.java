package ticket;

import db.HibernateService;
import db.MigrationService;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import planet.PlanetDAO;
import client.ClientDAO;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TicketDAOTest {
    private static Session session;

    private static TicketDAO tickedDAO;
    private static ClientDAO clientDAO;
    private static PlanetDAO planetDAO;

    @BeforeAll
    public static void beforeAll() {
        MigrationService.migrate();
        session = HibernateService.INSTANCE.getSessionFactory().openSession();
        tickedDAO = new TicketDAO();
        clientDAO = new ClientDAO();
        planetDAO = new PlanetDAO();
    }

    @Test
    void testGetTicket() {
        Ticket last = tickedDAO.getLast(session);
        Ticket actual = tickedDAO.get(session, last.getId());
        assertEquals(last.getId(), actual.getId());
    }

    @Test
    void testAddTicket() {
        Ticket ticket = new Ticket();
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setClient(clientDAO.get(session, 4L));
        ticket.setFromPlanet(planetDAO.get(session, "MARS0"));
        ticket.setToPlanet(planetDAO.get(session, "VEN0"));
        int size = tickedDAO.size(session);
        tickedDAO.add(session, ticket);
        assertEquals(size + 1, tickedDAO.size(session));
    }

    @Test
    void testUpdateTicket() {
        Ticket last = tickedDAO.getLast(session);
        last.setCreatedAt(LocalDateTime.now().minusMonths(10));
        tickedDAO.update(session, last);
        assertEquals(last.getCreatedAt(), tickedDAO.getLast(session).getCreatedAt());
    }

    @Test
    void testDeleteTicket() {
        Ticket last = tickedDAO.getLast(session);
        tickedDAO.delete(session, last);
        assertNotEquals(last.getId(), tickedDAO.getLast(session).getId());
    }

    @AfterAll
    public static void afterAll() {
        session.close();
        HibernateService.INSTANCE.close();
    }
}