package db;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.util.Properties;

public class MigrationService {

    static public void migrate() {
        Properties properties = readHibernateProperties();
        String url = properties.getProperty("hibernate.connection.url");
        Flyway flyway = Flyway
                .configure()
                .dataSource(url, null, null)
                .load();
        flyway.migrate();
    }

    static public Properties readHibernateProperties() {
        Properties properties = new Properties();
        try {
            properties.load(HibernateService.class
                    .getClassLoader()
                    .getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
