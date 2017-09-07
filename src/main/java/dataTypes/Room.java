package dataTypes;

public class Room extends Tables {

    private String roomType;
    private int id, capacity;

    public Room() {
        this(0, "test", 0);
    }

    public Room(int id, String roomType, int capacity) {
        setId(id);
        setRoomType(roomType);
        setCapacity(capacity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return String.format("%-50s %-100s %-20s %-20s", super.toString(), getId(), getRoomType(), getCapacity());

    }
}
