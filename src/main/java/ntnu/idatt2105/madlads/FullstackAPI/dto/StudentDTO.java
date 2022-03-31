package ntnu.idatt2105.madlads.FullstackAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String emailAddress;
    private String firstName;
    private String lastName;

    public StudentDTO(Student student){
        this.emailAddress=student.getEmailAddress();
        this.firstName=student.getFirstName();
        this.lastName=student.getLastName();
    }
}
