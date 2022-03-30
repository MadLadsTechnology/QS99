package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;

import javax.persistence.*;
import java.util.*;

@Entity
public class Student extends QSUser {
    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Subject> studentSubjects = new HashSet<>();

    @ManyToOne
    private Entry entry;

    public Student(QSUser user) {
        super(user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getPassword());
        dtype="Student";
    }

    public Student() {

    }

    public void addSubject(Subject subject){
        studentSubjects.add(subject);
    }
}
