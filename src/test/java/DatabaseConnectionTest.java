import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        assertTrue(db.connect("root", "root"));
    }


    @Test (expected = Exception.class)
    public void failToConnectToDatabase() throws Exception {
        assertTrue(db.connect("tull", "ball"));
    }

    @Test
    public void getContentFromTableForelesere() throws Exception {
        StringBuilder s = db.printProfessor();
        assertTrue(s != null);
    }
}