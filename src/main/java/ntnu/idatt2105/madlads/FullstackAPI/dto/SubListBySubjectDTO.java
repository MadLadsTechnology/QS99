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
public class SubListBySubjectDTO {
    private Long subListID;
    private List<Integer> exercises;
    private int numberOfMandatory;

    public SubListBySubjectDTO(ExerciseSubList subList, List<Exercise> exercisesOfSubList){
        this.subListID = subList.getId();
        this.numberOfMandatory = subList.getNumberOfMandatory();
        this.exercises = getExercises(exercisesOfSubList);
    }
    private List<Integer> getExercises(List<Exercise> exercises){
        ArrayList<Integer> map = new ArrayList<>();
        for(Exercise exercise: exercises){
            map.add(exercise.getExerciseNumber());
        }
        return map;
    }
}
