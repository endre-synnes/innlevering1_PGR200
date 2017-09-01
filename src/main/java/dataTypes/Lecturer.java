package dataTypes;

import java.sql.Time;

public class Lecturer extends Tables {

    private Time start;
    private Time end;
    private String comment;

    public Lecturer() {
        this("test", Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), "Test");
    }

    public Lecturer(String name, Time start, Time end, String comment) {
        super(name);
        setStart(start);
        setEnd(end);
        setComment(comment);
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return String.format("%-30s %-15s %-15s %-20s", super.toString(), getStart(), getEnd(), getComment());
    }
}
