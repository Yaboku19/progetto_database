package futureodissey.controller.api;

import java.util.List;

import futureodissey.model.impl.rowtype.Disponibilita;
import futureodissey.model.impl.rowtype.Fazione;
import futureodissey.model.impl.rowtype.Task;

public interface Controller {
    void fazione(String nomeFazione, String NomeCapitano, boolean isAdd);

    List<Fazione> getAllFazioni();

    String getInfo(int code);

    List<Disponibilita> getAllRisorseDisponibiliFromNomeFazione(String nomeFazione);

    List<String> getAllInsediamenti(String nomeFazione);

    List<String> getNomePianetaNomeRisorsaFree();

    List<String> getInsediamentoRisorsaAltruiFromFazione(String nomeFazione);

    List<Task> getTaskFromNomeFazione (String nomeFazione);
}
