package nl.han.oose.persistence;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionFactory {

    private Properties properties;

    Connection getConnection() {
        if (properties == null) {
            properties = readProperties();
        }
        try {
            Class.forName(properties.getProperty("db.driver"));
            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistenceException(e);
        }
    }

    private Properties readProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/database.properties"));
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
        return properties;
    }


}
