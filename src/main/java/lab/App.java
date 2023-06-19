package lab;
import lab.db.ConnectionProvider;
import lab.db.tables.StudentsTable;
import lab.model.Student;
import lab.utils.Utils;

public class App {
    final static String username = "root";
    final static String password = "Emanuele2002!";
    final static String dbName = "futureodissey";
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static StudentsTable studentsTable = new StudentsTable(connectionProvider.getMySQLConnection());

    final static Student student1 = new Student(1, "Giacomo", "Cavalieri", Utils.buildDate(11, 10, 1998));
    final Student student2 = new Student(2, "Tommaso", "Cavalieri");

    public static void main(String[] args) {
        System.out.println("Hello, JDBC!");
        System.out.println(connectionProvider);
        System.out.println(studentsTable.save(student1));
        System.out.println(studentsTable.findByPrimaryKey(1));

    }

}
