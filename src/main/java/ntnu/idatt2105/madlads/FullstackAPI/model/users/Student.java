package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Student extends QSUser {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="StudentSubject",
            joinColumns = @JoinColumn(name = "emailAddress", referencedColumnName = "emailAddress"),
            inverseJoinColumns = @JoinColumn(name= "subjectId", referencedColumnName = "id"))
    private List<Subject> studentSubjects = new ArrayList<>();

    @ManyToOne
    private Entry entry;

    public Student() {
        role = "Student";
    }

    public void addSubject(Subject subject){
        studentSubjects.add(subject);
    }
}
