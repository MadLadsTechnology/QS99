package ntnu.idatt2105.madlads.FullstackAPI.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subjectName;
    private String subjectDescription;
    private int mandatoryCount;
    private int year;
    @ManyToMany(mappedBy = "subjects")
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
