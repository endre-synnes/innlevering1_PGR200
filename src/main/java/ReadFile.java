import Database.PublishData;
import dataTypes.Tables;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile extends PublishData{
    private String[] columnNames, dataTypes;
    private String primaryKey, typeOfFile;
    ArrayList<String> linesInFile;

    public ReadFile(){ }

    //TODO: Denne metoden skal lese filen og kalle på en klasse som oppretter riktige objekter, avhenger av filnavn
    public void read(String filename){
        try {
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

    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        readFile.read("subject");
    }
}
