package Innlevering1.dataTypes;

public class Lecturer extends Tables {

    private String firstName;
    private String lastName;
    private String email;

    public Lecturer() {
        this("test", "test", "Test");
    }

    public Lecturer(String firstName, String lastname, String email) {
        setFirstName(firstName);
        setLastName(lastname);
        setEmail(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s %-15s %-15s %-20s", super.toString(), getFirstName(), getLastName(), getEmail());
    }
}
