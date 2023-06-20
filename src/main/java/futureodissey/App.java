package futureodissey;

import futureodissey.controller.api.Controller;
import futureodissey.controller.impl.ControllerImpl;

public class App {
    final static String username = "root";
    final static String password = "Emanuele2002!";
    final static String dbName = "futureodissey";

    public static void main(String[] args) {
        final Controller controller = new ControllerImpl(username, password, dbName);
        System.out.println("ciao");
        Launch.main(args);
    }

}
