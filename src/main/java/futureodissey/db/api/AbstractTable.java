package futureodissey.db.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import futureodissey.model.api.rowtype.RowType;

public abstract class AbstractTable<T extends RowType> {
    protected final String tableName;
    protected final Connection connection;

    public AbstractTable(final String name, final Connection connection) {
        tableName = name;
        this.connection = connection;
    }

    public String getTableName() {
        return tableName;
    }

    protected boolean createTablePrivate(final String query) {
        try (final Statement statement = this.connection.createStatement()) {
            statement.executeUpdate(query);
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

    protected Optional<T> findByPrimaryKeyPrivate(final String finalQuery, final Consumer<PreparedStatement> consumer) {
        final String query = "SELECT * FROM " + tableName + finalQuery;
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            consumer.accept(statement);
            final ResultSet result = statement.executeQuery();
            return readStudentsFromResultSet(result).stream().findFirst();
        } catch(SQLException e) {
            throw new IllegalStateException();
        }
    }

    public List<T> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(
                "SELECT * FROM " + tableName);
            return readStudentsFromResultSet(result);
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }
    }

    protected boolean savePrivate(final T value, final String finalQuery, Consumer<PreparedStatement> consumer) {
        final String query = "INSERT INTO " + tableName + finalQuery;      
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            consumer.accept(statement);
            return statement.executeUpdate() > 0 ? true : false;
        } catch (final SQLException e) {
            return false;
        }
    }

    protected boolean deletePrivate(final String finalQuery, final Consumer<PreparedStatement> consumer) {
        final String query = 
            "DELETE FROM " + tableName + finalQuery;
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            consumer.accept(statement);
            return statement.executeUpdate() > 0 ? true : false;
        } catch (final SQLException e) {
            return false;
        }
    }

    protected boolean updatePrivate(final T value, final String finalQuery, Consumer<PreparedStatement> consumer) {
        final String query = 
            "UPDATE " + tableName + finalQuery;
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            consumer.accept(statement);
            return statement.executeUpdate() > 0 ? true : false;
        } catch (final SQLException e) {
            return false;
        }
    }

    protected abstract List<T> readStudentsFromResultSet(final ResultSet resultSet);
}
