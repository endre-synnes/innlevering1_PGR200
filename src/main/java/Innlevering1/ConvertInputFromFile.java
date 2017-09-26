package Innlevering1;

import Innlevering1.dataTypes.Subject;
import Innlevering1.dataTypes.Tables;

import java.util.ArrayList;

public class ConvertInputFromFile {
    private ArrayList<String> stringInput;
    private ArrayList<Tables> objectOutput = new ArrayList<Tables>();

    public ConvertInputFromFile(String typeOfObjectsToCreate, ArrayList<String> stringInput){
        this.stringInput = stringInput;
        checkType(typeOfObjectsToCreate);
    }


    private void checkType(String tpeOfObjectsToCreate){
        if (tpeOfObjectsToCreate.equals("subject")) {
            createSubjects();
        }
    }

    private ArrayList<Tables> createSubjects(){
        for (String s: stringInput) {
            try {
                String[] line = s.split(";");
                String id = line[0];
                String name = line[1];
                int participants = Integer.parseInt(line[2]);
                objectOutput.add(new Subject(id, name, participants));
            }catch (Exception e){
                System.out.println("Invalid datatype");
                return null;
            }
        }
        return objectOutput;
    }

    public ArrayList<Tables> getObjectOutput() {
        return objectOutput;
    }
}
