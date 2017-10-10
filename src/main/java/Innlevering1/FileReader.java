package Innlevering1;

import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private String[] columnNames, dataTypes;
    private String primaryKey, tableName;
    private Table table;

    public FileReader() {}

    /**
     * Reads a file, then create an object of that file witch is then returned.
     * @param filename
     * @return converted file
     */
    public Table read(String filename, Table table) {
        try {
            ArrayList<String> file = readFile(filename);
            ArrayList data = new ArrayList<>();
            //Scanner reader = new Scanner(new java.io.FileReader("docs/files/" + filename+".csv"));
            table.setTableName(file.get(0));
            table.setColumnNames(file.get(1).split(";"));
            table.setDataTypes(file.get(2).split(";"));
            table.setPrimaryKey(file.get(3));
            for (int i = 4; i < file.size(); i++) {
                data.add(file.get(i));
            }
            table.setLinesColumnsFromFile(data);


//            tableName = reader.nextLine();
//            columnNames = reader.nextLine().split(";");
//            dataTypes = reader.nextLine().split(";");
//            primaryKey = reader.nextLine();

            //while (reader.hasNext()) file.add(reader.nextLine());
            //reader.close();
            //table = new Table(file, primaryKey, tableName, dataTypes, columnNames);
            //if (!table.checkRightAmountOfColumns()) return null;
            return table;
        }
        catch (Exception e){
            System.out.println("No such file!");
            return null;
        }
    }

    private ArrayList<String> readFile(String filename){
        try(Scanner reader = new Scanner(new java.io.FileReader("docs/files/" + filename+".csv"))) {
            ArrayList list = new ArrayList();
            while (reader.hasNext()) list.add(reader.nextLine());
            return list;
        } catch (Exception e){
            return null;
        }

    }
}
