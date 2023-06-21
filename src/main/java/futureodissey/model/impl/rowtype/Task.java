package futureodissey.model.impl.rowtype;

import java.util.Objects;
import java.util.Optional;

import futureodissey.model.api.rowtype.RowType;
import javafx.util.Pair;

public class Task implements RowType<Pair<String, Integer>> {
    private final String nomeFazione;
    private final int codiceTask;
    private final int codiceTaskType;
    private final Optional<String> nomeInsediamento;

    public Task(final String nomeFazione, final int codiceTask, final int codiceTaskType, final Optional<String> nomeInsediamento) {
        this.nomeFazione = Objects.requireNonNull(nomeFazione);
        this.codiceTask = Objects.requireNonNull(codiceTask);
        this.codiceTaskType = Objects.requireNonNull(codiceTaskType);
        this.nomeInsediamento = Objects.requireNonNull(nomeInsediamento);
    }

    public Task(final String nomeFazione, final int codiceTask, final int codiceTaskType) {
        this(nomeFazione, codiceTask, codiceTaskType, Optional.empty());
    }

    public String getNomeFazione() {
        return nomeFazione;
    }

    public int getCodiceTask() {
        return codiceTask;
    }

    public int getCodiceTaskType() {
        return codiceTaskType;
    }

    public Optional<String> getNomeInsediamento() {
        return nomeInsediamento;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(nomeFazione).append(", ").append(codiceTask).append(") ")
            .append(codiceTaskType).append(" - ").append(nomeInsediamento).toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Task)
            && ((Task) other).getCodiceTask() == this.getCodiceTask()
            && ((Task) other).getNomeFazione().equals(this.getNomeFazione())
            && ((Task) other).getCodiceTaskType() == this.getCodiceTaskType()
            && ((Task) other).getNomeInsediamento().equals(this.getNomeInsediamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeFazione, codiceTask, codiceTaskType, nomeInsediamento);
    }

    @Override
    public boolean isSameClass(Object object) {
        return object instanceof Task;
    }

    @Override
    public Pair<String, Integer> getKey() {
        return new Pair<>(nomeFazione, codiceTask);
    }
}
