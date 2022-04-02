package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

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
    private Long id;
    private LocalDateTime startTime;
    private String room;
    private String type;
    private String building;
    private int tableNumber;
    private boolean isGettingHelp;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Queue queue;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="entry_exercises",
            joinColumns = @JoinColumn(name = "entry_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "exercise_id", referencedColumnName = "id"))
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
