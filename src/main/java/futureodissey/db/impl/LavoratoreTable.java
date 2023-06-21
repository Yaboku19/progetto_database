package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import futureodissey.db.api.AbstractTable;
import futureodissey.db.api.Table;
import futureodissey.model.impl.rowtype.Lavoratore;

public class LavoratoreTable extends AbstractTable<Lavoratore> implements Table<Lavoratore, Integer>{
    private final String key = "codicePersona";
    private final String fazione = "nomeFazione";
    private final String insediamento = "nomeInsediamento";

    public LavoratoreTable(Connection connection) {
        super("lavoratore", connection);
    }

    @Override
    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key + " INT NOT NULL PRIMARY KEY," +
            fazione + " CHAR(40) NOT NULL," +
            insediamento + " char(40) NOT NULL" +
            ")");
    }

    @Override
    public Optional<Lavoratore> findByPrimaryKey(final Integer codicePersona) {
        return findByPrimaryKeyPrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setInt(1, codicePersona);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean save(final Lavoratore lavoratore) {
        return savePrivate(lavoratore, " VALUES(?, ?, ?)", st -> {
            try {
                st.setInt(1, lavoratore.getCodicePersona());
                st.setString(2, lavoratore.getNomeFazione());
                st.setString(3, lavoratore.getNomeInsediamento());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean delete(final Integer codicePersona) {
        return deletePrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setInt(0, codicePersona);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean update(final Lavoratore lavoratore) {
        return updatePrivate(lavoratore, " SET " + fazione + " = ?, " + insediamento + " = ? WHERE " + key + " = ?", st -> {
            try {
                st.setString(1, lavoratore.getNomeFazione());
                st.setString(2, lavoratore.getNomeInsediamento());
                st.setInt(3, lavoratore.getCodicePersona());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected List<Lavoratore> readStudentsFromResultSet(ResultSet resultSet) {
        List<Lavoratore> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new Lavoratore(
                    resultSet.getInt(key), 
                    resultSet.getString(fazione),
                    resultSet.getString(insediamento)));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }
}
