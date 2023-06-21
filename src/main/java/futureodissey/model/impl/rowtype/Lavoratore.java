package futureodissey.model.impl.rowtype;

import java.util.Objects;

import futureodissey.model.api.rowtype.RowType;

public class Lavoratore implements RowType<Integer> {
    private final int codicePersona;
    private final String nomeFazione;
    private final String nomeInsediamento;
    
    public Lavoratore(final int codicePersona, final String nomeFazione, final String nomeInsediamento) {
        this.codicePersona = Objects.requireNonNull(codicePersona);
        this.nomeFazione = Objects.requireNonNull(nomeFazione);
        this.nomeInsediamento = Objects.requireNonNull(nomeInsediamento);
    }
    
    public String getNomeFazione() {
        return this.nomeFazione;
    }
    
    public int getCodicePersona() {
        return this.codicePersona;
    }

    public String getNomeInsediamento() {
        return nomeInsediamento;
    }
    
    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(codicePersona).append(") ")
            .append(nomeFazione).append(" - ").append(nomeInsediamento).toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Lavoratore)
            && ((Lavoratore) other).getCodicePersona() == this.getCodicePersona()
            && ((Lavoratore) other).getNomeFazione().equals(this.getNomeFazione())
            && ((Lavoratore) other).getNomeInsediamento().equals(this.getNomeInsediamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codicePersona, nomeFazione, nomeInsediamento);
    }

    @Override
    public boolean isSameClass(Object object) {
        return object instanceof Lavoratore;
    }

    @Override
    public Integer getKey() {
        return this.codicePersona;
    }
}
