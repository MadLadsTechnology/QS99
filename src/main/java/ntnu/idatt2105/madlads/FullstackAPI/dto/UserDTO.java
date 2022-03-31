package ntnu.idatt2105.madlads.FullstackAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String role;

    public UserDTO(QSUser user){
        this.emailAddress=user.getEmailAddress();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        String type = user.getDtype();
        if (type.equals("QSUser")) type = "Admin";
        this.role= type;
    }
}
