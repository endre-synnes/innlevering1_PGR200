package Database;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class PublishData {

    //private Connection connection;

    protected static ArrayList<String> linesInFile;
    protected static String[] columnNames, dataTypes;
    protected static String primaryKey, typeOfFile;
    //private DatabaseConnection connection;


    public PublishData() throws Exception {
    }

    public void createTable(String tableName, String primaryKey, String[] columnName, String[] dataType){
        try (Connection connection = new DatabaseConnection().getConnection()){
            System.out.println("Creating table...");
            Statement statement = connection.createStatement();
            String sqlSyntax = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
            for (int i = 0; i < columnName.length; i++) {
                sqlSyntax += columnName[i] + " " + dataType[i] + ",";
            }
            sqlSyntax += "PRIMARY KEY(" + primaryKey + "));";
            int result = statement.executeUpdate(sqlSyntax);
            //print(sqlSyntax);
            if (linesInFile != null)insertData(tableName, columnName);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void insertData(String tableName, String[] columnNames) {
        try (Connection connection = new DatabaseConnection().getConnection()){
            System.out.println("Populating table: " + tableName + "...");
            Statement statement = connection.createStatement();
            String sqlSyntax = "INSERT INTO " + tableName + " (";
            for (int i = 0; i < columnNames.length; i++) {
                String columnName = columnNames[i];
                sqlSyntax += columnName;
                if (i < columnNames.length -1) sqlSyntax += ", ";
            }
            sqlSyntax += ") VALUES ";
            String[][] lines = convertLinesInFile();
            for (int i = 0; i < lines.length; i++) {
                sqlSyntax += "(";
                for (int j = 0; j < lines[0].length; j++) {
                    sqlSyntax += "'" + lines[i][j] + "'";
                    if (j < lines[0].length - 1) sqlSyntax += ", ";
                }
                if (i < lines.length - 1) sqlSyntax += "), ";
            }
            sqlSyntax += ");";
            System.out.println(sqlSyntax);
            int result = statement.executeUpdate(sqlSyntax);
            System.out.println("Objects inserted to table: " + result);
        }catch (Exception e){
            e.getStackTrace();
        }
    }


    public String[][] convertLinesInFile(){
        String[][] linesAndCellsFromFile = new String[linesInFile.size()][columnNames.length];
        for (int i = 0; i < linesInFile.size(); i++) {
            linesAndCellsFromFile[i] = linesInFile.get(i).split(";");
        }
        return linesAndCellsFromFile;
    }


    public void print(String string){
        System.out.println(string);
    }





}
