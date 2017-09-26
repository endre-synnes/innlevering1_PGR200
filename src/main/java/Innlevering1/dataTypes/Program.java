package Innlevering1.dataTypes;

public class Program extends Tables{
    int id;
    String name;

    public Program() {
        this(0, "test");
    }

    public Program(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s %-10s %-50s", super.toString(), getId(), getName());

    }
}
