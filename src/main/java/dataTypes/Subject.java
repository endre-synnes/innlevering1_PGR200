package dataTypes;

public class Subject extends Tables{

    private String facilities;
    private int participants;
    private int lecturerId;

    public Subject(){
        this("test", "test", 0, 0);
    }

    public Subject(String name, String facilities, int participants, int lecturerId){
        super(name);
        setFacilities(facilities);
        setParticipants(participants);
        setLecturerId(lecturerId);
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    @Override
    public String toString() {
        return String.format("%-50s %-100s %-20s %-20s", super.toString(), getFacilities(), getParticipants(), getLecturerId());
    }
}
