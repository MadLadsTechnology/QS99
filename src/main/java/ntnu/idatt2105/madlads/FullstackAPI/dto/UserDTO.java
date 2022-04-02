package ntnu.idatt2105.madlads.FullstackAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String role;
    private ArrayList<GetExerciseByStudentDTO> exercises;

    public UserDTO(Student student, List<Exercise> exercises){
        this.emailAddress=student.getEmailAddress();
        this.firstName=student.getFirstName();
        this.lastName=student.getLastName();
        String type = student.getDtype();
        if (type.equals("QSUser")) type = "Admin";
        this.role= type;
        this.exercises = getExercises(exercises, student);

    }
    public UserDTO(QSUser user){
        this.emailAddress=user.getEmailAddress();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        String type = user.getDtype();
        if (type.equals("QSUser")) type = "Admin";
        this.role= type;
    }
    private ArrayList<GetExerciseByStudentDTO> getExercises(List<Exercise> exercises, Student student){
        ArrayList<GetExerciseByStudentDTO> list = new ArrayList<>();
        for(Exercise exercise: exercises){
            list.add(new GetExerciseByStudentDTO(exercise, student));
        }
        return list;
    }
}
