package futureodissey;

import futureodissey.db.ConnectionProvider;
import futureodissey.db.impl.InsediamentoTable;
import futureodissey.model.impl.rowtype.Insediamento;
import javafx.util.Pair;

public class App {
    private static ConnectionProvider connectionProvider;
    private static final String username = "root";
    private static final String password = "Emanuele2002!";
    private static final String dbName = "futureodissey";
    public static void main(String[] args) {
        // Launch.main(args);
        connectionProvider = new ConnectionProvider(username, password, dbName);
        var insediamentoTable = new InsediamentoTable(connectionProvider.getMySQLConnection());
        insediamentoTable.dropTable();
        insediamentoTable.createTable();
        insediamentoTable.save(new Insediamento(username, password, dbName));
        insediamentoTable.save(new Insediamento(password, username, dbName));
        System.out.println(insediamentoTable.findAll());
        System.out.println(insediamentoTable.findByPrimaryKey(new Pair<String,String>(password, username)));
        insediamentoTable.delete(new Pair<String,String>(username, password));
        insediamentoTable.update(new Insediamento(password, username, dbName + username));
        System.out.println(insediamentoTable.findAll());

    }

}
