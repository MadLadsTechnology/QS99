package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QSUser {

    @Id
    @Schema(type = "string", description = "Users email", required = true, example = "test@test.no")
    private String emailAddress;
    @Schema(type = "string", description = "Users first name", required = true, example = "Ola")
    private String firstName;
    @Schema(type = "string", description = "Users last name", required = true, example = "Nordmann")
    private String lastName;
    @Schema(type = "string", description = "Users password saved salted and hashed", required = true, example = "1000:b5764f8c33d900cf6bf7f99927c8d5f8:3197c175246e99b87d59568ad4e39140ffbe0397d5aca9fdef9fc5c1ba7538969863044524ffe6b2b883d44c83d554f8f5c94c1005e8883b395b3916c828b4d2")
    protected String password;

    @Column(insertable = false, updatable = false)
    @Schema(type = "string", description = "Users type", required = true, example = "Professor")
    protected String dtype;

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
