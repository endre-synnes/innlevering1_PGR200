import Database.PublishData;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile extends PublishData{

    public ReadFile() throws Exception {}

    public void read(String filename){
        try {
            System.out.println("Reading file...");
            linesInFile = new ArrayList<>();
            Scanner reader = new Scanner(new FileReader(filename+".csv"));
            typeOfFile = reader.nextLine()
                    .replaceAll(";", "")
                    .toLowerCase()
                    .trim();
            columnNames = reader.nextLine().split(";");
            dataTypes = reader.nextLine().split(";");
            primaryKey = reader.nextLine()
                    .replaceAll(";", "")
                    .toLowerCase()
                    .trim();

            while (reader.hasNext()){ linesInFile.add(reader.nextLine()); }
            reader.close();
            createTable();
        }
        catch (Exception e){
            e.getMessage();
        }
    }


    public String[] getColumnNames() { return columnNames; }

    public String[] getDataTypes() { return dataTypes; }

    public String getPrimaryKey() { return primaryKey; }

    public ArrayList<String> getLinesInFile() { return linesInFile; }

    public String getTypeOfFile() {return typeOfFile;}

    public void createTable() {
        super.createTable(typeOfFile, primaryKey, columnNames, dataTypes);
    }

}
