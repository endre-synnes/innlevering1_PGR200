package Innlevering1.Database;

import Innlevering1.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataPublisher {

    public DataPublisher() {

    }

    public String createTable(Table tableFromFile) {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("")){
            System.out.println("Creating table...");
            statement.execute("DROP TABLE IF EXISTS " + tableFromFile.getTableName());
            String sqlSyntax = "CREATE TABLE " + tableFromFile.getTableName() + " (";
            for (int i = 0; i < tableFromFile.getColumnNames().length; i++) {
                sqlSyntax += tableFromFile.getColumnNames()[i] + " "
                        + tableFromFile.getDataTypes()[i] + ",";
            }
            sqlSyntax += "PRIMARY KEY(" + tableFromFile.getPrimaryKey() + "));";
            //System.out.println(sqlSyntax);

            statement.executeUpdate(sqlSyntax);
            if (tableFromFile.getLinesAndColumnsFromFile() != null)insertData(tableFromFile);
            return "Successfully created table";
        } catch (Exception e) {
            return "Could not create table, check if format in file is invalid!";
        }


    }

    public String insertData(Table tableFromFile) throws SQLException {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("")){

            ResultSet doesTableExist = connection.getMetaData().getTables(null, null, tableFromFile.getTableName(), null);
            if (!doesTableExist.next()){
                return "No table with that name!";
            }

            System.out.println("Populating table: " + tableFromFile.getTableName() + "...");
            String sqlSyntax = "INSERT INTO " + tableFromFile.getTableName() + " (";
            for (int i = 0; i < tableFromFile.getColumnNames().length; i++) {
                sqlSyntax += tableFromFile.getColumnNames()[i];
                if (i < tableFromFile.getColumnNames().length -1) sqlSyntax += ", ";
            }
            sqlSyntax += ") VALUES ";
            String[][] lines = tableFromFile.getLinesAndColumnsFromFile();
            for (int i = 0; i < lines.length; i++) {
                sqlSyntax += "(";
                for (int j = 0; j < lines[0].length; j++) {
                    sqlSyntax += "'" + lines[i][j] + "'";
                    if (j < lines[0].length - 1) sqlSyntax += ", ";
                }
                if (i < lines.length - 1) sqlSyntax += "), ";
            }
            sqlSyntax += ");";
            //System.out.println(sqlSyntax);
            int result = statement.executeUpdate(sqlSyntax);
            return "Objects inserted to table: " + result;
        }catch (Exception e){
            return "Could not insert data. Could be duplicates!";
        }
    }




}
