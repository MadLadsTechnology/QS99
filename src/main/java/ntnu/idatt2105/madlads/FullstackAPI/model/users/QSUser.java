package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("emailAddress").append(emailAddress).append("firstName").append(firstName)
                .append("lastName"+ lastName)
                .append("password"+ password)
                .append("dtype"+ dtype)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QSUser)) return false;
        if(o instanceof Student student){
            return getEmailAddress().equals(student.getEmailAddress());
        }  else if (o instanceof Professor professor) {
            return getEmailAddress().equals(professor.getEmailAddress());
        } else {
            return false;
        }
    }
}
