package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import javax.persistence.*;

@Entity
public class QSUser {

    @Id
    private String emailAddress;
    private String firstName;
    private String lastName;
    protected String password;

    @Column(insertable = false, updatable = false) protected String dtype;

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

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("emailAddress").append(emailAddress).append("firstName").append(firstName)
                .append("lastName"+ lastName)
                .append("password"+ password)
                .append("dtype"+ dtype)
                .toString();
    }
}
