package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import io.swagger.v3.oas.annotations.media.Schema;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(type = "id", description = "Id of the object", required = true, example = "2")
    private Long id;
    @Schema(type = "number", description = "Number of the exercise", required = true, example = "4")
    private int exerciseNumber;
    @ManyToOne
    @Schema(type = "subject", description = "Subject associated with this exercise", required = true)
    private Subject subject;

    @ManyToOne
    @Schema(type = "sublist", required = true)
    private ExerciseSubList subList;

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Student_Exercises",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}

    )
    @Schema(type = "set", description = "All students which have this exercise", required = true)
    private Set<Student> students = new HashSet<>();

    public Exercise(Subject subject, int exerciseNumber, ExerciseSubList ExerciseSubList){
        this.subject = subject;
        this.exerciseNumber = exerciseNumber;
        this.subList = ExerciseSubList;
    }

    protected Exercise() {}

    public Long getId() {
        return id;
    }

    public boolean addStudent(Student student){
        if(!students.contains(student)){
            students.add(student);
            return true;
        } else {
            return false;
        }
    }

    public int getExerciseNumber() {
        return exerciseNumber;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Subject getSubject() {
        return subject;
    }

    public ExerciseSubList getSubList() {
        return subList;
    }
}
