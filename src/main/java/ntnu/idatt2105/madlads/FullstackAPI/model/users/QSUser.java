package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import javax.persistence.*;

@Entity
public class QSUser {

    @Id
    private String emailAddress;
    private String firstName;
    private String lastName;
    protected String password;
    protected String role;

    public QSUser(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    protected QSUser(){}

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
