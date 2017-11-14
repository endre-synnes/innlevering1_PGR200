package Innlevering1.Database;

import Innlevering1.FileReader;
import Innlevering1.TableObjectFromDB;
import Innlevering1.TableObjectFromFile;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseReaderTest {
    private static DatabaseConnector dbConnector;
    private static FileReader fileReader;
    private static TableObjectFromDB tableObjectFromDB;
    private static TableObjectFromFile tableObjectFromFile;
    private static DataPublisher publisher;
    private static DatabaseReader dbReader;

    @BeforeClass
    public static void setUp() throws Exception{
        dbConnector = new DatabaseConnector("TestDatabaseProperties.properties");
        fileReader = new FileReader();
        tableObjectFromFile = new TableObjectFromFile();
        publisher = new DataPublisher(dbConnector);
        dbReader = new DatabaseReader(dbConnector);
        tableObjectFromFile = fileReader.createTableObject("testFiles/roomTest", tableObjectFromFile);
        publisher.createTableInDatabase(tableObjectFromFile);
        publisher.insertDataToDatabase(tableObjectFromFile);
    }

    @Before
    public void setup() throws Exception {

        tableObjectFromFile = new TableObjectFromFile();
        tableObjectFromDB = new TableObjectFromDB();
    }

    @After
    public void tearDown() throws Exception {
        dbConnector = null;
    }


    @Test
    public void countNumberOfRowsInTable() throws Exception {
        tableObjectFromDB = dbReader.getAllFromOneTable("roomTest", tableObjectFromDB);
        assertEquals(7, tableObjectFromDB.getContentOfTable().size());
    }


    @Test
    public void getAllTables() throws Exception {
        tableObjectFromDB = dbReader.getAllTables(tableObjectFromDB);
        assertEquals(1, tableObjectFromDB.getContentOfTable().size());
    }

    @Test
    public void getAllFromOneTable() throws Exception {
        tableObjectFromDB = dbReader.getMetaDataFromTable("roomtest", tableObjectFromDB);
        assertEquals(3, tableObjectFromDB.getColumnName().length);
        assertEquals("Id", tableObjectFromDB.getColumnName()[0]);
    }

    @Test
    public void getLinesWithParameter() throws Exception {
        tableObjectFromDB = dbReader.getLinesThatHasOneParameter("roomtest",
                "capacity", "45", tableObjectFromDB);
        assertEquals(2, tableObjectFromDB.getContentOfTable().size());
    }
}