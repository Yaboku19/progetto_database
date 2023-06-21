package futureodissey.model.impl.rowtype;

import java.util.Objects;

import futureodissey.model.api.rowtype.RowType;

public class Pianeta implements RowType {
    private final String nomePianeta;
    private final String nomeRisorsa;

    public Pianeta(final String nomePianeta, final String nomeRisorsa) {
        this.nomePianeta = Objects.requireNonNull(nomePianeta);
        this.nomeRisorsa = Objects.requireNonNull(nomeRisorsa);
    }

    public String getNomePianeta() {
        return this.nomePianeta;
    }

    public String getNomeRisorsa() {
        return this.nomeRisorsa;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(nomePianeta).append(") ")
            .append(nomeRisorsa).toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Pianeta)
            && ((Pianeta) other).getNomePianeta().equals(this.getNomePianeta())
            && ((Pianeta) other).getNomeRisorsa().equals(this.getNomeRisorsa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomePianeta, nomeRisorsa);
    }

    @Override
    public boolean isSameClass(Object object) {
        return object instanceof Pianeta;
    }
}
