package futureodissey.model.impl.rowtype;

import java.util.Objects;

import futureodissey.model.api.rowtype.RowType;

public class Insediamento implements RowType {
    private final String nomeFazione;
    private final String nomeInsediamento;
    private final String nomePianeta;
    
    public Insediamento(final String nomeFazione, final String nomeInsediamento, final String NomePianeta) {
        this.nomeFazione = Objects.requireNonNull(nomeFazione);
        this.nomeInsediamento = Objects.requireNonNull(nomeInsediamento);
        this.nomePianeta = Objects.requireNonNull(NomePianeta);
    }
    
    public String getNomeFazione() {
        return this.nomeFazione;
    }

    public String getNomeInsediamento() {
        return nomeInsediamento;
    }

    public String getNomePianeta() {
        return nomePianeta;
    }
    
    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(nomeFazione).append(", ").append(nomeInsediamento).append(") ")
            .append(nomePianeta).toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Insediamento)
            && ((Insediamento) other).getNomeFazione().equals(this.getNomeFazione())
            && ((Insediamento) other).getNomeInsediamento().equals(this.getNomeInsediamento())
            && ((Insediamento) other).getNomePianeta().equals(this.getNomePianeta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeFazione, nomeInsediamento, nomePianeta);
    }

    @Override
    public boolean isSameClass(Object object) {
        return object instanceof Insediamento;
    }
}
