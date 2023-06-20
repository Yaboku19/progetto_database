package futureodissey.view.impl.javafx;

import futureodissey.view.api.View;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Represents the {@link View} controller.
 */
public class ViewImpl implements View {
    private Stage stage;
    private Parent root;
    private MainViewController mainViewController;
    private AdminViewController adminViewController;

    private static final Logger LOGGER = Logger.getLogger("ViewControllerLog");
    private static final double SETTINGS_MIN_HEIGHT = 430;
    private static final double SETTINGS_MIN_WIDTH = 600;

    public void start(final Stage stage) {
        this.stage = stage;
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("layout/main.fxml"));
        View.addDeciderList("admin");
        try {
            root = loader.load();
            mainViewController = loader.getController();
            //controller = new BasicGameEngine(this);
            //controller.setViewResources();
            mainViewController.setViewController(this);
            final Scene mainViewScene = new Scene(root);
            stage.setTitle("Future-Odissey");
            stage.setScene(mainViewScene);
            stage.setMinHeight(SETTINGS_MIN_HEIGHT);
            stage.setMinWidth(SETTINGS_MIN_WIDTH);
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                exit();
            });
            stage.show();
        } catch (IOException e) {
            //LOGGER.log(Level.SEVERE, "Main scene load error during start.");
            e.printStackTrace();
        }
        
    }

    private void exit() {
        System.exit(0);
    }

    @Override
    public void changeStatus(String value, ActionEvent event) {
        if (value == "admin") {
            setAdminController(converterFromEvent(event));
        } else {
            setMainController(converterFromEvent(event));
        }
    }

    private void setMainController(Stage stage) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("layout/main.fxml"));
            final Scene main = new Scene(fxmlLoader.load());
            mainViewController = (MainViewController) fxmlLoader.getController();
            mainViewController.setViewController(this);
            stage.setScene(main);
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAdminController(Stage stage) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("layout/admin.fxml"));
            final Scene admin = new Scene(fxmlLoader.load());
            adminViewController = (AdminViewController) fxmlLoader.getController();
            adminViewController.setViewController(this);
            stage.setScene(admin);
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
