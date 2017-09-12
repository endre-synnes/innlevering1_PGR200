package dataTypes;

public class Subject extends Tables{

    private String id, name;
    private int participants;

    public Subject(){
        this("test", "test", 0);
    }

    public Subject(String id, String name, int participants) {
        setId(id);
        setName(name);
        setParticipants(participants);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-20s", getId(), getName(), getParticipants());
    }
}
