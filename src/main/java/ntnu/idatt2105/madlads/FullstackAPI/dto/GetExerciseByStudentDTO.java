package ntnu.idatt2105.madlads.FullstackAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetExerciseByStudentDTO {
    @Schema(type = "id", description = "Id of the object", required = true, example = "2")
    private Long exerciseID;
    @Schema(type = "number", description = "Number of the exercise", required = true, example = "4")
    private int exerciseNumber;
    @Schema(type = "boolean", description = "Whether exercise is approved", required = true, example = "true")
    private boolean isApproved;

    public GetExerciseByStudentDTO(Exercise exercise, Student student){
        exerciseID = exercise.getId();
        this.exerciseNumber = exercise.getExerciseNumber();
        this.isApproved = isApproved(exercise, student);

    }
    private boolean isApproved(Exercise exercise, Student student){
        return student.getApprovedExercises().contains(exercise);
    };
}
