//package Innlevering1.Database;
//
//import Innlevering1.FileReader;
//import Innlevering1.Table;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class NewDataPublisher {
//
//    private DatabaseConnector dbConnector;
//
//    public NewDataPublisher(DatabaseConnector dbConnector){
//        this.dbConnector = dbConnector;
//    }
//
//    public String insertDataToTable(Table convertedFile){
//        try (Connection connection = dbConnector.getConnection();
//             PreparedStatement statement = connection.prepareStatement(stringBuilding(convertedFile))){
//
//            int index = 1;
//            for (int i = 0; i < convertedFile.getLinesAndColumnsFromFile().length; i++) {
//                for (int j = 0; j < convertedFile.getLinesAndColumnsFromFile()[i].length; j++) {
//                    statement.setString(index++, convertedFile.getLinesAndColumnsFromFile()[i][j]);
//                }
//            }
//
//            int linesInserted = statement.executeUpdate();
//            return "Successfully inserted " + linesInserted + " rows to table";
//        } catch (SQLException e){
//            return SQLExceptionHandler.sqlErrorCode(e.getErrorCode());
//        }
//    }
//
//    private String stringBuilding(Table converter){
//        StringBuilder sqlString = new StringBuilder();
//        sqlString.append("INSERT INTO ");
//        sqlString.append(converter.getTableName());
//        sqlString.append("(");
//        int startColumn = 0;
//        if (converter.checkForAutoIncrementInTable()) startColumn = 1;
//        int columnCount = converter.getColumnNames().length;
//
//        for (int i = startColumn; i < columnCount; i++) {
//            sqlString.append(converter.getColumnNames()[i]);
//            if (i+1 < columnCount){
//                sqlString.append(",");
//            }
//        }
//
//        sqlString.append(")\nVALUES\n(");
//        for (int i = 0; i < converter.getLinesAndColumnsFromFile().length; i++) {
//            for (int j = 0; j < converter.getLinesAndColumnsFromFile()[i].length - 1; j++) {
//                sqlString.append("?" + ", ");
//            }
//            sqlString.append("?" + "),\n(");
//        }
//        return sqlString.toString().substring(0, sqlString.length() - 3);
//    }
//
//
//
//
//    public static void main(String[] args) {
//
//        try {
//            FileReader FileReader = new FileReader();
//            Table dataConverter = FileReader.read("lecturer");
//
//
//            DatabaseConnector dbConnector = new DatabaseConnector("DatabaseProperties.properties");
//
//            DataPublisher oldDbPublisher = new DataPublisher(dbConnector);
//            NewDataPublisher newDataPublisher = new NewDataPublisher(dbConnector);
//
//            oldDbPublisher.createTableInDatabase(dataConverter);
//            System.out.println(newDataPublisher.insertDataToTable(dataConverter));
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
