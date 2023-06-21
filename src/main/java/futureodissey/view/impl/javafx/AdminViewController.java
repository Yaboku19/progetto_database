package futureodissey.view.impl.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import futureodissey.view.api.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminViewController {

    private View controller;

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextArea fazioneList;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField addText;

    @FXML
    private ChoiceBox<String> deciderBox;

    @FXML
    private TextField addCaptanText;

    @FXML
    private Button removeButton;

    @FXML
    private TextField removeText;

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'admin.fxml'.";
        assert addCaptanText != null : "fx:id=\"addCaptanText\" was not injected: check your FXML file 'admin.fxml'.";
        assert addText != null : "fx:id=\"addText\" was not injected: check your FXML file 'admin.fxml'.";
        assert deciderBox != null : "fx:id=\"deciderBox\" was not injected: check your FXML file 'admin.fxml'.";
        assert fazioneList != null : "fx:id=\"fazioneList\" was not injected: check your FXML file 'admin.fxml'.";
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
        final String nation = addText.getText();
        final String captan = addText.getText();
        addText.clear();
        addCaptanText.clear();
        deciderBox.getItems().removeAll(View.getDeciderList());
        View.addDeciderList(nation);
        deciderBox.getItems().addAll(View.getDeciderList());
        controller.fazione(nation, captan, true);
    }

    @FXML
    void removeNation(MouseEvent event) {
        final String nation = removeText.getText();
        removeText.clear();
        deciderBox.getItems().removeAll(View.getDeciderList());
        View.removeDeciderList(nation);
        deciderBox.getItems().addAll(View.getDeciderList());
        controller.fazione(nation, "", false);
    }

}


