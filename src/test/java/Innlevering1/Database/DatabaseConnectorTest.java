package Innlevering1.Database;

import Innlevering1.CSVFileReader;
import Innlevering1.DataConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseConnectorTest {

    private Connection db;
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
    public void getConnectionToDatabase() throws Exception {
        //db = new DatabaseConnector().getConnection();
        assertNotNull(dbConnector.getConnection());
    }

    @Test
    public void ableToReadPrimaryKeyCorrectly() throws Exception {
        CSVFileReader reader = new CSVFileReader();
        DataConverter convertedFile = reader.read("subject");
        String primaryKey = convertedFile.getPrimaryKey();
        assertEquals("id", primaryKey);
    }

    @Test
    public void ableToCreateTable() throws Exception {
        CSVFileReader reader = new CSVFileReader();
        DataConverter convertedFile = reader.read("subject");
        DataPublisher publisher = new DataPublisher(dbConnector);
        assertEquals("Successfully created table!", publisher.createTable(convertedFile));
    }

    @Test
    public void doesTableExistInDatabaseAfterFileIsReadAndPushedToServer() throws Exception {

        String tableName = "subject";

        //Reading file
        CSVFileReader fileReader = new CSVFileReader();
        DataConverter convertedFile = fileReader.read(tableName);

        //Publishing table
        DataPublisher publisher = new DataPublisher(dbConnector);
        publisher.createTable(convertedFile);

        //Checking if table exist
        DatabaseReader databaseReader = new DatabaseReader(dbConnector);
        assertTrue(databaseReader.tableExist(tableName));
    }
}