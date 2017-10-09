package Innlevering1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CSVFileReaderTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void ableToReadPrimaryKeyCorrectly() throws Exception {
        CSVFileReader reader = new CSVFileReader();
        DataConverter convertedFile = reader.read("subject");
        String primaryKey = convertedFile.getPrimaryKey();
        assertEquals("id", primaryKey);
    }
}