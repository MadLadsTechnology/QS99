package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import javax.persistence.*;

@Entity
public class User {

    @Id
    private String emailAddress;
    private String firstName;
    private String lastName;
    protected String password;

    public User(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    protected User(){}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword(){ return password; }
}
