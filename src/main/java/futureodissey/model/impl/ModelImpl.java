package futureodissey.model.impl;

import java.sql.Connection;
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
import futureodissey.model.impl.rowtype.Disponibilita;
import futureodissey.model.impl.rowtype.Guerriero;
import futureodissey.model.impl.rowtype.Insediamento;
import futureodissey.model.impl.rowtype.Lavoratore;
import futureodissey.model.impl.rowtype.Pianeta;
import futureodissey.model.impl.rowtype.Task;

@SuppressWarnings("unchecked")
public class ModelImpl implements Model{
    private final ConnectionProvider connectionProvider;
    private final Connection connection;
    private final Set<Table> tableList = new HashSet<>();

    public ModelImpl(final String username, final String password, final String dbName) {
        connectionProvider = new ConnectionProvider(username, password, dbName);
        connection = connectionProvider.getMySQLConnection();
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
                } else if (notEnoughGuerrieri(task)){
                    return true;
                }
                System.out.println(task);
                break;
            case 5:
                if (task.getNomeInsediamento1().isEmpty() || task.getNomeInsediamento2().isEmpty()) {
                    return true;
                } else if (notEnoughLavoratori(task)){
                    return true;
                }
                break;
            case 6:
                if (task.getNomeInsediamento1().isEmpty()) {
                    return true;
                }
                break;
        }
        return isNotEnogh(task.getCodiceTask(), task.getNomeFazione());
    }

    private boolean notEnoughLavoratori (final Task task) {
        return getNumLavoratoriFromInsediamento(task.getNomeInsediamento1().get()) < 1;
    }

    private boolean notEnoughGuerrieri (final Task task) {
        return getNumGuerrieriFromInsediamento(task.getNomeInsediamento1().get()) < 1;
    }

    private void executeATask(final Task task) {
        switch(task.getCodiceTaskType()) {
            case 0:
                createLavoratore(task);
                break;
            case 1:
                createGuerriero(task);
                break;
            case 2:
                createInsediamento(task);
                break;
            case 3:
                break;
            case 4:
                transferGuerrieri(task);
                break;
            case 5:
                transferLavoratori(task);
                break;
            case 6:
                getRisorse(task);
                break;
        }
        setRisorse(task);
    }

    private void transferGuerrieri(final Task task) {
        final Guerriero guerriero = getAGuerrieroFromNomeInsediamento(task.getNomeInsediamento1().get());
        tableList
            .stream()
            .filter(t -> t.getClass().equals(GuerrieroTable.class))
            .map(t -> (GuerrieroTable) t)
            .findFirst()
            .get()
            .update(new Guerriero(guerriero.getCodicePersona(), guerriero.getNomeFazione(),
                task.getNomeInsediamento2().get().equals("Esercito") ? Optional.empty() : task.getNomeInsediamento2()));
    }

    private void transferLavoratori(final Task task) {
        final Lavoratore lavoratore = getALavoratoreFromNomeInsediamento(task.getNomeInsediamento1().get());
        tableList
            .stream()
            .filter(t -> t.getClass().equals(LavoratoreTable.class))
            .map(t -> (LavoratoreTable) t)
            .findFirst()
            .get()
            .update(new Lavoratore(lavoratore.getCodicePersona(), lavoratore.getNomeFazione(),
                task.getNomeInsediamento2().get()));
    }

    private void getRisorse(final Task task) {
        final int num = getNumLavoratoriFromInsediamento(task.getNomeInsediamento1().get());
        final Disponibilita disponibilita = getDisponibilitaFromNomeInsediamento(task.getNomeInsediamento1().get());
        tableList.stream()
            .filter(t -> t.getClass().equals(DisponibilitaTable.class))
            .map(t -> (DisponibilitaTable) t)
            .findFirst()
            .get()
            .update(new Disponibilita(disponibilita.getNomeRisorsa(), task.getNomeFazione(), disponibilita.getQuantita() + num));
    }

    private void setRisorse(final Task task) {
        getRichiestaDisponibilita(task.getCodiceTask(), task.getNomeFazione()).forEach(r -> {
            int num = r.getDisponibilita() - r.getRichiesta();
            tableList.stream()
            .filter(t -> t.getClass().equals(DisponibilitaTable.class))
            .map(t -> (DisponibilitaTable) t)
            .findFirst()
            .get()
            .update(new Disponibilita(r.getNomeRisorsa(), task.getNomeFazione(), num));
        });
    }

    private void createLavoratore(final Task task) {
        final int codice = getNextCodiceLavoratore();
        tableList.stream()
            .filter(t -> t.getClass().equals(LavoratoreTable.class))
            .map(t -> (LavoratoreTable) t)
            .findFirst()
            .get()
            .save(new Lavoratore(codice, task.getNomeFazione(), task.getNomeInsediamento1().get()));
    }

    private void createGuerriero(final Task task) {
        final int codice = getNextCodiceGuerriero();
        tableList.stream()
            .filter(t -> t.getClass().equals(GuerrieroTable.class))
            .map(t -> (GuerrieroTable) t)
            .findFirst()
            .get()
            .save(new Guerriero(codice, task.getNomeFazione(), task.getNomeInsediamento1()));
    }

    private void createInsediamento(final Task task) {
        tableList.stream()
            .filter(t -> t.getClass().equals(InsediamentoTable.class))
            .map(t -> (InsediamentoTable) t)
            .findFirst()
            .get()
            .save(new Insediamento(task.getNomeFazione(), task.getNomeInsediamento1().get(), task.getNomePianeta().get()));
    }

    private boolean isNotEnogh(final int codiceTask, final String nomeFazione) {
        final var list = getRichiestaDisponibilita(codiceTask, nomeFazione);
        for (var value : list) {
            if (value.getDisponibilita() < value.getRichiesta()) {
                return true;
            }
        }
        return false;
    }

    private List<TaskTransform> getRichiestaDisponibilita(final int codiceTask, final String nomeFazione) {
        String query = "SELECT D.nomeRisorsa, D.quantita as disponibile , R.quantita as richiesta " + 
                "FROM disponibilita D, fazione F, task T, tasktype Ts, richiesta R " +
                "WHERE D.nomeFazione = F.nomeFazione AND F.nomeFazione = T.nomeFazione " +
                "AND T.codiceTaskType = Ts.codiceTaskType AND Ts.codiceTaskType = R.codiceTaskType " +
                "AND R.nomeRisorsa = D.nomeRisorsa AND T.codiceTask = " + codiceTask +
                " AND F.nomeFazione = \"" + nomeFazione + "\"";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {  
            final ResultSet result = statement.executeQuery();
            List<TaskTransform> toReturn = new ArrayList<>();
            while(result.next()) {
                toReturn.add(new TaskTransform(result.getString("nomeRisorsa"),
                    result.getInt("disponibile"),
                    result.getInt("richiesta")));
            }
            return toReturn;
        } catch (final SQLException e) {
            System.out.println("errore");
            return new ArrayList<>();
        }
    }

    private int getNextCodiceLavoratore() {
        final String query = "SELECT MAX(codicePersona) AS codicePersona FROM lavoratore";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            int toReturn = -1;
            while(result.next()) {
                toReturn = result.getString("codicePersona") == null ? -1 : result.getInt("codicePersona");
            }
            return toReturn + 1;
        } catch (final SQLException e) {
            return -5;
        }
    }

    private int getNextCodiceGuerriero() {
        final String query = "SELECT MAX(codicePersona) AS codicePersona FROM guerriero";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            int toReturn = -1;
            while(result.next()) {
                toReturn = result.getString("codicePersona") == null ? -1 : result.getInt("codicePersona");
            }
            return toReturn + 1;
        } catch (final SQLException e) {
            return -5;
        }
    }

    private int getNumLavoratoriFromInsediamento(final String nomeInsediamento) {
        final String query = 
            "SELECT count(*) as numero FROM insediamento I, lavoratore L WHERE" + 
            " I.nomeInsediamento = L.nomeInsediamento AND I.nomeInsediamento = \"" +
            nomeInsediamento + "\"";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            int toReturn = 0;
            while(result.next()) {
                toReturn = result.getString("numero") == null ? 0 : result.getInt("numero");
            }
            return toReturn;
        } catch (final SQLException e) {
            return -1;
        }
    }

    private int getNumGuerrieriFromInsediamento(final String nomeInsediamento) {
        String query;
        if (nomeInsediamento.equals("Esercito")) {
            query = "SELECT count(*) as numero FROM guerriero WHERE" + 
            " nomeInsediamento is null";
        } else {
            query = "SELECT count(*) as numero FROM insediamento I, guerriero G WHERE" + 
            " I.nomeInsediamento = G.nomeInsediamento AND I.nomeInsediamento = \"" +
            nomeInsediamento + "\"";
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            int toReturn = 0;
            while(result.next()) {
                toReturn = result.getString("numero") == null ? 0 : result.getInt("numero");
            }
            return toReturn;
        } catch (final SQLException e) {
            System.out.println("errore numGuerriero");
            return -1;
        }
    }

    private Disponibilita getDisponibilitaFromNomeInsediamento(final String nomeInsediamento) {
        final String query = "SELECT D.* FROM disponibilita D, pianeta P, insediamento I " +
            " WHERE D.nomeRisorsa = P.nomeRisorsa AND P.nomePianeta = I.nomePianeta " +
            "AND I.nomeInsediamento = \""+ nomeInsediamento + "\" AND I.nomeFazione = D.nomeFazione";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            Disponibilita toReturn = null;
            while(result.next()) {
                toReturn = new Disponibilita(result.getString("nomeRisorsa"),
                                            result.getString("nomeFazione"),
                                            result.getInt("quantita"));
            }
            return toReturn;
        } catch (final SQLException e) {
            return null;
        }
    }

    private Lavoratore getALavoratoreFromNomeInsediamento(final String nomeInsediamento) {
        final String query = "SELECT L.* FROM lavoratore L, insediamento I " +
            "WHERE L.nomeInsediamento = I.nomeInsediamento " + 
            "AND I.nomeInsediamento = \""+ nomeInsediamento + "\" LIMIT 1";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            Lavoratore toReturn = null;
            while(result.next()) {
                toReturn = new Lavoratore(result.getInt("codicePersona"),
                                        result.getString("nomeFazione"),
                                        result.getString("nomeInsediamento"));
            }
            return toReturn;
        } catch (final SQLException e) {
            return null;
        }
    }

    private Guerriero getAGuerrieroFromNomeInsediamento(final String nomeInsediamento) {
        String query;
        if (nomeInsediamento.equals("Esercito")) {
            query = "SELECT * FROM guerriero WHERE" + 
            " nomeInsediamento is null LIMIT 1";
        } else {
            query = "SELECT G.* FROM guerriero G, insediamento I " +
            "WHERE G.nomeInsediamento = I.nomeInsediamento " + 
            "AND I.nomeInsediamento = \""+ nomeInsediamento + "\" LIMIT 1";;
        }
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            Guerriero toReturn = null;
            while(result.next()) {
                toReturn = new Guerriero(result.getInt("codicePersona"),
                                        result.getString("nomeFazione"),
                                        Optional.ofNullable(result.getString("nomeInsediamento")));
            }
            return toReturn;
        } catch (final SQLException e) {
            System.out.println("errore getAGuerriero");
            return null;
        }
    }
}
