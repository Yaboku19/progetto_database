package futureodissey.model.impl.rowtype;

import java.util.Objects;

public class TaskType {
    private final int codiceTaskType;
    private final String descrizione;
    private final int numPersone;
    private final int tempo;

    public TaskType(final int codiceTaskType, final String descrizione, final int numPersone, final int tempo) {
        this.codiceTaskType = codiceTaskType;
        this.descrizione = descrizione;
        this.numPersone = numPersone;
        this.tempo = tempo;
    }

    public int getCodiceTaskType() {
        return this.codiceTaskType;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public int getNumPersone() {
        return this.numPersone;
    }

    public int getTempo() {
        return this.tempo;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(codiceTaskType).append(") ")
            .append(descrizione).append(" - ")
            .append(numPersone).append(" - ")
            .append(tempo).toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof TaskType)
            && ((TaskType) other).getCodiceTaskType() == this.getCodiceTaskType()
            && ((TaskType) other).getDescrizione().equals(this.getDescrizione())
            && ((TaskType) other).getNumPersone() == this.getNumPersone()
            && ((TaskType) other).getTempo() == this.getTempo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiceTaskType, descrizione, numPersone, tempo);
    }
}
