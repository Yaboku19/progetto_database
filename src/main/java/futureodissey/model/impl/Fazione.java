package futureodissey.model.impl;

import java.util.Objects;

public class Fazione {
    private final String nomeFazione;
    private final String nomeCapitano;
    
    public Fazione(final String nomeFazione, final String nomeCapitano) {
        this.nomeFazione = Objects.requireNonNull(nomeFazione);
        this.nomeCapitano = Objects.requireNonNull(nomeCapitano);
    }
    
    public String getNomeFazione() {
        return this.nomeFazione;
    }
    
    public String getNomeCapitano() {
        return this.nomeCapitano;
    }
    
    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(nomeFazione).append(") ")
            .append(nomeCapitano).toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Fazione)
            && ((Fazione) other).getNomeFazione().equals(this.getNomeFazione())
            && ((Fazione) other).getNomeCapitano().equals(this.getNomeCapitano());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeFazione, nomeCapitano);
    }
}
