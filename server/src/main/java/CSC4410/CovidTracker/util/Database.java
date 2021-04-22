package CSC4410.CovidTracker.util;

import CSC4410.CovidTracker.Config;
import CSC4410.CovidTracker.operation.query.CountyTableCreateQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Utility methods for working with the MySQL database.
 */
public class Database {

    private Database() {}

    private static Connection connection;

    /**
     * @return The default database connection. If it does not yet exist, it
     * will bve created.
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            initConnection();
        }

        return connection;
    }

    private static void initConnection() throws SQLException {
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

        // switch to our newly created (or already existing) database
        connection.setCatalog(Config.mysqlDatabase);
    }

    private static void ensureTableCreated() throws SQLException {
        // create county data table
        new CountyTableCreateQuery().execute();
    }

}
