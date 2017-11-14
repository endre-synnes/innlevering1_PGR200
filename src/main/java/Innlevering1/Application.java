package Innlevering1;

import Innlevering1.Database.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private Scanner scanner = new Scanner(System.in);
    private DatabaseReader DBReader;
    private DataPublisher publisher;
    private TableConnector tableConnector;
    private TableManager tableManager;




    public Application(DatabaseConnector dbConnector){
        DBReader = new DatabaseReader(dbConnector);
        publisher = new DataPublisher(dbConnector);
        tableConnector = new TableConnector(dbConnector);
        tableManager = new TableManager(dbConnector);
    }

    /**
     * Run this method and it will keep the application running until you close it.
     */
    public void run() throws Exception {
        while (true) {
            System.out.println(printCommands());
            String input = scanner.nextLine();
            runCases(input);
        }
    }

    /**
     * All commands you can call.
     */
    private void runCases(String userInput){
        try {
            switch (userInput){
                case "1" : readFileAndPublish();
                    break;
                case "2" : getAllTables();
                    break;
                case "3" : getAllFromOneTable();
                    break;
                case "4" : getLinesWithParameter();
                    break;
                case "5" : countRows();
                    break;
                case "6" : getMetaDataFromTable();
                    break;
                case "7" : dropTable();
                    break;
                case "8" : readAllExampleFiles();
                    break;
                case "9" : getSubjectAndRoom();
                    break;
                case "exit" : System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }catch (SQLException se){
            System.out.println(SQLExceptionHandler.sqlErrorCode(se.getErrorCode()));
        }catch (NullPointerException empty){
            System.out.print(empty.getMessage());
            System.out.println(" Object not initialised.");
        }catch (FileNotFoundException noFile){
            System.out.println("File not Found.");
        }catch (IllegalArgumentException illegalArgument){
            System.out.println(illegalArgument.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Exception i Application");
        }

    }


    private StringBuilder printCommands(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 60; i++) { builder.append("#"); }
        builder.append(String.format("\n%-10s %-50s\n", "command:", "Description:"));
        builder.append(String.format("%-10s %-50s\n", "1", "Read file and publish to server."));
        builder.append(String.format("%-10s %-50s\n", "2", "Get tables in database"));
        builder.append(String.format("%-10s %-50s\n", "3", "Get everything from one table"));
        builder.append(String.format("%-10s %-50s\n", "4", "Get all lines from a table that has column equal to your input value."));
        builder.append(String.format("%-10s %-50s\n", "5", "Count rows in a table."));
        builder.append(String.format("%-10s %-50s\n", "6", "Get metadata from table."));
        builder.append(String.format("%-10s %-50s\n", "7", "Drop TableObjectFromFile (Needed to rewrite tables if you have connected tables)"));
        builder.append(String.format("%-10s %-50s\n", "8", "Read all example files and connect them."));
        builder.append(String.format("%-10s %-50s\n", "9", "Get subject that is connected to a room."));
        builder.append(String.format("%-10s %-50s\n", "exit", "Exit program"));
        for (int i = 0; i < 60; i++) { builder.append("#"); }
        builder.append("\nPress the command value and hit 'Enter':");
        return builder;
    }



    private void readFileAndPublish() throws SQLException, FileNotFoundException, NullPointerException{
        System.out.println("\nEnter name of file:");
        TableObjectFromFile tableObjectFromFile = new TableObjectFromFile();
        FileReader FileReader = new FileReader();
        tableObjectFromFile = FileReader.createTableObject(scanner.nextLine(), tableObjectFromFile);

        System.out.println(publisher.createTableInDatabase(tableObjectFromFile));
        System.out.println(publisher.insertDataToDatabase(tableObjectFromFile));

    }

    private void getAllTables() throws SQLException{
        System.out.println("\nPrinting all tables in database:");
        TableObjectFromDB dbTable = new TableObjectFromDB();
        dbTable = DBReader.getAllTables(dbTable);
        System.out.println(StringCreator.getContent(dbTable));

    }

    private void getAllFromOneTable() throws SQLException{
        TableObjectFromDB dbTable = new TableObjectFromDB();
        getAllTables();
        System.out.println("\nEnter name of the table you want to print: ");
        dbTable = DBReader.getAllFromOneTable(scanner.nextLine(), dbTable);
        System.out.println(StringCreator.getContent(dbTable));
    }

    private void getLinesWithParameter() throws SQLException{
        TableObjectFromDB dbTable = new TableObjectFromDB();
        getAllTables();
        System.out.println("Enter table name:");
        String tableName = scanner.nextLine();
        getColumnNames(tableName, dbTable);
        System.out.println("Enter column name:");
        String columnName = scanner.nextLine();
        System.out.println("Enter value: ");
        dbTable = DBReader.getLinesThatHasOneParameter(tableName, columnName, scanner.nextLine(), dbTable);
        System.out.println(StringCreator.getContent(dbTable));
    }

    private void getColumnNames(String tableName, TableObjectFromDB dbTable) throws SQLException{
        dbTable = DBReader.getAllFromOneTable(tableName, dbTable);
        System.out.println(StringCreator.getColumnNames(dbTable));
    }


    private void countRows() throws SQLException{
        getAllTables();
        System.out.println("\nEnter name of the table you want to count rows: ");
        TableObjectFromDB dbTable = new TableObjectFromDB();
        dbTable = DBReader.countRowsInTable(scanner.nextLine(), dbTable);
        System.out.println(StringCreator.getContent(dbTable));
    }


    private void getMetaDataFromTable() throws SQLException{
        getAllTables();
        System.out.println("\nEnter name of the table you want to get metadata from: ");
        TableObjectFromDB dbTable = new TableObjectFromDB();
        dbTable = DBReader.getMetaDataFromTable(scanner.nextLine(), dbTable);
        System.out.println(StringCreator.getMetaData(dbTable));
    }


    private void getSubjectAndRoom() throws Exception{
        TableObjectFromDB dbTable = new TableObjectFromDB();
        dbTable = DBReader.getTwoConnectedTables(dbTable);
        System.out.println(StringCreator.getContent(dbTable));
    }

    /**
     * Creates foreign key constraints for all example files.
     */
    private void connectTables() throws SQLException{
        System.out.println("\nConnecting all tables...");
        System.out.println(tableConnector.addConstraintToTwoTables("availability", "lecturer", "lecturerId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("lecturerandsubject", "lecturer", "lecturerId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("lecturerandsubject", "subject", "subjectId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("subjectandprogram", "program", "programId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("subjectandprogram", "subject", "subjectId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("subjectandroom", "room", "roomId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("subjectandroom", "subject", "subjectId", "id") + "\n");
    }


    private void dropTable() throws SQLException{
        getAllTables();
        System.out.println("\nEnter name of the table you want to drop: ");
        System.out.println(tableManager.dropTable(scanner.nextLine()) + "\n");
    }


    private void readAllExampleFiles() throws SQLException, FileNotFoundException{
        FileReader fileReader = new FileReader();
        String[] fileNames = {"availability", "lecturer", "lecturerAndSubject",
                "program", "room", "semester", "subject", "subjectAndProgram",
                "SubjectAndRoom"};
        for (String fileName: fileNames) {
            TableObjectFromFile tableObjectFromFile = new TableObjectFromFile();
            tableObjectFromFile = fileReader.createTableObject(fileName, tableObjectFromFile);
            publisher.createTableInDatabase(tableObjectFromFile);
            publisher.insertDataToDatabase(tableObjectFromFile);
        }
        System.out.println("All tables are created.\n");

        connectTables();
    }




    public static void main(String[] args) {
        try {
            Application application = new Application(new DatabaseConnector("DatabaseProperties.properties"));
            application.run();
        }catch (Exception e){
            System.out.println("Error while connecting to server, check properties file!");
        }

    }

}
