package futureodissey.model.impl.rowtype;

import java.util.Objects;

import futureodissey.model.api.rowtype.RowType;

public class Richiesta implements RowType {
    private final String nomeRisorsa;
    private final int codiceTaskType;
    private final int quantita;

    public Richiesta(final String nomeRisorsa, final int codiceTaskType, final int quantita) {
        this.nomeRisorsa = Objects.requireNonNull(nomeRisorsa);
        this.codiceTaskType = Objects.requireNonNull(codiceTaskType);
        this.quantita = Objects.requireNonNull(quantita);
    }

    public String getNomeRisorsa() {
        return this.nomeRisorsa;
    }

    public int getCodiceTaskType() {
        return this.codiceTaskType;
    }

    public int getQuantita() {
        return this.quantita;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(nomeRisorsa).append(", ").append(codiceTaskType).append(") ")
            .append(quantita).toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Richiesta)
            && ((Richiesta) other).getCodiceTaskType() == this.getCodiceTaskType()
            && ((Richiesta) other).getNomeRisorsa().equals(this.getNomeRisorsa())
            && ((Richiesta) other).getQuantita() == this.getQuantita();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeRisorsa, codiceTaskType, quantita);
    }

    @Override
    public boolean isSameClass(Object object) {
        return object instanceof Richiesta;
    }
}
