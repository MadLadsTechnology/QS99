package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int exerciseNumber;
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private ExerciseSubList subList;

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Student_Exercises",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}

    )
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
}
