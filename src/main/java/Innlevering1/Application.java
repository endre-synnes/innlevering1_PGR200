package Innlevering1;

import Innlevering1.Database.*;

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
     *
     */
    public void run() throws Exception {
        boolean running = true;
        while (running) {
            System.out.println(printCommands());
            String input = scanner.nextLine();
            runCases(input);
        }
    }

    public void runCases(String userInput){
        switch (userInput){
            case "1" : readFileAndPublish();
                break;
            case "2" : getAllTables();
                break;
            case "3" : getAllFromOneTable();
                break;
            case "4" : getLinesWithParameter();
                break;
            case "5" : getLinesWithValuesGreaterOrLessThen();
                break;
            case "6" : countRows();
                break;
            case "7" : getMetaDataFromTable();
                break;
            case "8" : connectTables();
                break;
            case "9" : dropTable();
                break;
            case "10" : readAllExampleFiles();
                break;
            case "exit" : System.exit(0);
                break;
            default:
                System.out.println("Unknown command");


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
        builder.append(String.format("%-10s %-50s\n", "5", "Get all lines that have column value greater or less then your input value."));
        builder.append(String.format("%-10s %-50s\n", "6", "Count rows in a table."));
        builder.append(String.format("%-10s %-50s\n", "7", "Get metadata from table."));
        builder.append(String.format("%-10s %-50s\n", "8", "Connect all tables (Only after all tables are in the database!)"));
        builder.append(String.format("%-10s %-50s\n", "9", "Drop Table (Needed to rewrite tables after they are connected)"));
        builder.append(String.format("%-10s %-50s\n", "10", "Read all example files"));
        builder.append(String.format("%-10s %-50s\n", "exit", "Exit program"));
        for (int i = 0; i < 60; i++) { builder.append("#"); }
        builder.append("\nPress the command value and hit 'Enter':");
        return builder;
    }



    private void readFileAndPublish(){
        System.out.println("\nEnter name of file:");
        CSVFileReader CSVFileReader = new CSVFileReader();
        DataConverter dataConverter = CSVFileReader.read(scanner.nextLine());
        System.out.println(publisher.createTable(dataConverter));
        System.out.println(publisher.insertData(dataConverter) + "\n");
    }

    private void getAllTables(){
        System.out.println("\nPrinting all tables in database:");
        System.out.println(DBReader.getAllTables());
    }

    private void getAllFromOneTable() {
        System.out.println("\nEnter name of the table you want to print: ");
        System.out.println(DBReader.getAllFromOneTable(scanner.nextLine()) + "\n");
    }

    private void getLinesWithParameter(){
        System.out.println("\nEnter name of table, column name and your parameter (press enter for each element):");
        System.out.println(DBReader.getLinesThatHasOneParameter(scanner.nextLine(), scanner.nextLine(), scanner.nextLine()) + "\n");
    }

    private void getLinesWithValuesGreaterOrLessThen() {
        System.out.println("\nEnter name of table, column name, greater/less and integer value");
        System.out.println(DBReader.getLinesWithValuesGreaterOrLessThen(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextInt()) + "\n");
    }

    private void countRows(){
        System.out.println("\nEnter name of the table you want to count rows: ");
        System.out.println(DBReader.countRowsInTable(scanner.nextLine()));
    }


    private void getMetaDataFromTable() {
        System.out.println("\nEnter name of the table you want to get metadata from: ");
        System.out.println(DBReader.getMetaDataFromTable(scanner.nextLine()));
    }


    private void connectTables(){
        System.out.println("\nConnecting all tables...");
        System.out.println(tableConnector.addConstraintToTwoTables("availability", "lecturer", "lecturerId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("lecturerandsubject", "lecturer", "lecturerId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("lecturerandsubject", "subject", "subjectId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("subjectandprogram", "program", "programId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("subjectandprogram", "subject", "subjectId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("subjectandroom", "room", "roomId", "id"));
        System.out.println(tableConnector.addConstraintToTwoTables("subjectandroom", "subject", "subjectId", "id"));
    }


    private void dropTable(){
        System.out.println("\nEnter name of the table you want to drop: ");
        System.out.println(tableManager.dropTable(scanner.nextLine()));
    }


    private void readAllExampleFiles(){
        CSVFileReader fileReader = new CSVFileReader();
        String[] fileNames = {"availability", "lecturer", "lecturerAndSubject",
                "program", "room", "semester", "subject", "subjectAndProgram",
                "SubjectAndRoom"};
        for (String fileName: fileNames) {
            DataConverter dataConverter = fileReader.read(fileName);
            publisher.createTable(dataConverter);
            publisher.insertData(dataConverter);
        }
    }




    public static void main(String[] args) throws Exception {
        Application application = new Application(new DatabaseConnector("DatabaseProperties.properties"));
        application.run();
    }

}