package Innlevering1.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseConnectionTest {

    DatabaseConnection db;
    @Before
    public void setUp() throws Exception {
        db = new DatabaseConnection();
    }

    @After
    public void tearDown() throws Exception {
        db = null;
    }

    @Test
    public void getConnectionToDatabase() throws Exception {
        //assertNotEquals(null, db.connect("root", "root"));
    }


    @Test (expected = Exception.class)
    public void failToConnectToDatabase() throws Exception {
        //assertEquals(null, db.connect("tull", "ball"));
    }

    @Test
    public void getContentFromTableForelesere() throws Exception {
    }
}