package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends QSUser {
    @ManyToMany(mappedBy = "students", cascade =  CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Subject> studentSubjects = new HashSet<>();

    @ManyToMany(mappedBy = "students", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Subject> assitantSubjects = new HashSet<>();

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
    public void addStudentSubject(Subject subject){
        studentSubjects.add(subject);
    }

    public void addAssistantSubject(Subject subject){
        assitantSubjects.add(subject);
    }


}
