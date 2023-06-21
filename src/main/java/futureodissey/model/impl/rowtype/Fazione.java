package futureodissey.model.impl.rowtype;

import java.util.Objects;

import futureodissey.model.api.rowtype.RowType;

public class Fazione implements RowType{
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

    @Override
    public boolean isSameClass(Object object) {
        return object instanceof Fazione;
    }
}
