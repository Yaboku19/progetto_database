package futureodissey.model.impl.rowtype;

import java.util.Objects;

public class Risorsa {
    private final String nomeRisorsa;

    public Risorsa(final String nomeRisorsa) {
        this.nomeRisorsa = nomeRisorsa;
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
}
