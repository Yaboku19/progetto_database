package futureodissey.view.impl.javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox buttonBox;

    @FXML
    private Button playButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button tutorialButton;

    @FXML
    void play(ActionEvent event) {

    }

    @FXML
    void settings(ActionEvent event) {

    }

    @FXML
    void tutorial(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert buttonBox != null : "fx:id=\"buttonBox\" was not injected: check your FXML file 'main.fxml'.";
        assert playButton != null : "fx:id=\"playButton\" was not injected: check your FXML file 'main.fxml'.";
        assert settingButton != null : "fx:id=\"settingButton\" was not injected: check your FXML file 'main.fxml'.";
        assert tutorialButton != null : "fx:id=\"tutorialButton\" was not injected: check your FXML file 'main.fxml'.";

    }

}
