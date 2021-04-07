package CSC4410.CovidTracker.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Any class which represents a query to the database.
 */
public abstract class Query {

    private PreparedStatement stmt;

    protected PreparedStatement getStatement() throws SQLException {
        if (stmt == null) {
            stmt = Database.getConnection().prepareStatement(getStatementBody());
        }

        return stmt;
    }

    public Query() {}

    public abstract void execute() throws SQLException;
    protected abstract String getStatementBody();

    public void begin() throws SQLException {
        Database.getConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        var conn = Database.getConnection();
        conn.commit();
        conn.setAutoCommit(true);
    }

    public void rollback() throws SQLException {
        var conn = Database.getConnection();
        conn.rollback();
        conn.setAutoCommit(true);
    }

}
