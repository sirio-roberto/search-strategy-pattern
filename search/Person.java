package search;

public class Person {
    private String firstName;
    private String lastName;
    private String emailAddress;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String emailAddress) {
        this(firstName, lastName);
        this.emailAddress = emailAddress;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.firstName);
        if (this.lastName != null) {
            sb.append(" ").append(lastName);
        }
        if (this.emailAddress != null) {
            sb.append(" ").append(this.emailAddress);
        }
        return sb.toString();
    }
}
