package Innlevering1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileReaderTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void ableToReadPrimaryKeyCorrectly() throws Exception {
        FileReader reader = new FileReader();
        TableObjectFromFile tableObjectFromFile = new TableObjectFromFile();
        tableObjectFromFile = reader.createTableObject("subject", tableObjectFromFile);
        String primaryKey = tableObjectFromFile.getPrimaryKey();
        assertEquals("id", primaryKey);
    }
}