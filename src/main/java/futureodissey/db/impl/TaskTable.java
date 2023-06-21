package futureodissey.db.impl;

import java.sql.Connection;
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
    private final String insediamento = "nomeInsediamento";

    public TaskTable(Connection connection) {
        super("task", connection);
    }

    @Override
    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key1 + " CHAR(40) NOT NULL," +
            key2 + " INT NOT NULL," +
            taskType + " INT NOT NULL," +
            insediamento + " char(40)," +
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
        return savePrivate(task, " VALUES(?, ?, ?, ?)", st -> {
            try {
                st.setString(1, task.getNomeFazione());
                st.setInt(2, task.getCodiceTask());
                st.setInt(3, task.getCodiceTaskType());
                st.setString(4, task.getNomeInsediamento().isEmpty() ? null : task.getNomeInsediamento().get());
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
            task, " SET " + taskType + " = ?, " + insediamento + " = ? WHERE " + key1 + " = ? AND " + key2 + " = ?", st -> {
            try {
                st.setInt(1, task.getCodiceTaskType());
                st.setString(2, task.getNomeInsediamento().isEmpty() ? null : task.getNomeInsediamento().get());
                st.setString(3, task.getNomeFazione());
                st.setInt(4, task.getCodiceTask());
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
                    Optional.ofNullable(resultSet.getString(insediamento))));
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
    
}
