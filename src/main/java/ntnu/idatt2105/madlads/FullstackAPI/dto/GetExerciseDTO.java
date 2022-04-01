package ntnu.idatt2105.madlads.FullstackAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.ExerciseSubList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetExerciseDTO {
    private Long id;
    private int exerciseNumber;
    private Long subListId;
    private int mandatoryNumber;
    private boolean isApproved;

    public GetExerciseDTO(Exercise exercise, boolean isApproved){
        this.id = exercise.getId();
        this.exerciseNumber = exercise.getExerciseNumber();
        this.subListId = exercise.getSubList().getId();
        this.mandatoryNumber = exercise.getSubList().getNumberOfMandatory();
        this.isApproved = isApproved;
    }
}
