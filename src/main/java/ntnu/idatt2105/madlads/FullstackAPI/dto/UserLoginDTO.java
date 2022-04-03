package ntnu.idatt2105.madlads.FullstackAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    @Schema(type = "string", description = "Users email", required = true, example = "test@test.no")
    private String emailAddress;
    @Schema(type = "string", description = "Users first name", required = true, example = "Ola")
    private String firstName;
    @Schema(type = "string", description = "Users last name", required = true, example = "Nordmann")
    private String lastName;
    @Schema(type = "string", description = "Users type", required = true, example = "Professor")
    private String role;
    @Schema(type = "string", description = "Users token", required = true, example = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ6MTY0OTU5MTk5Nn0.4iaybnWZPo_AdEr0kcR_-emddb5X3F8Q-QoTKWsAsdrWXaCsIfEaLuUah2zZogq3")
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