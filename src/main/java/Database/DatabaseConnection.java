package Database;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection implements DatabaseInterface{
    private static String hostName;
    private static String dbName;
    private static String userName;
    private static String password;
    private static int port;
    private Properties prop;

    public DatabaseConnection(){
        this("DatabaseProperties.properties");
    }


    public DatabaseConnection(String properties){
        try {
            prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(properties);
            prop.load(fileInputStream);
            hostName = prop.getProperty("hostName");
            dbName = prop.getProperty("dbName");
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");
            port = Integer.parseInt(prop.getProperty("port"));
            //System.out.println("print: " + userName);
        }catch (Exception e){
            e.getStackTrace();
        }
    }


    @Override
    public Connection getConnection() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName(hostName);
            dataSource.setDatabaseName(dbName);
            dataSource.setUser(userName);
            dataSource.setPassword(password);
            dataSource.setPort(port);
            return dataSource.getConnection();
        }catch (SQLException dbException){
            dbException.getErrorCode();
            return null;
        }
    }

//    public static Connection connect() throws Exception {
//        try {
//            Scanner userInput = new Scanner(System.in);
//            System.out.print("Enter user name: ");
//            String userName = userInput.nextLine();
//            System.out.print("\nEnter user password: ");
//            String password = userInput.nextLine();
//
//            dataSource = new MysqlDataSource();
//            dataSource.setServerName("localhost");
//            dataSource.setUser(userName);
//            dataSource.setPassword(password);
//            connection = dataSource.getConnection();
//
//            System.out.print("\nEnter the name of your database: ");
//            String dbName = userInput.nextLine().trim();
//            while (!createDB(dbName)) {
//                System.out.print("\nEnter the name of your database: ");
//                dbName = userInput.nextLine();
//            }
//
//            System.out.println("setting connection");
//            connection = dataSource.getConnection();
//            Statement useDB = connection.createStatement();
//            useDB.executeQuery("USE " + dbName);
//            return connection;
//        }
//        catch (Exception e){
//            throw e;
//        }
//    }
//
//
//    public static boolean createDB(String dbName){
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("CREATE DATABASE " + dbName);
//            preparedStatement.executeUpdate();
//            System.out.println("Database " + dbName + " successfully created!");
//            return true;
//        }catch (SQLException e){
//            e.getErrorCode();
//            System.out.println("Database already exist");
//            return false;
//        }
//    }



    public void close() throws SQLException {

    }

    public static void main(String[] args) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        if (db.getConnection() != null){
            System.out.println("Successful connection!");
        }
        else System.out.println("no connection!");
    }
}