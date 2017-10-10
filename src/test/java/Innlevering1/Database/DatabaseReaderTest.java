package Innlevering1.Database;

import Innlevering1.FileReader;
import Innlevering1.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        FileReader fileReader = new FileReader();
        Table table = new Table();
        table = fileReader.createTableObject("testFiles/roomTest", table);
        DataPublisher publisher = new DataPublisher(dbConnector);
        publisher.createTable(table);
        publisher.insertData(table);

        String dbReader = new DatabaseReader(dbConnector).countRowsInTable("testFiles/roomTest");
    }
}