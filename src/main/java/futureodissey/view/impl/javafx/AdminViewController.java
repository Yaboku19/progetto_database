package futureodissey.view.impl.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import futureodissey.view.api.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminViewController {

    private View controller;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField addText;

    @FXML
    private ChoiceBox<String> deciderBox;

    @FXML
    private Button removeButton;

    @FXML
    private TextField removeText;

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'admin.fxml'.";
        assert addText != null : "fx:id=\"addText\" was not injected: check your FXML file 'admin.fxml'.";
        assert deciderBox != null : "fx:id=\"deciderBox\" was not injected: check your FXML file 'admin.fxml'.";
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'admin.fxml'.";
        assert removeText != null : "fx:id=\"removeText\" was not injected: check your FXML file 'admin.fxml'.";

        deciderBox.getItems().addAll(View.getDeciderList());
        deciderBox.setOnAction(this::getchoice);
    }

    public void getchoice(ActionEvent event) {
        controller.changeStatus(deciderBox.getValue(), event);
    }

    public void setViewController(View controller) {
        this.controller = controller;
    }

    @FXML
    void addNation(MouseEvent event) {
        final String text = addText.getText();
        deciderBox.getItems().removeAll(View.getDeciderList());
        View.addDeciderList(text);
        deciderBox.getItems().addAll(View.getDeciderList());
    }

    @FXML
    void removeNation(MouseEvent event) {
        final String text = removeText.getText();
        deciderBox.getItems().removeAll(View.getDeciderList());
        View.removeDeciderList(text);
        deciderBox.getItems().addAll(View.getDeciderList());
    }

}


