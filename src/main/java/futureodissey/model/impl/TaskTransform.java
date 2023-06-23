package futureodissey.model.impl;

public class TaskTransform {
    private final String nomeRisorsa;
    private final int disponibilita;
    private final int richiesta;

    public TaskTransform(final String nomeRisorsa, final int disponibilita, final int richiesta) {
        this.nomeRisorsa = nomeRisorsa;
        this.disponibilita = disponibilita;
        this.richiesta = richiesta;
    }

    public String getNomeRisorsa() {
        return nomeRisorsa;
    }

    public int getDisponibilita() {
        return disponibilita;
    }

    public int getRichiesta() {
        return richiesta;
    }

    @Override
    public String toString() {
        return "TaskTransform [nomeRisorsa=" + nomeRisorsa + ", disponibilita=" + disponibilita + ", richiesta="
                + richiesta + "]";
    }
}
