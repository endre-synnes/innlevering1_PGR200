import Database.PublishData;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateTable extends ReadFile{


    @Override
    public void read(String filename) {
        super.read(filename);
    }

    public void createTable(){
        String typeOfFile = getTypeOfFile();
        String[] columnName = getColumnNames();
        String[] dataType = getDataTypes();
        String primaryKey = getPrimaryKey();

        try {
            Connection connection = connect("root", "root");
            Statement statement = connection.createStatement();
            StringBuilder sqlSyntax = new StringBuilder("CREATE TABLE " + typeOfFile + " (");
            for (int i = 0; i < columnName.length; i++) {
                sqlSyntax.append(columnName[i]).append(" ").append(dataType[i]).append(",");
            }
            sqlSyntax.append("PRIMARY KEY(").append(primaryKey).append("));");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        CreateTable c = new CreateTable();
        c.read("subject");
    }


}
