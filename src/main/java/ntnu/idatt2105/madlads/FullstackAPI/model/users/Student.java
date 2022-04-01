package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends QSUser {
    //List of all subjects the student is a student in.
    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "Subject_Students",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    private Set<Subject> studentSubjects = new HashSet<>();

    //List of all subjects the student is an assistant in.
    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "Subject_Assistants",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    private Set<Subject> assistantSubjects = new HashSet<>();

    //List of all approved exercises of a student across all subjects.
    @ManyToMany(mappedBy = "students", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Exercise> approvedExercises = new HashSet<>();

    @ManyToOne
    private Entry entry;

    public Student(QSUser user) {
        super(user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getPassword());
        dtype="Student";
    }

    public Student() {

    }
    public ArrayList<Integer> getStudentSubjects(){
        ArrayList<Integer> subjects = new ArrayList<>();
        for(Subject subject: studentSubjects){
            subjects.add(subject.getId());
        }
        return subjects;
    }
    public boolean addStudentSubject(Subject subject){
        if(studentSubjects.contains(subject) || assistantSubjects.contains(subject)){
            return false;
        } else {
            studentSubjects.add(subject);
            return true;
        }

    }

    public boolean addAssistantSubject(Subject subject){
        if(studentSubjects.contains(subject) || assistantSubjects.contains(subject)){
            return false;
        } else {
            assistantSubjects.add(subject);
            return true;
        }
    }

    public void addApprovedExercise(Exercise exercise){
        approvedExercises.add(exercise);
    }

    public Set<Exercise> getApprovedExercises(){
        return approvedExercises;
    }

    public boolean removeStudentSubject(Subject subject){
        return studentSubjects.remove(subject);
    }
}
