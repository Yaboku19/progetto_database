package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import futureodissey.db.api.AbstractTable;
import futureodissey.db.api.Table;
import futureodissey.model.impl.rowtype.Risorsa;

public class RisorsaTable extends AbstractTable<Risorsa> implements Table<Risorsa, String> {
    private final String key = "nomeRisorsa";

    public RisorsaTable(Connection connection) {
        super("risorsa", connection);
    }

    @Override
    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key + " char(40) NOT NULL PRIMARY KEY" +
            ")");
    }

    @Override
    public Optional<Risorsa> findByPrimaryKey(String nomeRisorsa) {
        return findByPrimaryKeyPrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setString(1, nomeRisorsa);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean save(Risorsa risorsa) {
        return savePrivate(risorsa, " VALUES(?)", st -> {
            try {
                st.setString(1, risorsa.getNomeRisorsa());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean delete(String nomeRisorsa) {
        return deletePrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setString(1, nomeRisorsa);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean update(Risorsa risorsa) {
        return true;
    }

    @Override
    protected List<Risorsa> readStudentsFromResultSet(ResultSet resultSet) {
        List<Risorsa> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new Risorsa(resultSet.getString(key)));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }

    @Override
    public Risorsa getRowSample() {
        return new Risorsa("sample");
    }    
}
