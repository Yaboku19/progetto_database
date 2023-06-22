package futureodissey.view.api;

import java.util.ArrayList;
import java.util.List;

import futureodissey.model.api.rowtype.RowType;
import futureodissey.model.impl.rowtype.Disponibilita;
import futureodissey.model.impl.rowtype.Fazione;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public interface View {
    static final List<String> deciderList = new ArrayList<>();

    void start(Stage stage);

    static List<String> getDeciderList() {
        return deciderList;
    }

    static void addDeciderList(String value) {
        if(!deciderList.contains(value)) {
            deciderList.add(value);
        }
    }

    static void removeDeciderList(String value) {
        if (! "admin".equals(value)) {
            deciderList.remove(value);
        }
        
    }

    void changeStatus(String value, ActionEvent event);

    void fazione(String nomeFazione, String NomeCapitano, boolean isAdd);

    List<Fazione> getAllFazioni();

    String info(int code);

    List<Disponibilita> getAllRisorseDisponibili();

    List<String> getAllNomeInsediamento(String nomeFazione);
}
