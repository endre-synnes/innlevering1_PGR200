//package Innlevering1.Database;
//
//
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class CheckDatabase {
//
//    public CheckDatabase(String databaseName){
//
//    }
//
//
//
//
//
//    public boolean tableExists(String tableName){
//        try {
//            DatabaseMetaData dbm = connection.getMetaData();
//            ResultSet tables = dbm.getTables(null, null, tableName, null);
//            if (tables.next()){
//                return true;
//            }
//            return false;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//    }
//
//}
