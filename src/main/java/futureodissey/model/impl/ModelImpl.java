package futureodissey.model.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import futureodissey.model.impl.rowtype.Pianeta;
import futureodissey.model.impl.rowtype.Task;

@SuppressWarnings("unchecked")
public class ModelImpl implements Model{
    private final ConnectionProvider connectionProvider;
    private final Set<Table> tableList = new HashSet<>();

    public ModelImpl(final String username, final String password, final String dbName) {
        connectionProvider = new ConnectionProvider(username, password, dbName);
        inizialize();
        tableList
            .stream()
            .filter(t -> t.getClass().equals(InsediamentoTable.class))
            .map(t -> (InsediamentoTable) t)
            .forEach(t -> t.getNomeFazioneAltruiFromNomeFAzione("aa", tableList
                .stream()
                .filter(q -> q.getClass().equals(PianetaTable.class))
                .map(q -> (PianetaTable) q)
                .findFirst()
                .get()));
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

    @Override
    public RowType<? extends Object> getByPrimaryKey(Object key, Class<? extends Table> tableClass) {
        for (var value : tableList) {
            if (tableClass.equals(value.getClass())) {
                return value.findByPrimaryKey(key).isPresent() ? 
                    (RowType<? extends Object>) value.findByPrimaryKey(key).get() 
                    : null;
            }
        }
        return null;
    }

    @Override
    public List<String> getNomeInsediamentoFromNomeFazione(String nomeFazione) {
        return tableList
            .stream()
            .filter(t -> t.getClass().equals(InsediamentoTable.class))
            .map(t -> (InsediamentoTable) t)
            .map(t -> t.getNomeInsediamentoFromNomeFazione(nomeFazione))
            .findFirst()
            .get();
    }

    @Override
    public List<Pianeta> getFreePianeta() {
        return tableList
            .stream()
            .filter(t -> t.getClass().equals(PianetaTable.class))
            .map(t -> (PianetaTable) t)
            .map(t -> t.getFreePianeta(tableList
                .stream()
                .filter(q -> q.getClass().equals(InsediamentoTable.class))
                .map(q -> (InsediamentoTable) q)
                .findFirst()
                .get()))
            .findFirst()
            .get();
    }

    @Override
    public List<String> getInsediamentoRisorsaAltruiFromNomeFazione(String nomeFazione) {
        return tableList
            .stream()
            .filter(t -> t.getClass().equals(InsediamentoTable.class))
            .map(t -> (InsediamentoTable) t)
            .map(t -> t.getNomeFazioneAltruiFromNomeFAzione(nomeFazione, tableList
                .stream()
                .filter(q -> q.getClass().equals(PianetaTable.class))
                .map(q -> (PianetaTable) q)
                .findFirst()
                .get()))
            .findFirst()
            .get();
    }

    @Override
    public void creaTask(int codiceTask, String nomeFazione, Optional<String> nomeInsediamento1,
            Optional<String> nomeInsediamento2, Optional<String> nomePianeta, int num) {
        for (int i = 0; i < num; i++) {
            int codice = tableList
                .stream()
                .filter(t -> t.getClass().equals(TaskTable.class))
                .map(t -> (TaskTable) t)
                .map(t -> t.getNextCodice(nomeFazione))
                .findFirst()
                .get();
            this.addElement(new Task(nomeFazione, codice, codiceTask, nomeInsediamento1, nomeInsediamento2, nomePianeta));
        }
    }

    @Override
    public List<Task> getTaskFromNomeFazione(final String nomeFazione) {
         return tableList
            .stream()
            .filter(t -> t.getClass().equals(TaskTable.class))
            .map(t -> (TaskTable) t)
            .map(t -> t.getTaskFromNomeFazione(nomeFazione))
            .findFirst()
            .get();
    }

    @Override
    public void executeTask() {
        TaskTable taskTable = tableList
            .stream()
            .filter(t -> t.getClass().equals(TaskTable.class))
            .map(t -> ((TaskTable) t)).findFirst().get();
        List<Task> taskList = taskTable.getTaskSorted();
        List<Task> taskListCopy = new ArrayList<>(taskList);
        for(final var task : taskListCopy) {
            if (!isWrong(task)) {
                executeATask(task);
            }
        }
        taskTable.dropTable();
        taskTable.createTable();

    }

    private boolean isWrong(final Task task) {
        switch(task.getCodiceTaskType()) {
            case 0:
                if (task.getNomeInsediamento1().isEmpty()) {
                    return true;
                }
                break;
            case 1:
                if (task.getNomeInsediamento1().isEmpty()) {
                    return true;
                }
                break;
            case 2:
                if (task.getNomePianeta().isEmpty() || task.getNomeInsediamento1().isEmpty()) {
                    return true;
                }
                break;
            case 3:
                if (task.getNomeInsediamento1().isEmpty()) {
                    return true;
                }
                break;
            case 4:
                if (task.getNomeInsediamento1().isEmpty() || task.getNomeInsediamento2().isEmpty()) {
                    return true;
                }
                break;
            case 5:
                if (task.getNomeInsediamento1().isEmpty() || task.getNomeInsediamento2().isEmpty()) {
                    return true;
                }
                break;
            case 6:
                if (task.getNomeInsediamento1().isEmpty()) {
                    return true;
                }
                break;
        }
        return isNotEnogh(task.getCodiceTask());
    }

    private void executeATask(final Task task) {
        System.out.println(task);
        switch(task.getCodiceTaskType()) {
            case 0:
                createLavoratore(task);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    private void createLavoratore(final Task task) {

    }

    private boolean isNotEnogh(final int codiceTask) {
        final var list = getRichiestaDisponibilita(codiceTask);
        for (var value : list) {
            if (value.getDisponibilita() < value.getRichiesta()) {
                return true;
            }
        }
        return false;
    }

    private List<TaskTransform> getRichiestaDisponibilita(final int codiceTask) {
        String query = "SELECT D.nomeRisorsa, D.quantita as disponibile , R.quantita as richiesta " + 
                "FROM disponibilita D, fazione F, task T, tasktype Ts, richiesta R " +
                "WHERE D.nomeFazione = F.nomeFazione AND F.nomeFazione = T.nomeFazione " +
                "AND T.codiceTaskType = Ts.codiceTaskType AND Ts.codiceTaskType = R.codiceTaskType " +
                "AND R.nomeRisorsa = D.nomeRisorsa AND T.codiceTask = " + codiceTask;
        try (final PreparedStatement statement = this.connectionProvider.getMySQLConnection().prepareStatement(query)) {  
            final ResultSet result = statement.executeQuery();
            List<TaskTransform> toReturn = new ArrayList<>();
            while(result.next()) {
                toReturn.add(new TaskTransform(result.getString("nomeRisorsa"),
                    result.getInt("disponibile"),
                    result.getInt("richiesta")));
            }
            return toReturn;
        } catch (final SQLException e) {
            return new ArrayList<>();
        }
    }
}
