package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import futureodissey.db.api.AbstractTable;
import futureodissey.model.impl.Fazione;

public class FazioneTable extends AbstractTable<Fazione>{
    private final String key = "nomeFazione";
    private final String capitano = "NomeCapitano";

    public FazioneTable(Connection connection) {
        super("fazione", connection);
    }

    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key + " char(40) NOT NULL PRIMARY KEY," +
            capitano + " CHAR(40) NOT NULL" +
            ")");
    }

    public Optional<Fazione> findByPrimaryKey(final String name) {
        return findByPrimaryKeyPrivate(name, " WHERE " + key + " = ?");
    }

    public boolean save(final Fazione fazione) {
        return savePrivate(fazione, " VALUES(?, ?)", st -> {
            try {
                st.setString(1, fazione.getNomeFazione());
                st.setString(2, fazione.getNomeCapitano());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean delete(final String NomeFazione) {
        return deletePrivate(NomeFazione, " WHERE " + key + " = ?");
    }

    public boolean update(final Fazione fazione) {
        return updatePrivate(fazione, " SET " + capitano + " = ? WHERE " + key + " = ?", st -> {
            try {
                st.setString(1, fazione.getNomeCapitano());
                st.setString(2, fazione.getNomeFazione());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    
    protected List<Fazione> readStudentsFromResultSet(final ResultSet resultSet) {
        List<Fazione> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new Fazione(
                    resultSet.getString(key), 
                    resultSet.getString(capitano)));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }
}
