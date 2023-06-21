package futureodissey.model.impl.rowtype;

import java.util.Objects;

public class Disponibilita {
    private final String nomeRisorsa;
    private final String nomeFazione;
    private final int quantita;

    public Disponibilita (final String nomeRisorsa, final String nomeFazione, final int quantita) {
        this.nomeRisorsa = nomeRisorsa;
        this.nomeFazione = nomeFazione;
        this.quantita = quantita;
    }

    public String getNomeRisorsa() {
        return this.nomeRisorsa;
    }

    public String getNomeFazione() {
        return this.nomeFazione;
    }

    public int getQuantita() {
        return this.quantita;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(nomeRisorsa).append(", ").append(nomeFazione).append(") ")
            .append(quantita).toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Disponibilita)
            && ((Disponibilita) other).getNomeFazione().equals(this.getNomeFazione())
            && ((Disponibilita) other).getNomeRisorsa().equals(this.getNomeRisorsa())
            && ((Disponibilita) other).getQuantita() == this.getQuantita();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeRisorsa, nomeFazione, quantita);
    }
}