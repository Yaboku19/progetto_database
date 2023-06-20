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

    public FazioneTable(Connection connection) {
        super("fazione", connection);
    }

    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            "nomeFazione char(40) NOT NULL PRIMARY KEY," +
            "nomeCapitano CHAR(40) NOT NULL," +
            ")");
    }

    public Optional<Fazione> findByPrimaryKey(final String name) {
        return findByPrimaryKeyPrivate(name, " WHERE NomeFazione = ?");
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
        return deletePrivate(NomeFazione, " WHERE NomeFazione = ?");
    }

    public boolean update(final Fazione fazione) {
        return updatePrivate(fazione, " SET NomeCapitano = ? WHERE NomeFazione = ?", st -> {
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
                    resultSet.getString("NomeFazione"), 
                    resultSet.getString("NomeCapitano")));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }
}
