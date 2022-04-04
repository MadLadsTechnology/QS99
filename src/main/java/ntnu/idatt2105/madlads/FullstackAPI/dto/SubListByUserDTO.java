package ntnu.idatt2105.madlads.FullstackAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.ExerciseSubList;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubListByUserDTO {
    private Long subListID;
    private Map<Integer, Boolean> exercises;
    private int numberOfMandatory;

    public SubListByUserDTO(ExerciseSubList subList, Student student, List<Exercise> exercisesOfSubList){
        this.subListID = subList.getId();
        this.numberOfMandatory = subList.getNumberOfMandatory();
        this.exercises = getExercises(exercisesOfSubList, student);
    }


    private Map<Integer, Boolean> getExercises(List<Exercise> exercises, Student student){
        HashMap<Integer, Boolean> map = new HashMap<>();
        for(Exercise exercise: exercises){
            map.put(exercise.getExerciseNumber(), student.getApprovedExercises().contains(exercise));
        }
        return map;
    }
}
