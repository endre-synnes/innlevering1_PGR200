package Innlevering1.Database;

import Innlevering1.FileReader;
import Innlevering1.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataPublisherTest {
    private DatabaseConnector dbConnector;


    @Before
    public void setUp() throws Exception {
        dbConnector = new DatabaseConnector("TestDatabaseProperties.properties");
    }

    @After
    public void tearDown() throws Exception {
        dbConnector = null;
    }

    @Test
    public void ableToCreateTable() throws Exception {
        FileReader reader = new FileReader();
        Table table = new Table();
        table = reader.read("testFiles/subjectTest", table);
        DataPublisher publisher = new DataPublisher(dbConnector);
        assertEquals("Successfully created table!", publisher.createTable(table));
    }

    @Test
    public void doesTableExistInDatabaseAfterFileIsReadAndPushedToServer() throws Exception {

        String tableName = "subject";

        //Reading file
        FileReader fileReader = new FileReader();
        Table table = new Table();
        table = fileReader.read(tableName, table);

        //Publishing table
        DataPublisher publisher = new DataPublisher(dbConnector);
        publisher.createTable(table);

        //Checking if table exist
        DatabaseReader databaseReader = new DatabaseReader(dbConnector);
        assertTrue(databaseReader.tableExist(tableName));
    }

}