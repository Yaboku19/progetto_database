package futureodissey.view.api;

import java.util.ArrayList;
import java.util.List;

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
        deciderList.remove(value);
    }

    void changeStatus(String value, ActionEvent event);
}
