package Innlevering1.Database;

import Innlevering1.CSVFileReader;
import Innlevering1.DataConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseReaderTest {
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
    public void countNumberOfRowsInTable() throws Exception {
        //Read file
        CSVFileReader fileReader = new CSVFileReader();
        DataConverter convertedFile = fileReader.read("testFiles/roomTest");
        DataPublisher publisher = new DataPublisher(dbConnector);
        publisher.createTable(convertedFile);
        publisher.insertData(convertedFile);

        String dbReader = new DatabaseReader(dbConnector).countRowsInTable("testFiles/roomTest");
    }
}