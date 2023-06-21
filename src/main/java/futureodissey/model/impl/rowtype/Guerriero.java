package futureodissey.model.impl.rowtype;

import java.util.Objects;
import java.util.Optional;

import futureodissey.model.api.rowtype.RowType;

public class Guerriero implements RowType{
    private final int codicePersona;
    private final String nomeFazione;
    private final Optional<String> nomeInsediamento;
    
    public Guerriero(final int codicePersona, final String nomeFazione, final Optional<String> nomeInsediamento) {
        this.codicePersona = Objects.requireNonNull(codicePersona);
        this.nomeFazione = Objects.requireNonNull(nomeFazione);
        this.nomeInsediamento = Objects.requireNonNull(nomeInsediamento);
    }

    public Guerriero(final int codicePersona, final String nomeFazione) {
        this(codicePersona, nomeFazione, Optional.empty());
    }
    
    public String getNomeFazione() {
        return this.nomeFazione;
    }
    
    public int getCodicePersona() {
        return this.codicePersona;
    }

    public Optional<String> getNomeInsediamento() {
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
        return (other instanceof Guerriero)
            && ((Guerriero) other).getCodicePersona() == this.getCodicePersona()
            && ((Guerriero) other).getNomeFazione().equals(this.getNomeFazione())
            && ((Guerriero) other).getNomeInsediamento().equals(this.getNomeInsediamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codicePersona, nomeFazione, nomeInsediamento);
    }

    @Override
    public boolean isSameClass(Object object) {
        return object instanceof Guerriero;
    }
}