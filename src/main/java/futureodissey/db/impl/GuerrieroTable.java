package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import futureodissey.db.api.AbstractTable;
import futureodissey.db.api.Table;
import futureodissey.model.impl.rowtype.Guerriero;

public class GuerrieroTable extends AbstractTable<Guerriero> implements Table<Guerriero, Integer>{
    private final String key = "codicePersona";
    private final String fazione = "nomeFazione";
    private final String insediamento = "nomeInsediamento";

    public GuerrieroTable(Connection connection) {
        super("guerriero", connection);
    }

    @Override
    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key + " INT NOT NULL PRIMARY KEY," +
            fazione + " CHAR(40) NOT NULL," +
            insediamento + " char(40)" +
            ")");
    }

    @Override
    public Optional<Guerriero> findByPrimaryKey(final Integer codicePersona) {
        return findByPrimaryKeyPrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setInt(1, codicePersona);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean save(final Guerriero guerriero) {
        return savePrivate(guerriero, " VALUES(?, ?, ?)", st -> {
            try {
                st.setInt(1, guerriero.getCodicePersona());
                st.setString(2, guerriero.getNomeFazione());
                st.setString(3, guerriero.getNomeInsediamento().isEmpty() ? null : guerriero.getNomeInsediamento().get());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean delete(final Integer codicePersona) {
        return deletePrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setInt(1, codicePersona);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean update(final Guerriero guerriero) {
        return updatePrivate(guerriero, " SET " + fazione + " = ?, " + insediamento + " = ? WHERE " + key + " = ?", st -> {
            try {
                st.setString(1, guerriero.getNomeFazione());
                st.setString(2, guerriero.getNomeInsediamento().isEmpty() ? null : guerriero.getNomeInsediamento().get());
                st.setInt(3, guerriero.getCodicePersona());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected List<Guerriero> readStudentsFromResultSet(final ResultSet resultSet) {
        List<Guerriero> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new Guerriero(
                    resultSet.getInt(key), 
                    resultSet.getString(fazione),
                    Optional.ofNullable(resultSet.getString(insediamento))));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }

    @Override
    public Guerriero getRowSample() {
        return new Guerriero(0, "sample");
    }
}
