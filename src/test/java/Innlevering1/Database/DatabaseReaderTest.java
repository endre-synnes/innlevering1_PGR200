package Innlevering1.Database;

import Innlevering1.FileReader;
import Innlevering1.TableObjectFromDB;
import Innlevering1.TableObjectFromFile;
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
        FileReader fileReader = new FileReader();
        TableObjectFromFile tableObjectFromFile = new TableObjectFromFile();

        //Publish file
        tableObjectFromFile = fileReader.createTableObject("testFiles/roomTest", tableObjectFromFile);
        DataPublisher publisher = new DataPublisher(dbConnector);
        publisher.createTableInDatabase(tableObjectFromFile);
        publisher.insertDataToDatabase(tableObjectFromFile);

        //Rad from DB
        TableObjectFromDB tableObjectFromDB = new TableObjectFromDB();
        DatabaseReader reader = new DatabaseReader(dbConnector);
        tableObjectFromDB = reader.getAllFromOneTable("roomTest", tableObjectFromDB);

        //Test answer
        assertEquals(7, tableObjectFromDB.getContentOfTable().size());
    }
}