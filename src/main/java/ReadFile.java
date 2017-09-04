import dataTypes.Tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFile {
    String csvSplitBy;


    public ReadFile(){
        csvSplitBy = ";";
    }


    public ArrayList<Tables> read(String filename){
        try {
            ArrayList<Tables> table = new ArrayList<Tables>();
            BufferedReader reader = new BufferedReader(new FileReader(filename+".csv"));
            String typeOfFile = reader.readLine()
                    .replaceAll(";", "")
                    .trim()
                    .toLowerCase();
            System.out.println(typeOfFile);
            return table;
        }
        catch (Exception e){
            e.getMessage();
            return null;
        }

    }

    public static void main(String[] args) {
        ReadFile readFile =  new ReadFile();
        readFile.read("Subject");
    }

}
