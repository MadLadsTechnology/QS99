package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import io.swagger.v3.oas.annotations.media.Schema;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class Entry {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "id", type = "number", format = "long", description = "The id of the object", required = true, example = "1")
    private Long id;

    @Schema(name = "startTime", type = "date", format = "LocalDateTime", description = "Start time of the entry", required = true, example = "12:00 01.01.2000")
    private LocalDateTime startTime;

    @Schema(name = "room", type = "number", format = "long", description = "The id of the object", required = true, example = "1")
    private String room;

    private String type;

    @Schema(type = "string", format = "String", description = "The building", required = true, example = "Realfagsbygget")
    private String building;

    @Schema(type = "number", description = "Number of the table", required = true, example = "2")
    private int tableNumber;

    @Schema(type = "boolean", description = "Indicates whether the student ascoiated with this entry is getting help", required = true, example = "true")
    private boolean isGettingHelp;


    @ManyToOne
    @Schema(type = "Student", description = "Student ascoiated with this entry", required = true)
    private Student student;

    @ManyToOne
    @Schema(type = "Queue", description = "Which queue the entry is in", required = true)
    private Queue queue;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="entry_exercises",
            joinColumns = @JoinColumn(name = "entry_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "exercise_id", referencedColumnName = "id"))
    @Schema(name = "exercises", type = "list", format = "collection", description = "A collection of all exercises", required = true)
    private Collection<Exercise> exercises;

    public Entry(LocalDateTime startTime, String room,  String building, int tableNumber, String type, Student student, Queue queue, Collection<Exercise> exercises) {
        this.startTime = startTime;
        this.room = room;
        this.type = type;
        this.building = building;
        this.tableNumber = tableNumber;
        this.queue = queue;
        this.exercises = exercises;
        this.student = student;
        this.isGettingHelp = false;
    }

    public Entry() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getRoom() {
        return room;
    }

    public String getType() {
        return type;
    }

    public String getBuilding() {
        return building;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public Student getStudent() {
        return student;
    }

    public Queue getQueue() {
        return queue;
    }

    public Collection<Exercise> getExercises() {
        return exercises;
    }

    public void setGettingHelp(boolean gettingHelp) {
        isGettingHelp = gettingHelp;
    }

    public boolean isGettingHelp() {
        return isGettingHelp;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }
    public void removeExercises(){
        exercises = new ArrayList<>();
    }
}
