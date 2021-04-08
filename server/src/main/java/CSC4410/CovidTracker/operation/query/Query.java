package CSC4410.CovidTracker.operation.query;

import CSC4410.CovidTracker.util.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Any class which represents a query to the database.
 */
public abstract class Query {

    private PreparedStatement stmt;

    /**
     * @return The prepared SQL statement. If it does not yet exist, it will be
     * created with the Database's default connection.
     * @throws SQLException
     */
    protected PreparedStatement getStatement() throws SQLException {
        if (stmt == null) {
            stmt = Database.getConnection().prepareStatement(getStatementBody());
        }

        return stmt;
    }

    public Query() {}

    /**
     * Execute the query.
     * @throws SQLException
     */
    public abstract void execute() throws SQLException;

    /**
     * @return The SQL statement we are executing.
     */
    protected abstract String getStatementBody();

    /**
     * Begin a transaction. No changes will be saved to the database until
     * commit() is called.
     * @throws SQLException
     */
    public void begin() throws SQLException {
        Database.getConnection().setAutoCommit(false);
    }

    /**
     * Commit the changes to the database and end the transaction.
     * @throws SQLException
     */
    public void commit() throws SQLException {
        var conn = Database.getConnection();
        conn.commit();
        conn.setAutoCommit(true);
    }

    /**
     * Undo the changes to the database and end the transaction.
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        var conn = Database.getConnection();
        conn.rollback();
        conn.setAutoCommit(true);
    }

}
