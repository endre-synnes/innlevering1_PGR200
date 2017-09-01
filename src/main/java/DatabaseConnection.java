import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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


    public StringBuilder printProfessor() throws Exception{
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM foreleser");
            StringBuilder string = new StringBuilder();
            while (result.next()){
                string.append(result.getString("navn"));
                string.append(String.valueOf(result.getTime("startTid")));
                string.append(String.valueOf(result.getTime("slutTid")));
                string.append(result.getString("kommentar"));
            }
            return string;
        }
        catch (Exception e){
            throw e;
        }

    }





}