package Innlevering1.dataTypes;

public class LecturerAndSubject extends Tables {

    private int lecturerId;
    private String subjectId;

    public LecturerAndSubject() {
        this(0, "test");
    }

    public LecturerAndSubject(int lecturerId, String subjectId) {
        setLecturerId(lecturerId);
        setSubjectId(subjectId);
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return String.format("%s %-10s %-50s", super.toString(), getLecturerId(), getSubjectId());

    }
}
