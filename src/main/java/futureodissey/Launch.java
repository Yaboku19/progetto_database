package futureodissey;

import futureodissey.view.api.View;
import futureodissey.view.impl.javafx.ViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launch extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        final View viewController = new ViewImpl();
        viewController.start(primaryStage);
    }

    public static void main(String [] args) {
        launch(args);
    }
    
}
