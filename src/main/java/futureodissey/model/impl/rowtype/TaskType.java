package futureodissey.model.impl.rowtype;

import java.util.Objects;
import futureodissey.model.api.rowtype.RowType;

public class TaskType implements RowType<Integer> {
    private final int codiceTaskType;
    private final String descrizione;
    private final int numPersone;

    public TaskType(final int codiceTaskType, final String descrizione, final int numPersone) {
        this.codiceTaskType = codiceTaskType;
        this.descrizione = descrizione;
        this.numPersone = numPersone;
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

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(codiceTaskType).append(") ")
            .append(descrizione).append(" - ")
            .append(numPersone).append(" - ").toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof TaskType)
            && ((TaskType) other).getCodiceTaskType() == this.getCodiceTaskType()
            && ((TaskType) other).getDescrizione().equals(this.getDescrizione())
            && ((TaskType) other).getNumPersone() == this.getNumPersone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiceTaskType, descrizione, numPersone);
    }

    @Override
    public boolean isSameClass(Object object) {
        return object instanceof TaskType;
    }

    @Override
    public Integer getKey() {
        return this.codiceTaskType;
    }
}
