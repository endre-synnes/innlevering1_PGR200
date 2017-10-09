package Innlevering1.Database;

import Innlevering1.CSVFileReader;
import Innlevering1.DataConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseConnectorTest {

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
        assertNotNull(dbConnector.getConnection());
    }

}