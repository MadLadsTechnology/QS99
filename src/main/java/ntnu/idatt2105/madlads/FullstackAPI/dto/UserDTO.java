package ntnu.idatt2105.madlads.FullstackAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Schema(type = "string", description = "Users email", required = true, example = "test@test.no")
    private String emailAddress;
    @Schema(type = "string", description = "Users first name", required = true, example = "Ola")
    private String firstName;
    @Schema(type = "string", description = "Users last name", required = true, example = "Nordmann")
    private String lastName;
    @Schema(type = "string", description = "Users type", required = true, example = "Professor")
    private String role;
    @Schema(type = "List", description = "All exercises", required = true)
    private ArrayList<GetExerciseByStudentDTO> exercises;

    public UserDTO(Student student, List<Exercise> exercises) {
        this.emailAddress = student.getEmailAddress();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        String type = student.getDtype();
        if (type.equals("QSUser")) type = "Admin";
        this.role = type;
        this.exercises = getExercises(exercises, student);

    }

    public UserDTO(QSUser user) {
        this.emailAddress = user.getEmailAddress();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        String type = user.getDtype();
        if (type.equals("QSUser")) type = "Admin";
        this.role = type;
    }


    public UserDTO(QSUser user, boolean isAssistant) {
        this.emailAddress = user.getEmailAddress();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        String type = user.getDtype();
        if (type.equals("QSUser")) type = "Admin";
        if (isAssistant) type = "Assistant";
        this.role = type;
    }

    private ArrayList<GetExerciseByStudentDTO> getExercises(List<Exercise> exercises, Student student) {
        ArrayList<GetExerciseByStudentDTO> list = new ArrayList<>();
        for (Exercise exercise : exercises) {
            list.add(new GetExerciseByStudentDTO(exercise, student));
        }
        return list;
    }
}
