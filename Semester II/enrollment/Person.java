package semester_ii.enrollment;

public class Person {

    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSimpleRepresentation() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}