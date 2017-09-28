package Innlevering1;

import java.util.ArrayList;
import java.util.Scanner;

public class CSVFileReader {
    private ArrayList<String> linesInFile;
    private String[] columnNames, dataTypes;
    private String primaryKey, tableName;
    private DataConverter dataConverter;

    public CSVFileReader() {}

    public DataConverter read(String filename) {
        try {
            System.out.println("Reading file...");
            linesInFile = new ArrayList<>();
            Scanner reader = new Scanner(new java.io.FileReader(filename+".csv"));
            tableName = reader.nextLine();
            columnNames = reader.nextLine().split(";");
            dataTypes = reader.nextLine().split(";");
            primaryKey = reader.nextLine();

            while (reader.hasNext()) linesInFile.add(reader.nextLine());
            reader.close();
            dataConverter = new DataConverter(linesInFile, primaryKey, tableName, dataTypes, columnNames);
            //if (!dataConverter.checkRightAmountOfColumns()) return null;
            return dataConverter;
        }
        catch (Exception e){
            System.out.println("No such file!");
            return null;
        }
    }

}
