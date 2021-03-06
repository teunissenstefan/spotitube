package dev.stefanteunissen.restserver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private final String url;
    private final String user;
    private final String password;
    private final String driverClassName;

    public DatabaseConnection() throws IOException {
        InputStream in = getClass().getResourceAsStream("/database.properties");
        Properties properties = new Properties();
        properties.load(in);
        in.close();
        this.url = properties.getProperty("jdbc.url");
        this.user = properties.getProperty("jdbc.username");
        this.password = properties.getProperty("jdbc.password");
        this.driverClassName = properties.getProperty("jdbc.driverClassName");
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(this.driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(this.url, this.user, this.password);
    }
}
