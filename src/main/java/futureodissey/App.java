package futureodissey;

import futureodissey.db.ConnectionProvider;
import futureodissey.db.impl.InsediamentoTable;
import futureodissey.db.impl.TaskTable;
import futureodissey.model.impl.rowtype.Insediamento;

public class App {
    private static ConnectionProvider connectionProvider;
    private static final String username = "root";
    private static final String password = "Emanuele2002!";
    private static final String dbName = "futureodissey";
    public static void main(String[] args) {
        Launch.main(args);
        /*connectionProvider = new ConnectionProvider(username, password, dbName);
        TaskTable taskTable = new TaskTable(connectionProvider.getMySQLConnection());
        taskTable.createTable();
        taskTable.dropTable();*/
    }

}
