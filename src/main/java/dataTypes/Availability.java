package dataTypes;

public class Availability extends Tables{

    private int weekId, lecturerId;
    private boolean monday, tuesday, wednesday, thursday, friday;

    public Availability() {
    }

    public Availability(int weekId, int lecturerId, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday) {
        setWeekId(weekId);
        setLecturerId(lecturerId);
        setMonday(monday);
        setTuesday(tuesday);
        setWednesday(wednesday);
        setThursday(thursday);
        setFriday(friday);
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s", super.toString(),
                getWeekId(),
                getLecturerId(),
                isMonday(),
                isTuesday(),
                isWednesday(),
                isThursday(),
                isFriday());
    }
}
