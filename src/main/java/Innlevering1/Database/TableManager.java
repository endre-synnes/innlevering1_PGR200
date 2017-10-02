package Innlevering1.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {
    private DatabaseConnector connector;

    public TableManager(DatabaseConnector connector){
        this.connector = connector;
    }

    public String dropTable(String tableName){
        try (Connection connection = connector.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE " + tableName);
            return "Table " + tableName + " is dropped!";
        }catch (SQLException e){
            System.out.println(e.getErrorCode());
            return "Could not drop table";
        }
    }


}
