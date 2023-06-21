package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import futureodissey.db.api.AbstractTable;
import futureodissey.db.api.Table;
import futureodissey.model.impl.rowtype.Disponibilita;
import javafx.util.Pair;

public class DisponibilitaTable extends AbstractTable<Disponibilita> implements Table<Disponibilita, Pair<String, String>>{
    private final String key1 = "nomeRisorsa";
    private final String key2 = "nomeFazione";
    private final String quantita = "quantita";

    public DisponibilitaTable(String name, Connection connection) {
        super("disponibilita", connection);
    }

    @Override
    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key1 + " CHAR(40) NOT NULL," +
            key2 + " CHAR(40) NOT NULL," +
            quantita + " INT NOT NULL," +
            "PRIMARY KEY (" + key1 + ", " + key2 +
            "))");
    }

    @Override
    public Optional<Disponibilita> findByPrimaryKey(Pair<String, String> primaryKey) {
        return findByPrimaryKeyPrivate(" WHERE " + key1 + " = ? AND " + key2 + " = ?", st -> {
            try {
                st.setString(1, primaryKey.getKey());
                st.setString(2, primaryKey.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean save(Disponibilita disponibilita) {
        return savePrivate(disponibilita, " VALUES(?, ?, ?)", st -> {
            try {
                st.setString(1, disponibilita.getNomeRisorsa());
                st.setString(2, disponibilita.getNomeFazione());
                st.setInt(3, disponibilita.getQuantita());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean delete(Pair<String, String> primaryKey) {
        return deletePrivate(" WHERE " + key1 + " = ? AND " + key2 + " = ?", st -> {
            try {
                st.setString(1, primaryKey.getKey());
                st.setString(2, primaryKey.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean update(Disponibilita disponibilita) {
        return updatePrivate(disponibilita, " SET " + quantita + " = ? WHERE " + key1 + " = ? AND " + key2 + " = ?", st -> {
            try {
                st.setInt(1, disponibilita.getQuantita());
                st.setString(2, disponibilita.getNomeRisorsa());
                st.setString(3, disponibilita.getNomeFazione());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected List<Disponibilita> readStudentsFromResultSet(ResultSet resultSet) {
        List<Disponibilita> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new Disponibilita(
                    resultSet.getString(key1), 
                    resultSet.getString(key2),
                    resultSet.getInt(quantita)));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }
    
}
