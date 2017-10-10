package Innlevering1.Database;

import Innlevering1.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataPublisher {
    private DatabaseConnector dbConnector;

    public DataPublisher(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Creating a table or overwrites it if the table already exists.
     * @param tableFromFile
     * @return String explaining if i succeeded.
     */
    public String createTable(Table tableFromFile) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")){
            statement.execute("DROP TABLE IF EXISTS " + tableFromFile.getTableName());
            String sqlSyntax = "CREATE TABLE " + tableFromFile.getTableName() + " (";
            for (int i = 0; i < tableFromFile.getColumnNames().length; i++) {
                sqlSyntax += tableFromFile.getColumnNames()[i] + " "
                        + tableFromFile.getDataTypes()[i] + ",";
            }
            sqlSyntax += "PRIMARY KEY(" + tableFromFile.getPrimaryKey() + "));";

            statement.executeUpdate(sqlSyntax);
            return "Successfully created table!";
        } catch (SQLException e) {
            return SQLExceptionHandler.sqlErrorCode(e.getErrorCode());
        }


    }

    /**
     * Inserting data into a table if it exist
     * @param convertedFile
     * @return String explaining if i succeeded.
     */
    //TODO få denne til å funke
    public String insertData(Table convertedFile) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(stringBuilding(convertedFile))){

            int index = 1;
            for (int i = 0; i < convertedFile.getLinesAndColumnsFromFile().length; i++) {
                for (int j = 0; j < convertedFile.getLinesAndColumnsFromFile()[i].length; j++) {
                    statement.setString(index++, convertedFile.getLinesAndColumnsFromFile()[i][j]);
                }
            }

            int linesInserted = statement.executeUpdate();
            return "Successfully inserted " + linesInserted + " rows to table";
        } catch (SQLException e){
            return SQLExceptionHandler.sqlErrorCode(e.getErrorCode());
        }
    }

    //TODO: Opprette string til insertDataToTable metoden
    private String stringBuilding(Table converter){
        StringBuilder sqlString = new StringBuilder();
        sqlString.append("INSERT INTO ");
        sqlString.append(converter.getTableName());
        sqlString.append("(");
        int startColumn = 0;
        if (converter.checkForAutoIncrementInTable()) startColumn = 1;
        int columnCount = converter.getColumnNames().length;

        for (int i = startColumn; i < columnCount; i++) {
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
        return sqlString.toString().substring(0, sqlString.length() - 3);
    }


}
