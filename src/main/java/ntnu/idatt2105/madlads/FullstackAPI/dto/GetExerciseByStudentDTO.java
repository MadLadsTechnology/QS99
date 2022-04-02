package ntnu.idatt2105.madlads.FullstackAPI.dto;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

public class GetExerciseByStudentDTO {
    private Long exerciseID;
    private int exerciseNumber;
    private boolean isApproved;

    public GetExerciseByStudentDTO(Exercise exercise, Student student){
        this.exerciseID = exercise.getId();
        this.exerciseNumber = exercise.getExerciseNumber();
        this.isApproved = isApproved(exercise, student);

    }
    private boolean isApproved(Exercise exercise, Student student){
        return student.getApprovedExercises().contains(exercise);
    };
}
