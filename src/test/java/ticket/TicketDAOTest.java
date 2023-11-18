package ticket;

import client.Client;
import dao.ServiceDAO;
import db.HibernateService;
import db.MigrationService;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import planet.Planet;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TicketDAOTest {
    private static Session session;

    private static ServiceDAO<Ticket> tickedDAO;
    private static ServiceDAO<Client> clientDAO;
    private static ServiceDAO<Planet> planetDAO;

    @BeforeAll
    public static void beforeAll() {
        MigrationService.migrate();
        session = HibernateService.INSTANCE.getSessionFactory().openSession();
        tickedDAO = new ServiceDAO<>(Ticket.class);
        clientDAO = new ServiceDAO<>(Client.class);
        planetDAO = new ServiceDAO<>(Planet.class);
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