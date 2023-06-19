package futureodissey.controller.impl;

import futureodissey.controller.api.Controller;
import futureodissey.db.ConnectionProvider;

public class ControllerImpl implements Controller {
    private final ConnectionProvider connectionProvider;

    public ControllerImpl(final String username, final String password, final String dbName) {
        connectionProvider = new ConnectionProvider(username, password, dbName);
    }
    
}
