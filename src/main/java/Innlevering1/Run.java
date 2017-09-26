package Innlevering1;

import Innlevering1.Database.DataPublisher;
import Innlevering1.Database.DatabaseReader;

import java.util.Scanner;

public class Run {
    Scanner scanner = new Scanner(System.in);
    String input;
    DatabaseReader reader = new DatabaseReader();




    public Run(){

    }

    public void run() throws Exception {
        boolean running = true;
        while (running) {
            System.out.println(printCommands());
            //System.out.print("Enter your command: ");
            input = scanner.nextLine();
            runCases(input);
            if (input.equals("exit"))break;
            System.out.println("\n");

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


        }
    }


    public StringBuilder printCommands(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 60; i++) { builder.append("#"); }
        //TODO Gjør så alle filene blir lagt inn automatisk og ikke spør bruker om filnanvn
        builder.append(String.format("\n%-10s %-50s\n", "command:", "Description:"));
        builder.append(String.format("%-10s %-50s\n", "1", "Read file and publish to server."));
        builder.append(String.format("%-10s %-50s\n", "2", "Get tables in database"));
        builder.append(String.format("%-10s %-50s\n", "3", "Get everything from one table"));
        builder.append(String.format("%-10s %-50s\n", "4", "Get all lines from a table that has column equal to your input value."));
        builder.append(String.format("%-10s %-50s\n", "5", "Get all lines that have column value greater or less then your input value."));
        builder.append(String.format("%-10s %-50s\n", "6", "Count rows in a table."));
        builder.append(String.format("%-10s %-50s\n", "7", "Get metadata from table."));
        builder.append(String.format("%-10s %-50s\n", "exit", "Exit program"));
        builder.append("Press the command value and hit 'Enter'!\n");
        return builder;
    }



    private void readFileAndPublish(){
        System.out.println("\nEnter name of file:");
        CSVFileReader CSVFileReader = new CSVFileReader();
        input = scanner.nextLine();
        Table table = CSVFileReader.read(input);
        DataPublisher dataPublisher = new DataPublisher();
        System.out.println(dataPublisher.createTable(table));
    }

    private void getAllTables(){
        System.out.println("\nPrinting all tables in database:");
        System.out.println(reader.getAllTables());
    }

    private void getAllFromOneTable() {
        System.out.println("\nEnter name of the table you want to print: ");
        System.out.println(reader.getAllFromOneTable(scanner.nextLine()) + "\n");
    }

    private void getLinesWithParameter(){
        System.out.println("\nEnter name of table, column name and your parameter (press enter for each element):");
        System.out.println(reader.getLinesThatHasOneParameter(scanner.nextLine(), scanner.nextLine(), scanner.nextLine()) + "\n");
    }

    private void getLinesWithValuesGreaterOrLessThen() {
        System.out.println("\nEnter name of table, column name, greater/less and integer value");
        System.out.println(reader.getLinesWithValuesGreaterOrLessThen(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextInt()) + "\n");
    }

    private void countRows(){
        System.out.println("\nEnter name of the table you want to count rows: ");
        System.out.println(reader.countRowsInTable(scanner.nextLine()));
    }


    private void getMetaDataFromTable() {
        System.out.println("\nEnter name of the table you want to get metadata from: ");
        System.out.println(reader.getMetaDataFromTable(scanner.nextLine()));
    }




    public static void main(String[] args) throws Exception {
        Run run = new Run();
        run.run();
    }

}
