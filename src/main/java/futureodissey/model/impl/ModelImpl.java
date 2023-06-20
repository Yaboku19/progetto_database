package futureodissey.model.impl;

import futureodissey.db.ConnectionProvider;
import futureodissey.model.api.Model;

public class ModelImpl implements Model{
    private final ConnectionProvider connectionProvider;

    public ModelImpl(final String username, final String password, final String dbName) {
        connectionProvider = new ConnectionProvider(username, password, dbName);
    }
}
