package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import futureodissey.db.api.AbstractTable;
import futureodissey.db.api.Table;
import futureodissey.model.impl.rowtype.Task;
import javafx.util.Pair;

public class TaskTable extends AbstractTable<Task> implements Table<Task, Pair<String, Integer>> {
    private final String key1 = "nomeFazione";
    private final String key2 = "codiceTask";
    private final String taskType = "codiceTaskType";
    private final String insediamento1 = "nomeInsediamento1";
    private final String insediamento2 = "nomeInsediamento2";
    private final String pianeta = "nomePianeta";

    public TaskTable(Connection connection) {
        super("task", connection);
    }

    @Override
    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key1 + " CHAR(40) NOT NULL," +
            key2 + " INT NOT NULL," +
            taskType + " INT NOT NULL," +
            insediamento1 + " char(40)," +
            insediamento2 + " char(40)," +
            pianeta + " char(40)," +
            "PRIMARY KEY (" + key1 + ", " + key2 +
            "))");
    }

    @Override
    public Optional<Task> findByPrimaryKey(Pair<String, Integer> primaryKey) {
        return findByPrimaryKeyPrivate(" WHERE " + key1 + " = ? AND " + key2 + " = ?", st -> {
            try {
                st.setString(1, primaryKey.getKey());
                st.setInt(2, primaryKey.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean save(Task task) {
        return savePrivate(task, " VALUES(?, ?, ?, ?, ?, ?)", st -> {
            try {
                st.setString(1, task.getNomeFazione());
                st.setInt(2, task.getCodiceTask());
                st.setInt(3, task.getCodiceTaskType());
                st.setString(4, task.getNomeInsediamento1().isEmpty() ? null : task.getNomeInsediamento1().get());
                st.setString(5, task.getNomeInsediamento2().isEmpty() ? null : task.getNomeInsediamento2().get());
                st.setString(6, task.getNomePianeta().isEmpty() ? null : task.getNomePianeta().get());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean delete(Pair<String, Integer> primaryKey) {
        return deletePrivate(" WHERE " + key1 + " = ? AND " + key2 + " = ?", st -> {
            try {
                st.setString(1, primaryKey.getKey());
                st.setInt(2, primaryKey.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean update(Task task) {
        return updatePrivate(
            task, " SET " + taskType + " = ?, " + insediamento1 + " = ?, " + insediamento2 + " = ?, " +
            pianeta + " = ? WHERE " + key1 + " = ? AND " + key2 + " = ?",
            st -> {
            try {
                st.setInt(1, task.getCodiceTaskType());
                st.setString(2, task.getNomeInsediamento1().isEmpty() ? null : task.getNomeInsediamento1().get());
                st.setString(3, task.getNomePianeta().isEmpty() ? null : task.getNomePianeta().get());
                st.setString(4, task.getNomeFazione());
                st.setInt(5, task.getCodiceTask());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected List<Task> readStudentsFromResultSet(ResultSet resultSet) {
        List<Task> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new Task(
                    resultSet.getString(key1), 
                    resultSet.getInt(key2),
                    resultSet.getInt(taskType),
                    Optional.ofNullable(resultSet.getString(insediamento1)),
                    Optional.ofNullable(resultSet.getString(insediamento2)),
                    Optional.ofNullable(resultSet.getString(pianeta))));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }

    @Override
    public Task getRowSample() {
        return new Task("sample", 0, 0);
    }

    public int getNextCodice(final String nomeFazione) {
        final String query = "SELECT MAX(" + key2 + ") as "+ key2 + " FROM " + tableName +
            " WHERE " + this.key1 + " = \"" + nomeFazione + "\"";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            int toReturn = -1;
            while(result.next()) {
                toReturn = result.getString(key2) == null ? -1 : result.getInt(key2);
            }
            return toReturn + 1;
        } catch (final SQLException e) {
            return -5;
        }
    }

    public List<Task> getTaskFromNomeFazione(final String nomeFazione) {
        final String query = "SELECT  * FROM "+ tableName + " WHERE " + this.key1 + " = \"" + nomeFazione + "\"";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            return readStudentsFromResultSet(result);
        } catch (final SQLException e) {
            return new ArrayList<>();
        }
    }

    public List<Task> getTaskSorted() {
        final String query = "SELECT  * FROM "+ tableName + " ORDER BY 2";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            return readStudentsFromResultSet(result);
        } catch (final SQLException e) {
            return new ArrayList<>();
        }
    }
    
}
