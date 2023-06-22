package futureodissey.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import futureodissey.db.ConnectionProvider;
import futureodissey.db.api.Table;
import futureodissey.db.impl.DisponibilitaTable;
import futureodissey.db.impl.FazioneTable;
import futureodissey.db.impl.GuerrieroTable;
import futureodissey.db.impl.InsediamentoTable;
import futureodissey.db.impl.LavoratoreTable;
import futureodissey.db.impl.PianetaTable;
import futureodissey.db.impl.RichiestaTable;
import futureodissey.db.impl.RisorsaTable;
import futureodissey.db.impl.TaskTable;
import futureodissey.db.impl.TaskTypeTable;
import futureodissey.model.api.Model;
import futureodissey.model.api.rowtype.RowType;

@SuppressWarnings("unchecked")
public class ModelImpl implements Model{
    private final ConnectionProvider connectionProvider;
    private final Set<Table> tableList = new HashSet<>();

    public ModelImpl(final String username, final String password, final String dbName) {
        connectionProvider = new ConnectionProvider(username, password, dbName);
        inizialize();
    }

    private void inizialize() {
        tableList.add(new DisponibilitaTable(connectionProvider.getMySQLConnection()));
        tableList.add(new FazioneTable(connectionProvider.getMySQLConnection()));
        tableList.add(new GuerrieroTable(connectionProvider.getMySQLConnection()));
        tableList.add(new InsediamentoTable(connectionProvider.getMySQLConnection()));
        tableList.add(new LavoratoreTable(connectionProvider.getMySQLConnection()));
        tableList.add(new PianetaTable(connectionProvider.getMySQLConnection()));
        tableList.add(new RichiestaTable(connectionProvider.getMySQLConnection()));
        tableList.add(new RisorsaTable(connectionProvider.getMySQLConnection()));
        tableList.add(new TaskTable(connectionProvider.getMySQLConnection()));
        tableList.add(new TaskTypeTable(connectionProvider.getMySQLConnection()));
        tableList.stream().forEach(t -> {
            t.dropTable();
            t.createTable();
        });
    }

    @Override
    public void addElement(RowType<? extends Object> row) {
        tableList.stream().forEach(t -> {
            if (t.getRowSample().isSameClass(row)) {
                t.save(row);
            }
        });
    }

    @Override
    public void removeElement(RowType<? extends Object> row) {
        tableList.stream().forEach(t -> {
            if (t.getRowSample().isSameClass(row)) {
                t.delete(row.getKey());
            }
        });
    }

    @Override
    public List<RowType<? extends Object>> getAllElement(Class<? extends Table> tableClass) {
        List<RowType<? extends Object>> toReturn = new ArrayList<>();
        tableList.stream().forEach(t -> {
            if(tableClass.equals(t.getClass())) {
                toReturn.addAll(t.findAll());
            }
        });
        return toReturn == null ? new ArrayList<>() : toReturn;
    }
    
}
