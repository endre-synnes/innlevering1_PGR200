package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection{

    private Connection connection;

    public DatabaseConnection(){ }

    public boolean connect(String username, String password) throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgr200", username, password);
            return true;
        }
        catch (Exception e){
            throw e;
        }
    }

    public Connection getConnection(){
        return connection;
    }





}