package futureodissey.controller.api;

import java.util.List;

import javafx.util.Pair;

public interface Controller {
    void fazione(String nomeFazione, String NomeCapitano, boolean isAdd);

    List<Pair<String, String>> getAllFazioni();
}
