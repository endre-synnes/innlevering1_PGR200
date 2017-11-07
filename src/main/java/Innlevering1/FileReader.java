package Innlevering1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public FileReader() {}

    /**
     * Reads a file, then create an object of that file witch is then returned.
     * @param filename
     * @return converted file
     */
    public TableObjectFromFile createTableObject(String filename, TableObjectFromFile tableObjectFromFile)
        throws FileNotFoundException, NullPointerException{
        try {
            ArrayList<String> file = readFile(filename);
            ArrayList data = new ArrayList<>();
            tableObjectFromFile.setTableName(file.get(0));
            tableObjectFromFile.setColumnNames(file.get(1).split(";"));
            tableObjectFromFile.setDataTypes(file.get(2).split(";"));
            tableObjectFromFile.setPrimaryKey(file.get(3));
            file.stream()
                    .skip(4)
                    .forEach(data::add);
            tableObjectFromFile.setLinesColumnsFromFile(data);
            return tableObjectFromFile;
        } catch(NullPointerException nullExc){
            throw new NullPointerException("No file with that name!");
        }
    }

    private ArrayList<String> readFile(String filename) throws NullPointerException{
        try(Scanner reader = new Scanner(new java.io.FileReader("docs/files/" + filename+".csv"))) {
            ArrayList list = new ArrayList();
            while (reader.hasNext()) list.add(reader.nextLine());
            return list;
        } catch (Exception e){
            return null;
        }

    }
}
