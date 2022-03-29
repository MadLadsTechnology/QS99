package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Student extends QSUser {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="StudentSubject",
            joinColumns = @JoinColumn(name = "emailAddress", referencedColumnName = "emailAddress"),
            inverseJoinColumns = @JoinColumn(name= "subjectId", referencedColumnName = "id"))
    private Collection<Subject> subjects;

    @ManyToOne
    private Entry entry;

    public Student() {
    }
}
