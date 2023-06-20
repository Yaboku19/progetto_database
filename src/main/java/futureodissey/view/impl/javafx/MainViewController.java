package futureodissey.view.impl.javafx;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import futureodissey.view.api.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main menu {@link Scene} controller.
 */
public class MainViewController {

    private View viewController;
    private String tank1Resource;
    private String tank2Resource;
    private String mapResource;
    private static final Logger LOGGER = Logger.getLogger("MainViewControllerLog");

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
    void initialize() {

    }

    /**
     * Start a new game with the chosen settings, standard settings if not chosen.
     * @param event button click
     */
    @FXML
    void play(final ActionEvent event) {
        ;
    }

    /**
     * Sets the {@link Scene} to the Settings scene.
     * @param event button click
     */
    @FXML
    void settings(final ActionEvent event) {
        ;
    }

    /**
     * Sets the {@link Scene} to the tutorial scene.
     * @param event button click
     */
    @FXML
    void tutorial(final ActionEvent event) {
        ;
    }

    /**
     * Sets the {@link View} controller.
     * @param viewController the {@link View} controller
     */
    public void setViewController(final View viewController) {
        this.viewController = viewController;
    }

    /**
     * Sets the tanks and map resources.
     * @param tank1Resource first tank resource
     * @param tank2Resource second tank resource
     * @param mapResource map resource
     */
    public void setResource(final String tank1Resource, final String tank2Resource, final String mapResource) {
        this.tank1Resource = tank1Resource;
        this.tank2Resource = tank2Resource;
        this.mapResource = mapResource;
    }

    private void addKeyListener(final Scene gameScene) {
        ;
    }

    static Stage converterFromEvent(final ActionEvent event) {
        final Node node = (Node) event.getSource();
        final var converter = node.getScene().getWindow();
        if (!(converter instanceof Stage)) {
            throw new AssertionError("Unexpected type: " + converter);
        }
        return (Stage) converter;
    }
}
