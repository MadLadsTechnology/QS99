package ntnu.idatt2105.madlads.FullstackAPI.dto;

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
    private String studentId;
    private String firstName;
    private String lastName;
    private String room;
    private String building;
    private int tableNumber;
    private String type;
    private LocalDateTime startTime;
    private Map<Long, Integer> exercises;

    public GetEntryDTO(Entry entry){
        studentId = entry.getStudent().getEmailAddress();
        firstName = entry.getStudent().getFirstName();
        lastName = entry.getStudent().getLastName();
        room = entry.getRoom();
        building = entry.getBuilding();
        tableNumber = entry.getTableNumber();
        type = entry.getType();
        startTime = entry.getStartTime();
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
