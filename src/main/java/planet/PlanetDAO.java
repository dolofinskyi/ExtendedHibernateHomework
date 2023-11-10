package planet;

import dao.ServiceDAO;

public class PlanetDAO extends ServiceDAO<Planet> {
    public PlanetDAO() {
        super(Planet.class);
    }
}
