package futureodissey.controller.impl;

import futureodissey.controller.api.Controller;
import futureodissey.model.api.Model;
import futureodissey.model.impl.ModelImpl;
import futureodissey.view.api.View;

public class ControllerImpl implements Controller {
    private final View view;
    private final String username = "root";
    private final String password = "Emanuele2002!";
    private final String dbName = "futureodissey";
    private final Model model;

    public ControllerImpl(View view) {
        this.view = view;
        model = new ModelImpl(username, password, dbName);
    }
    
}
