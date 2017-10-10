package Innlevering1.Database;

import Innlevering1.CSVFileReader;
import Innlevering1.DataConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewDataPublisher {

    private DatabaseConnector dbConnector;

    public NewDataPublisher(DatabaseConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    //TODO: Ferdigstille metode
    public String insertDataToTable(DataConverter convertedFile){
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(stringBuilding(convertedFile))){

            int index = 0;
            if (convertedFile.checkForAutoIncrementInTable()) index = 1;

            for (int i = 0; i < convertedFile.getLinesAndColumnsFromFile().length; i++) {
                for (int j = 0; j < convertedFile.getLinesAndColumnsFromFile()[i].length; j++) {
                    statement.setString(index++, convertedFile.getLinesAndColumnsFromFile()[i][j]);
                }
            }

            int linesInserted = statement.executeUpdate();
            System.out.println("is inserted");
            return "Successfully inserted " + linesInserted + " rows to table";
        } catch (SQLException e){
            return SQLExceptionHandler.sqlErrorCode(e.getErrorCode());
        }
    }

    //TODO: Opprette string til insertDataToTable metoden
    public String stringBuilding(DataConverter converter){
        StringBuilder sqlString = new StringBuilder();
        sqlString.append("INSERT INTO ");
        sqlString.append(converter.getTableName());
        sqlString.append("(");
        int columnCount = converter.getColumnNames().length;
        for (int i = 0; i < columnCount; i++) {
            sqlString.append(converter.getColumnNames()[i]);
            if (i+1 < columnCount){
                sqlString.append(",");
            }
        }
        sqlString.append(")\nVALUES\n(");
        for (int i = 0; i < converter.getLinesAndColumnsFromFile().length; i++) {
            for (int j = 0; j < converter.getLinesAndColumnsFromFile()[i].length - 1; j++) {
                sqlString.append("?" + ", ");
            }
            sqlString.append("?" + "),\n(");
        }
        System.out.println(sqlString.toString().substring(0, sqlString.length() - 3));
        return sqlString.toString();
    }




    public static void main(String[] args) {

        try {
            CSVFileReader CSVFileReader = new CSVFileReader();
            DataConverter dataConverter = CSVFileReader.read("lecturer");


            DatabaseConnector dbConnector = new DatabaseConnector("DatabaseProperties.properties");

            DataPublisher oldDbPublisher = new DataPublisher(dbConnector);
            NewDataPublisher newDataPublisher = new NewDataPublisher(dbConnector);

            oldDbPublisher.createTable(dataConverter);
            newDataPublisher.insertDataToTable(dataConverter);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
