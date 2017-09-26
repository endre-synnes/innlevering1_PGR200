package Innlevering1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVFileReader {
    private ArrayList<String> linesInFile;
    private String[] columnNames, dataTypes;
    private String primaryKey, tableName;
    private Table table;

    public CSVFileReader() {}

    public Table read(String filename) {
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
            table = new Table(linesInFile, primaryKey, tableName, dataTypes, columnNames);
            return table;
        }
        catch (Exception e){
            System.out.println("No such file!");
            return null;
        }
    }

}
