import java.util.Scanner;

public class Run {

    public void readFiles() throws Exception {
        System.out.println("Enter name of file:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ReadFile readFile = new ReadFile();
        readFile.read(input);
    }


    public static void main(String[] args) throws Exception {
        Run run = new Run();
        run.readFiles();
    }

}
