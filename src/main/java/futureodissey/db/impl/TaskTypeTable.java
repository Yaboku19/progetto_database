package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import futureodissey.db.api.AbstractTable;
import futureodissey.db.api.Table;
import futureodissey.model.impl.rowtype.TaskType;

public class TaskTypeTable extends AbstractTable<TaskType> implements Table<TaskType, Integer> {
    private final String key = "codiceTaskType";
    private final String descrizione = "descrizione";
    private final String numPersone = "numPersone";

    public TaskTypeTable(Connection connection) {
        super("taskType", connection);
    }

    @Override
    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key + " INT NOT NULL PRIMARY KEY," +
            descrizione + " CHAR(150) NOT NULL," +
            numPersone + " INT NOT NULL" +
            ")");
    }

    @Override
    public Optional<TaskType> findByPrimaryKey(Integer codiceTaskType) {
        return findByPrimaryKeyPrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setInt(1, codiceTaskType);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean save(TaskType taskType) {
        return savePrivate(taskType, " VALUES(?, ?, ?)", st -> {
            try {
                st.setInt(1, taskType.getCodiceTaskType());
                st.setString(2, taskType.getDescrizione());
                st.setInt(3, taskType.getNumPersone());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean delete(Integer codiceTaskType) {
        return deletePrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setInt(1, codiceTaskType);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean update(TaskType taskType) {
        return updatePrivate(
            taskType, " SET " + descrizione + " = ?, " + numPersone + " = ? WHERE " + key + " = ?", st -> {
            try {
                st.setString(1, taskType.getDescrizione());
                st.setInt(2, taskType.getNumPersone());
                st.setInt(3, taskType.getCodiceTaskType());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected List<TaskType> readStudentsFromResultSet(ResultSet resultSet) {
        List<TaskType> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new TaskType(
                    resultSet.getInt(key), 
                    resultSet.getString(descrizione),
                    resultSet.getInt(numPersone)));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }

    @Override
    public TaskType getRowSample() {
        return new TaskType(0, "sample", 0);
    }
    
}
