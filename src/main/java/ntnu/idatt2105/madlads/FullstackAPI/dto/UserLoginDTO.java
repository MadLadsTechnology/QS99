package ntnu.idatt2105.madlads.FullstackAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String role;
    private String token;

    public UserLoginDTO(QSUser user, String token){
        this.emailAddress=user.getEmailAddress();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        String type = user.getDtype();
        if (type.equals("QSUser")) type = "Admin";
        this.role = type;
        this.token = token;
    }
}