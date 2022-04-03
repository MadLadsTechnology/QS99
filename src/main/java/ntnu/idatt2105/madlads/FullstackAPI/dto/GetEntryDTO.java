package ntnu.idatt2105.madlads.FullstackAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEntryDTO {
    @Schema(type = "number", description = "Id of the object", required = true, example = "2")
    private Long entryId;
    @Schema(type = "string", description = "Users email", required = true, example = "test@test.no")
    private String studentId;
    @Schema(type = "string", description = "Users first name", required = true, example = "Ola")
    private String firstName;
    @Schema(type = "string", description = "Users last name", required = true, example = "Nordmann")
    private String lastName;
    @Schema(name = "room", type = "number", format = "long", description = "The id of the object", required = true, example = "1")
    private String room;
    @Schema(type = "string", format = "String", description = "The building", required = true, example = "Realfagsbygget")
    private String building;
    @Schema(type = "number", description = "Number of the table", required = true, example = "2")
    private int tableNumber;
    private String type;
    @Schema(name = "startTime", type = "date", format = "LocalDateTime", description = "Start time of the entry", required = true, example = "12:00 01.01.2000")
    private LocalDateTime startTime;
    @Schema(type = "boolean", description = "Indicates whether the student ascoiated with this entry is getting help", required = true, example = "true")
    private boolean isGettingHelp;
    @Schema(type = "Map", description = "All exercises in this entry", required = true)
    private Map<Long, Integer> exercises;

    public GetEntryDTO(Entry entry){
        entryId = entry.getId();
        studentId = entry.getStudent().getEmailAddress();
        firstName = entry.getStudent().getFirstName();
        lastName = entry.getStudent().getLastName();
        room = entry.getRoom();
        building = entry.getBuilding();
        tableNumber = entry.getTableNumber();
        type = entry.getType();
        startTime = entry.getStartTime();
        isGettingHelp = entry.isGettingHelp();
        exercises = getExercises(entry);
    }

    private Map<Long, Integer> getExercises(Entry entry){
        Collection<Exercise> exercises = entry.getExercises();
        Map<Long, Integer> map = new HashMap<>();
        for(Exercise exercise: exercises){
            map.put(exercise.getId(), exercise.getExerciseNumber());
        }
        return map;
    }
}
