package ntnu.idatt2105.madlads.FullstackAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetExerciseDTO {
    @Schema(type = "id", description = "Id of the object", required = true, example = "2")
    private Long id;
    @Schema(type = "number", description = "Number of the exercise", required = true, example = "4")
    private int exerciseNumber;
    private Long subListId;
    @Schema(type = "number", description = "How many exercises are mandatory", required = true, example = "4")
    private int mandatoryNumber;
    @Schema(type = "boolean", description = "Whether exercise is approved", required = true, example = "true")
    private Boolean isApproved;

    public GetExerciseDTO(Exercise exercise, Boolean isApproved){
        this.id = exercise.getId();
        this.exerciseNumber = exercise.getExerciseNumber();
        this.subListId = exercise.getSubList().getId();
        this.mandatoryNumber = exercise.getSubList().getNumberOfMandatory();
        this.isApproved = isApproved;
    }
}
