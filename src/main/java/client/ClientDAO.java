package client;

import dao.ServiceDAO;

public class ClientDAO extends ServiceDAO<Client> {
    public ClientDAO() {
        super(Client.class);
    }
}
