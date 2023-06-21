package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import futureodissey.db.api.AbstractTable;
import futureodissey.db.api.Table;
import futureodissey.model.impl.rowtype.Pianeta;

public class PianetaTable extends AbstractTable<Pianeta> implements Table<Pianeta, String>{
    private final String key = "nomePianeta";
    private final String risorsa = "nomeRisorsa";

    public PianetaTable(Connection connection) {
        super("pianeta", connection);
    }

    @Override
    public boolean createTable() {
         return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key + " char(40) NOT NULL PRIMARY KEY," +
            risorsa + " CHAR(40) NOT NULL" +
            ")");
    }

    @Override
    public Optional<Pianeta> findByPrimaryKey(String nomePianeta) {
        return findByPrimaryKeyPrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setString(1, nomePianeta);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean save(Pianeta pianeta) {
        return savePrivate(pianeta, " VALUES(?, ?)", st -> {
            try {
                st.setString(1, pianeta.getNomePianeta());
                st.setString(2, pianeta.getNomeRisorsa());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean delete(String nomePianeta) {
        return deletePrivate(" WHERE " + key + " = ?", st -> {
            try {
                st.setString(1, nomePianeta);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean update(Pianeta pianeta) {
        return updatePrivate(pianeta, " SET " + risorsa + " = ? WHERE " + key + " = ?", st -> {
            try {
                st.setString(1, pianeta.getNomePianeta());
                st.setString(2, pianeta.getNomeRisorsa());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected List<Pianeta> readStudentsFromResultSet(ResultSet resultSet) {
        List<Pianeta> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new Pianeta(
                    resultSet.getString(key), 
                    resultSet.getString(risorsa)));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }

    @Override
    public Pianeta getRowSample() {
        return new Pianeta("sample", "sample");
    }
    
}
