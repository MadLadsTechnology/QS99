package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subjectName;
    private String subjectDescription;
    private int mandatoryCount;
    private int year;
    @ManyToMany
    private Collection<User> users;

    public Subject(String subjectName, String subjectDescription, int mandatoryCount, int year) {
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.mandatoryCount = mandatoryCount;
        this.year = year;
    }

    protected Subject() {}

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public int getMandatoryCount() {
        return mandatoryCount;
    }

    public int getYear() {
        return year;
    }
}
