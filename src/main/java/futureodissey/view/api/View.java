package futureodissey.view.api;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

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

    List<Pair<String, String>> getAllFazioni();
}
