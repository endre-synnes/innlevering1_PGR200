package Innlevering1.Database;

import Innlevering1.TableObjectFromDB;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseReader{
    private DatabaseConnector dbConnector;

    public DatabaseReader(DatabaseConnector dbConnector){
        this.dbConnector = dbConnector;
    }


    public String getAllTables() throws SQLException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")) {
            ResultSet result = statement.executeQuery("SHOW TABLES");
            return buildString(result);
        }
    }

    /**
     * @param tableName
     * @return A formatted string of all table names.
     */
    public TableObjectFromDB getAllFromOneTable(String tableName, TableObjectFromDB tableObjectFromDB) throws Exception{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")) {
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);
            return setContentOfTableFromDB(result, tableObjectFromDB);
        }catch (Exception e){

            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param tableName
     * @param columnName
     * @param parameter
     * @return A formatted string containing Lines in a table that has one parameter.
     */
    public TableObjectFromDB getLinesThatHasOneParameter(String tableName, String columnName,
                                              String parameter, TableObjectFromDB tableObjectFromDB) throws SQLException{
        try (Connection connection = dbConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("")){
            ResultSet result = statement.executeQuery("SELECT * FROM " + tableName
            + " WHERE " + columnName + " LIKE '" + parameter + "';");
            return setContentOfTableFromDB(result, tableObjectFromDB);
        }
    }

    /**
     *
     * @param tableName
     * @param columnName
     * @param greaterOrLess
     * @param value
     * @return Return a formatted string containing lines with an int value greater og less then value you put in.
     */
    public String getLinesWithValuesGreaterOrLessThen(String tableName,
                                                      String columnName, String greaterOrLess,
                                                      String value) throws SQLException{
        try(Connection connection = dbConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("")){
            String sqlSyntax = "SELECT * FROM " + tableName + " WHERE " + columnName;
            if (greaterOrLess.equals("greater")){
                sqlSyntax += " > " + value + ";";

            }
            else if (greaterOrLess.equals("less")){
                sqlSyntax += " < " + value + ";";

            }
            else return "Enter valid data and parameter (greater or less)";
            ResultSet result = statement.executeQuery(sqlSyntax);
            return buildString(result);
        }
    }

    /**
     *
     * @param tableName
     * @return Returns a string with the number of rows in a table.
     */
    public String countRowsInTable(String tableName) throws SQLException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("")) {
            String sqlSyntax = "Select count(*) as rows from " + tableName;
            return buildString(statement.executeQuery(sqlSyntax));
        }
    }

    /**
     *
     * @param tableName
     * @return Returns a formatted string containing metadata from one table.
     */
    public TableObjectFromDB getMetaDataFromTable(String tableName, TableObjectFromDB tableObjectFromDB)
            throws SQLException, NullPointerException{
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName)){
            ResultSet result = statement.executeQuery();
            setTableMetadata(result, tableObjectFromDB);
            return tableObjectFromDB;

        }catch (SQLException e){
            throw new SQLException();
        }catch (NullPointerException e){
            throw new NullPointerException("Table objectFrom file was empty");
        }
    }

    /**
     *
     * @param tableName
     * @return True of False if table exist in the database or not.
     */
    public boolean tableExist(String tableName) throws SQLException{
        try (Connection connection = dbConnector.getConnection()) {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet tables = dbMetaData.getTables(null, null, tableName, null);
            return tables.next();
        }
    }

    /**
     * Building a string in the right formatting.
     * @param result
     * @return String
     */
    private String buildString(ResultSet result) throws SQLException{
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


    private TableObjectFromDB setContentOfTableFromDB(ResultSet result, TableObjectFromDB tableObjectFromDB)
        throws NullPointerException, SQLException{
        try {
            int columnCount = result.getMetaData().getColumnCount();
            ArrayList<String[]> content = new ArrayList<>();

            while (result.next()){
                String[] line = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    line[i] = result.getString(i + 1);
                }
                content.add(line);
            }
            tableObjectFromDB.setContentOfTable(content);
            return setTableMetadata(result, tableObjectFromDB);

        }catch (NullPointerException e){
            throw new NullPointerException();
        } catch (SQLException e){
            throw new SQLException();
        }

    }

    private TableObjectFromDB setTableMetadata(ResultSet result, TableObjectFromDB tableObjectFromDB)
            throws NullPointerException, SQLException {
        try {
            ResultSetMetaData metadata = result.getMetaData();
            int columnCount = metadata.getColumnCount();

            String[] columnNames = new String[columnCount];
            String[] columnDisplaySize = new String[columnCount];
            String[] columnTypeName = new String[columnCount];

            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metadata.getColumnName(i + 1);
                columnDisplaySize[i] = String.valueOf(metadata.getColumnDisplaySize(i + 1));
                columnTypeName[i] = metadata.getColumnTypeName(i + 1);
            }
            tableObjectFromDB.setColumnName(columnNames);
            tableObjectFromDB.setColumnDisplaySize(columnDisplaySize);
            tableObjectFromDB.setColumnTypeName(columnTypeName);
            return tableObjectFromDB;
        }catch (NullPointerException e){
            throw new NullPointerException();
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    /**
     *
     * @param result
     * @return Returns column names in a table.
     */
    private String getColumnNamesFromTable(ResultSet result) throws SQLException{
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
            return "Could not createTableObject column names";
        }
    }

}
