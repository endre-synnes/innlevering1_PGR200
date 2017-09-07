package dataTypes;

import java.sql.Time;
import java.util.Date;

public class Semester extends Tables {

    private String name;
    private Date start, end;

    public Semester() {
        this("test", "0000-00-00", "0000-00-00");
    }

    public Semester(String name, String start, String end) {
        setName(name);
        setStart(start);
        setEnd(end);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(String start) {
        try{
            this.start = Time.valueOf(start);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(String end) {
        try {
            this.end = Time.valueOf(end);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("%s %-20s %-20s %-20S", super.toString(), getName(), getStart(), getEnd());
    }
}
