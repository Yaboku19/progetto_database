package lab.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import futureodissey.db.ConnectionProvider;
import futureodissey.db.impl.TaskTable;
import futureodissey.model.impl.rowtype.Task;

class TaskTableTests {
    final static String username = "root";
    final static String password = "Emanuele2002!";
    final static String dbName = "futureodissey";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static TaskTable taskTable = new TaskTable(connectionProvider.getMySQLConnection());

    final Task task1 = new Task("bla", 3, 2, Optional.ofNullable("acca"));
    final Task task2 = new Task("cia", 4, 5);

    @BeforeEach
    void setUp() throws Exception {
        taskTable.dropTable();
        taskTable.createTable();
    }

    @AfterEach
    void tearDown() throws Exception {
        taskTable.dropTable();
    }

    @Test
    void creationAndDropTest() {
        assertTrue(taskTable.dropTable());
        assertFalse(taskTable.dropTable());
        assertTrue(taskTable.createTable());
        assertFalse(taskTable.createTable());
    }
    
    /*@Test
    void saveTest() {
        assertTrue(studentsTable.save(student1));
        assertFalse(studentsTable.save(student1));
        assertTrue(studentsTable.save(student2));
    }
    
    @Test
    void updateTest() {
        assertFalse(studentsTable.update(student1));
        studentsTable.save(student2);
        final Student updatedStudent2 = new Student(2, "Tommaso", "Cavalieri", Utils.buildDate(11, 10, 1998));
        assertTrue(studentsTable.update(updatedStudent2));
        final Optional<Student> foundStudent = studentsTable.findByPrimaryKey(updatedStudent2.getId());
        assertFalse(foundStudent.isEmpty());
        assertEquals(updatedStudent2.getBirthday(), foundStudent.get().getBirthday());
    }

    @Test
    void deleteTest() {
        studentsTable.save(student1);
        assertTrue(studentsTable.delete(student1.getId()));
        assertFalse(studentsTable.delete(student1.getId()));
        assertTrue(studentsTable.findByPrimaryKey(student1.getId()).isEmpty());
    }

    @Test
    void findByPrimaryKeyTest() {
        studentsTable.save(student1);
        studentsTable.save(student2);
        assertEquals(student1, studentsTable.findByPrimaryKey(student1.getId()).orElse(null));
        assertEquals(student2, studentsTable.findByPrimaryKey(student2.getId()).orElse(null));
    }

    @Test
    void findAllTest() {
        studentsTable.save(student1);
        studentsTable.save(student2);
        assertIterableEquals(
            List.of(student1, student2),
            studentsTable.findAll()
        );
    }
    
    @Test
    void findByBirthdayTest() {
        final Student student3 = new Student(3, "Pietro", "Rossi", Utils.buildDate(11, 10, 1998));
        studentsTable.save(student1);
        studentsTable.save(student2);
        studentsTable.save(student3);
        assertIterableEquals(
            List.of(student1, student3),
            studentsTable.findByBirthday(Utils.buildDate(11, 10, 1998).get())
        );
    }*/
}
