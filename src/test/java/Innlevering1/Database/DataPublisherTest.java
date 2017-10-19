package Innlevering1.Database;

import Innlevering1.FileReader;
import Innlevering1.TableObjectFromDB;
import Innlevering1.TableObjectFromFile;
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
        TableObjectFromFile tableObjectFromFile = new TableObjectFromFile();
        tableObjectFromFile = reader.createTableObject("testFiles/subjectTest", tableObjectFromFile);
        DataPublisher publisher = new DataPublisher(dbConnector);
        publisher.createTableInDatabase(tableObjectFromFile);
    }

    @Test
    public void doesTableExistInDatabaseAfterFileIsReadAndPushedToServer() throws Exception {

        String tableName = "subjectTest";

        //Reading file
        FileReader fileReader = new FileReader();
        TableObjectFromFile tableObjectFromFile = new TableObjectFromFile();
        tableObjectFromFile = fileReader.createTableObject("testFiles/" + tableName, tableObjectFromFile);

        //Publishing tableObjectFromFile
        DataPublisher publisher = new DataPublisher(dbConnector);
        publisher.createTableInDatabase(tableObjectFromFile);

        //Checking if tableObjectFromFile exist
        DatabaseReader databaseReader = new DatabaseReader(dbConnector);
        TableObjectFromDB dbTable = new TableObjectFromDB();
        dbTable = databaseReader.getAllTables(dbTable);

        boolean tableInDb = false;
        for (String table: dbTable.getTablesInDB()) {
            if (table.equals("subjectTest")) tableInDb = true;
        }

        assertTrue(tableInDb);
    }

}