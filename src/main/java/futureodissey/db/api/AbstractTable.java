package futureodissey.db.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractTable {
    protected final String tableName;
    protected final Connection connection;

    public AbstractTable(final String name, final Connection connection) {
        tableName = name;
        this.connection = connection;
    }

    public boolean createTable() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.executeUpdate(
                "CREATE TABLE " + tableName + " (" +
                        "id INT NOT NULL PRIMARY KEY," +
                        "firstName CHAR(40)," + 
                        "lastName CHAR(40)," + 
                        "birthday DATE" + 
                    ")");
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    public boolean dropTable() {
        try (final Statement statement = this.connection.createStatement()) {
            statement.executeUpdate("DROP TABLE " + tableName);
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }
}
