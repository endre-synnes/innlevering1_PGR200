package Innlevering1.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableConnector {
    private DatabaseConnector connector;

    public TableConnector(DatabaseConnector connector){
        this.connector = connector;
    }


    public String addConstraintToTwoTables(
            String mainTable, String secondTable, String mainTableColumnName, String secondTableColumnName){

        try (Connection connection = connector.getConnection()){
            String constraintText = "fk_" + mainTable + "_" + mainTableColumnName + "_" + secondTableColumnName;
            Statement statement = connection.createStatement();
            statement.executeUpdate("ALTER TABLE `" + mainTable + "`" +
                    "  ADD CONSTRAINT `" + constraintText + "`" +
                    " FOREIGN KEY (`" + mainTableColumnName +"`)" +
                    " REFERENCES `" + secondTable +"` (`" + secondTableColumnName +"`);");
            return "successfully connected" + mainTable + " and " + secondTable;

        }catch (SQLException e){
            if (e.getErrorCode() == 1022) return "This constraint already exist!";
            if (e.getErrorCode() == 1146 || e.getErrorCode() == 1215 || e.getErrorCode() == 1072) return "Check your table or column name!";
            if (e.getErrorCode() == 1452) return "At least one row in the child table that references a non-existent row in the parent table";
            return "Failed to connect tables, error code: " + e.getErrorCode() + ". Could be wrong formatting!";

        }

    }

}
