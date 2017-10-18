package Innlevering1;

import java.util.ArrayList;

public class TableObjectFromDB{
    private String tableName;
    private String[] columnName, columnDisplaySize, columnTypeName, tablesInDB;
    private ArrayList<String[]> contentOfTable;

    public TableObjectFromDB(){
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public String[] getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(String[] columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    public ArrayList<String[]> getContentOfTable() {
        return contentOfTable;
    }

    public void setContentOfTable(ArrayList<String[]> contentOfTable) {
        this.contentOfTable = contentOfTable;

    }

    public String[] getTablesInDB() {
        return tablesInDB;
    }

    public void setTablesInDB(String[] tablesInDB) {
        this.tablesInDB = tablesInDB;
    }

    public String[] getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String[] columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String readContent(){
        StringBuilder string = new StringBuilder();
        for (String s: columnName) {
            string.append(s).append(" ");
        }
        string.append("\n");
        for (String[] line: contentOfTable) {
            for (String s: line) {
                string.append(s).append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }

    public String readMetadata(){
        StringBuilder string = new StringBuilder();
        string.append(String.format("%-15s%-15s%-15s\n", "Name", "Size", "Data type"));
        for (int i = 0; i < 40; i++) { string.append("-"); }
        string.append("\n");
        for (int i = 0; i < columnName.length; i++) {
            string.append(String.format("%-15s%-15s%-15s\n",
                    columnName[i], columnTypeName[i],
                    columnDisplaySize[i]));
        }
        return string.toString();
    }

}
