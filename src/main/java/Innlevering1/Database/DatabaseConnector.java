package Innlevering1.Database;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnector implements DatabaseInterface{
    private static String hostName;
    private static String dbName;
    private static String userName;
    private static String password;
    private static int port;
    private Properties prop;

    public DatabaseConnector(){
        //this("DatabaseProperties.properties");
    }


    public DatabaseConnector(String properties){
        try {
            prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream(properties);
            prop.load(fileInputStream);
            hostName = prop.getProperty("hostName");
            dbName = prop.getProperty("dbName");
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");
            port = Integer.parseInt(prop.getProperty("port"));
        }catch (Exception e){
            //e.getStackTrace();
            System.out.println("Could not read property file correctly!");
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
            System.out.println("Could not connect to server, please check the property file or your username/password!");
            System.exit(0);
            return null;
        }
    }

    public void close() throws SQLException {
        userName = null;
        password = null;
        dbName = null;
        port = 0;
        hostName = null;
        prop = null;
    }

    public static void main(String[] args) throws Exception {
        DatabaseConnector db = new DatabaseConnector();
        if (db.getConnection() != null){
            System.out.println("Successful connection!");
        }
        else System.out.println("no connection!");
    }
}