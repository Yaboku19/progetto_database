package futureodissey.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import futureodissey.db.api.AbstractTable;
import futureodissey.db.api.Table;
import futureodissey.model.impl.rowtype.Insediamento;
import javafx.util.Pair;

public class InsediamentoTable extends AbstractTable<Insediamento> implements Table<Insediamento, Pair<String, String>>{
    private final String key1 = "nomeFazione";
    private final String key2 = "nomeInsediamento";
    private final String pianeta = "nomePianeta";

    public InsediamentoTable(Connection connection) {
        super("insediamento", connection);
    }

    @Override
    public boolean createTable() {
        return createTablePrivate("CREATE TABLE " + tableName + " (" +
            key1 + " CHAR(40) NOT NULL," +
            key2 + " CHAR(40) NOT NULL," +
            pianeta + " char(40) NOT NULL," +
            "PRIMARY KEY (" + key1 + ", " + key2 +
            "))");
    }

    @Override
    public Optional<Insediamento> findByPrimaryKey(Pair<String, String> primaryKey) {
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
    public boolean save(Insediamento insediamento) {
        return savePrivate(insediamento, " VALUES(?, ?, ?)", st -> {
            try {
                st.setString(1, insediamento.getNomeFazione());
                st.setString(2, insediamento.getNomeInsediamento());
                st.setString(3, insediamento.getNomePianeta());
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
    public boolean update(Insediamento insediamento) {
        return updatePrivate(insediamento, " SET " + pianeta + " = ? WHERE " + key1 + " = ? AND " + key2 + " = ?", st -> {
            try {
                st.setString(1, insediamento.getNomePianeta());
                st.setString(2, insediamento.getNomeFazione());
                st.setString(3, insediamento.getNomeInsediamento());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected List<Insediamento> readStudentsFromResultSet(ResultSet resultSet) {
        List<Insediamento> result = new ArrayList<>();
        try {
            while(resultSet.next()) {
                result.add(new Insediamento(
                    resultSet.getString(key1), 
                    resultSet.getString(key2),
                    resultSet.getString(pianeta)));
            }
        } catch (SQLException e) {
            return result;
        }
        return result;
    }

    @Override
    public Insediamento getRowSample() {
        return new Insediamento("sample", "sample", "sample");
    }

    public List<String> getNomeInsediamentoFromNomeFazione(final String nomeFazione) {
        final String query = "SELECT " + key2 + " FROM " + tableName + " WHERE " + key1 + " = \"" + nomeFazione + "\"";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            List<String> toReturn = new ArrayList<>();
            while(result.next()) {
                toReturn.add(result.getString(key2));
            }
            return toReturn;
        } catch (final SQLException e) {
            System.out.println("bro");
            return new ArrayList<>();
        }
    }

    public List<String> getNomeFazioneAltruiFromNomeFAzione(final String NomeFazione, final PianetaTable pianeta) {
        final String query = "SELECT I."+ key2 + ", P." + pianeta.getRisorsa() + " FROM " + tableName + 
            " I, " + pianeta.getTableName() + " P WHERE " + key1 + " != \"" + NomeFazione + "\" and I." + 
            this.pianeta + " = P." + pianeta.getKey();
        System.out.println(query);
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            List<String> toReturn = new ArrayList<>();
            while(result.next()) {
                toReturn.add(result.getString(key2) + " " +
                    result.getString(pianeta.getRisorsa()));
            }
            return toReturn;
        }catch (final SQLException e) {
            return new ArrayList<>();
        }
    }

    public String GetPianeta() {
        return pianeta;
    }
    
}
