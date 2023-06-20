package futureodissey.view.impl.javafx;

import futureodissey.view.api.View;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Represents the {@link View} controller.
 */
public class ViewImpl implements View {
    private Stage stage;
    private Parent root;
    private MainViewController mainViewController;

    private static final Logger LOGGER = Logger.getLogger("ViewControllerLog");
    private static final double SETTINGS_MIN_HEIGHT = 430;
    private static final double SETTINGS_MIN_WIDTH = 600;

    public void start(final Stage stage) {
        this.stage = stage;
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("layout/main.fxml"));
        System.out.println(ClassLoader.getSystemResource("layout/main.fxml"));
        try {
            System.out.println("000000000");
            root = loader.load();
            System.out.println("111111111111");
            mainViewController = loader.getController();
            System.out.println("waaaa");
            //controller = new BasicGameEngine(this);
            //controller.setViewResources();
            mainViewController.setViewController(this);
            final Scene mainViewScene = new Scene(root);
            stage.setTitle("Tank-Battle");
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

}
