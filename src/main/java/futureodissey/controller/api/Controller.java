package futureodissey.controller.api;

import java.util.List;
import futureodissey.model.impl.rowtype.Fazione;

public interface Controller {
    void fazione(String nomeFazione, String NomeCapitano, boolean isAdd);

    List<Fazione> getAllFazioni();
}
