package CSC4410.CovidTracker.util;

import CSC4410.CovidTracker.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {

    private Database() {}

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            // create a new connection
            Properties connectionProperties = new Properties();
            connectionProperties.put("user", Config.mysqlUser);
            connectionProperties.put("password", Config.mysqlPassword);
            connection = DriverManager.getConnection("jdbc:mysql://"
                    + Config.mysqlHost + ":" + Config.mysqlPort
                    + "/", connectionProperties);

            // create the database if it does not yet exist
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE DATABASE IF NOT EXISTS " +  Config.mysqlDatabase);

            // use our newly created (or already existing) database
            connection.setCatalog(Config.mysqlDatabase);
        }

        return connection;
    }
}
