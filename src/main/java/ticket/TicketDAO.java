package ticket;

import dao.ServiceDAO;

public class TicketDAO extends ServiceDAO<Ticket> {
    public TicketDAO() {
        super(Ticket.class);
    }
}
