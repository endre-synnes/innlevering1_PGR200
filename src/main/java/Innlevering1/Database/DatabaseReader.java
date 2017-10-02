package Innlevering1.Database;

import java.sql.*;

public class DatabaseReader{
    private DatabaseConnector dbConnector;

    public DatabaseReader(DatabaseConnector dbConnector){
        this.dbConnector = dbConnector;
    }


    public String getAllTables(){
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")){
            ResultSet result = statement.executeQuery("SHOW TABLES");
            return buildString(result);
        }catch (SQLException e){
                 return "no tables in Database";
        }
    }


    public String getAllFromOneTable(String tableName) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")){
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);
            return buildString(result);
        }
        catch (SQLException e){
            return "No table with that name!";
        }
    }


    public String getLinesThatHasOneParameter(String tableName, String columnName, String parameter){
        try (Connection connection = dbConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("")){
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName
            + " WHERE " + columnName + " LIKE '" + parameter + "';");
            return buildString(result);
        }
        catch (SQLException e){
            return ("No table with that name or column name");
        }
    }

    public String getLinesWithValuesGreaterOrLessThen(String tableName, String columnName, String greaterOrLess, int value){
        try(Connection connection = dbConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("")){
            String sqlSyntax = "SELECT * FROM " + tableName + " WHERE " + columnName;
            if (greaterOrLess.equals("greater")){
                sqlSyntax += " > " + value + ";";

            }
            else if (greaterOrLess.equals("less")){
                sqlSyntax += " < " + value + ";";

            }
            else return "Enter valid parameter (greater or less)";
            ResultSet result = statement.executeQuery(sqlSyntax);
            return buildString(result);


        }catch (SQLException e){
            return "No table with that name or column name!";
        }
    }
    public String countRowsInTable(String tableName) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")) {
            String sqlSyntax = "Select count(*) as rows from " + tableName;
            return buildString(statement.executeQuery(sqlSyntax));

        }catch (SQLException e){
            return "Could not count table, wrong table name";
        }
    }

    public StringBuilder getMetaDataFromTable(String tableName){
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")){
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = result.getMetaData();
            StringBuilder resultString = new StringBuilder();
            resultString.append("----------------------------------------\n");
            resultString.append(String.format("%-15s %-15s %-15s\n", "Name", "Size", "Datatype"));
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                resultString.append(String.format("%-15s %-15s %-15s\n",
                        metaData.getColumnName(i + 1),
                        metaData.getColumnDisplaySize(i + 1),
                        metaData.getColumnTypeName(i + 1)));
            }
            resultString.append("----------------------------------------");
            return resultString;
        }catch (SQLException e){
            return new StringBuilder().append("Could not read table");
        }
    }

    public boolean tableExist(String tableName){
        try (Connection connection = dbConnector.getConnection()) {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet tables = dbMetaData.getTables(null, null, tableName, null);

            return tables.next();

        } catch (SQLException e){
            return false;
        }
    }


    private String buildString(ResultSet result){
        try {
            StringBuilder stringResult = new StringBuilder();
            stringResult.append(getColumnNamesFromTable(result));
            int columnCount = result.getMetaData().getColumnCount();
            while (result.next()){
                stringResult.append("\n");
                for (int i = 0; i < columnCount; i++) {
                    stringResult.append(String.format("%-30s", result.getString(i + 1)));
                }
            }
            stringResult.append("\n");
            if (stringResult.length() == 0) return "No lines";
            return stringResult.toString();
        } catch (Exception e){
            return "Error while creating print!";
        }
    }


    private String getColumnNamesFromTable(ResultSet result){
        try {
            ResultSetMetaData columnNames = result.getMetaData();
            int columnCount = columnNames.getColumnCount();
            StringBuilder stringColumnNames = new StringBuilder();
            for (int i = 0; i < columnCount; i++) {
                stringColumnNames.append(String.format("%-30s", columnNames.getColumnName(i+1)));
            }
            stringColumnNames.append("\n");
            for (int i = 0; i < 80; i++) { stringColumnNames.append("-"); }
            return stringColumnNames.toString();


        }catch (SQLException e){
            return "Could not read column names";
        }
    }

}
