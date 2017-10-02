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
    private MysqlDataSource dataSource;


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
            dropDatabaseIfExist(getConnection());
        }catch (Exception e){
            System.out.println("Could not read property file correctly!");
        }
    }


    @Override
    public Connection getConnection() {
        try {
            dataSource = new MysqlDataSource();
            dataSource.setServerName(hostName);
            dataSource.setUser(userName);
            dataSource.setPassword(password);
            dataSource.setPort(port);
            createAndSetDatabase(dataSource.getConnection());
            return dataSource.getConnection();
        }catch (SQLException dbException){
            System.out.println("Could not connect to server, please check the DB Name file or your username/password!");
            System.exit(0);
            return null;
        }
    }

    private void createAndSetDatabase(Connection connection){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            dataSource.setDatabaseName(dbName);
        }catch (SQLException e){
            System.out.println("Could not set or create Database");
        }
    }

    private void dropDatabaseIfExist (Connection connection){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE IF EXISTS " + dbName);
        }catch (SQLException e){
            System.out.println("Failed to drop Database");
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
}