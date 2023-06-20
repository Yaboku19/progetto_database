package futureodissey.view.impl.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import futureodissey.view.api.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class MainViewController {

    private View controller;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> deciderBox;

    @FXML
    void initialize() {
        assert deciderBox != null : "fx:id=\"deciderBox\" was not injected: check your FXML file 'main.fxml'.";
        deciderBox.getItems().addAll(View.getDeciderList());
        deciderBox.setOnAction(this::getchoice);

    }

    public void getchoice(ActionEvent event) {
        controller.changeStatus(deciderBox.getValue(), event);
    }

    public void setViewController(View controller) {
        this.controller = controller;
    }

}
