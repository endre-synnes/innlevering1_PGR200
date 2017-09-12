package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable{

    private static Connection connection;

    public DatabaseConnection(){ }

    public static Connection connect(String username, String password) throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgr200", username, password);
            return connection;
        }
        catch (Exception e){
            throw e;
        }
    }


    public void close() throws SQLException {

    }
}