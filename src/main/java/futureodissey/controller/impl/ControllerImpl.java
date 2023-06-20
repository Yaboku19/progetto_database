package futureodissey.controller.impl;

import futureodissey.controller.api.Controller;
import futureodissey.db.ConnectionProvider;
import futureodissey.view.api.View;

public class ControllerImpl implements Controller {
    private final ConnectionProvider connectionProvider;
    private final View view;

    public ControllerImpl(final String username, final String password, final String dbName, View view) {
        connectionProvider = new ConnectionProvider(username, password, dbName);
        this.view = view;
    }
    
}
