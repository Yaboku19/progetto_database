package futureodissey.model.impl.rowtype;

import java.util.Objects;

import futureodissey.model.api.rowtype.RowType;

public class Risorsa implements RowType {
    private final String nomeRisorsa;

    public Risorsa(final String nomeRisorsa) {
        this.nomeRisorsa = Objects.requireNonNull(nomeRisorsa);
    }

    public String getNomeRisorsa() {
        return this.nomeRisorsa;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(nomeRisorsa).append(") ").toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Risorsa)
            && ((Risorsa) other).getNomeRisorsa().equals(this.getNomeRisorsa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeRisorsa);
    }

    @Override
    public boolean isSameClass(Object object) {
        return object instanceof Risorsa;
    }
}
