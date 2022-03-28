package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Student extends User{
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="student_subject",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "subject_id", referencedColumnName = "id"))
    private Collection<Subject> subjects;

    @ManyToOne
    private Entry entry;
}
