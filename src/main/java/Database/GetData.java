package Database;

import dataTypes.Lecturer;
import dataTypes.Tables;

import java.sql.*;
import java.util.ArrayList;

public class GetData {

    private ArrayList<Tables> tables;
    DatabaseConnection dbConn;
    Statement statement;
    ResultSet result;

    public GetData() throws Exception {
        tables = new ArrayList<Tables>();
        dbConn = new DatabaseConnection();
        dbConn.connect("root", "root");
    }


    // TODO fikse slik metoden er genrell
    public ArrayList getDataFromTable(String tableName) throws Exception{
        try {
            statement = dbConn.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM " + tableName);
            tables = new ArrayList<Tables>();
            while (result.next()){
                if (tableName == "forelesere"){
                    addLecturer();
                }
            }
            return tables;
        }
        catch (Exception e){
            throw e;
        }
    }


    public void addLecturer(){
        try {
            String name = result.getString("navn");
            Time start = result.getTime("startTid");
            Time end = result.getTime("slutTid");
            String comment = result.getString("kommentar");
            tables.add(new Lecturer(name, start, end, comment));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        GetData getData = new GetData();
        ArrayList tables = getData.getDataFromTable("forelesere");
        System.out.println(tables.get(0).toString());
    }


}
