package Innlevering1.Database;

import Innlevering1.DataConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataPublisher {
    private DatabaseConnector dbConnector;

    public DataPublisher(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Creating a table or overwrites it if the table already exists.
     * @param dataConverterFromFile
     * @return String explaining if i succeeded.
     */
    public String createTable(DataConverter dataConverterFromFile) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")){
            statement.execute("DROP TABLE IF EXISTS " + dataConverterFromFile.getTableName());
            String sqlSyntax = "CREATE TABLE " + dataConverterFromFile.getTableName() + " (";
            for (int i = 0; i < dataConverterFromFile.getColumnNames().length; i++) {
                sqlSyntax += dataConverterFromFile.getColumnNames()[i] + " "
                        + dataConverterFromFile.getDataTypes()[i] + ",";
            }
            sqlSyntax += "PRIMARY KEY(" + dataConverterFromFile.getPrimaryKey() + "));";

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
    public String insertData(DataConverter convertedFile) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")){

            ResultSet doesTableExist = connection.getMetaData()
                    .getTables(null, null, convertedFile.getTableName(), null);
            if (!doesTableExist.next()) return "No table with that name!";

            int index = 0;
            if (convertedFile.checkForAutoIncrementInTable()) index = 1;

            String sqlSyntax = "INSERT INTO " + convertedFile.getTableName() + " (";
            for (int i = index; i < convertedFile.getColumnNames().length; i++) {
                sqlSyntax += convertedFile.getColumnNames()[i];
                if (i < convertedFile.getColumnNames().length -1) sqlSyntax += ", ";

            }
            sqlSyntax += ") VALUES ";
            String[][] lines = convertedFile.getLinesAndColumnsFromFile();
            for (int i = 0; i < lines.length; i++) {
                sqlSyntax += "(";
                for (int j = 0; j < lines[0].length; j++) {
                    sqlSyntax += "'" + lines[i][j] + "'";
                    if (j < lines[0].length - 1) sqlSyntax += ", ";
                }
                if (i < lines.length - 1) sqlSyntax += "), ";
            }
            sqlSyntax += ");";
            int result = statement.executeUpdate(sqlSyntax);
            return "Objects inserted to table: " + result;
        }catch (SQLException e){
            System.out.println(e.getErrorCode());
            return SQLExceptionHandler.sqlErrorCode(e.getErrorCode());
        }
    }

}
