package dataTypes;

public class Tables {
    private String name;

    public Tables(){
        this("test");
    }

    public Tables(String name){
        setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
