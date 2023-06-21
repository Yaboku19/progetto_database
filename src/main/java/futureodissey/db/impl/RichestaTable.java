package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import futureodissey.db.api.AbstractTable;
import futureodissey.db.api.Table;
import futureodissey.model.impl.rowtype.Richiesta;
import javafx.util.Pair;

public class RichestaTable extends AbstractTable<Richiesta> implements Table<Richiesta, Pair<String, Integer>> {
    private final String key1 = "nomeRisorsa";
    private final String key2 = "codiceTaskType";
    private final String quantita = "quantita";

    public RichestaTable(Connection connection) {
        super("richiesta", connection);
    }

    @Override
    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key1 + " CHAR(40) NOT NULL," +
            key2 + " INT NOT NULL," +
            quantita + " INT NOT NULL," +
            "PRIMARY KEY (" + key1 + ", " + key2 +
            "))");
    }

    @Override
    public Optional<Richiesta> findByPrimaryKey(Pair<String, Integer> primaryKey) {
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
    public boolean save(Richiesta richiesta) {
        return savePrivate(richiesta, " VALUES(?, ?, ?)", st -> {
            try {
                st.setString(1, richiesta.getNomeRisorsa());
                st.setInt(2, richiesta.getCodiceTaskType());
                st.setInt(3, richiesta.getQuantita());
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
    public boolean update(Richiesta richiesta) {
        return updatePrivate(richiesta, " SET " + quantita + " = ? WHERE " + key1 + " = ? AND " + key2 + " = ?", st -> {
            try {
                st.setInt(1, richiesta.getQuantita());
                st.setString(2, richiesta.getNomeRisorsa());
                st.setInt(3, richiesta.getCodiceTaskType());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected List<Richiesta> readStudentsFromResultSet(ResultSet resultSet) {
        List<Richiesta> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new Richiesta(
                    resultSet.getString(key1), 
                    resultSet.getInt(key2),
                    resultSet.getInt(quantita)));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }

    @Override
    public Richiesta getRowSample() {
        return new Richiesta("sample", 0, 0);
    }
    
}
