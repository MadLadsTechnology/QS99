package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subjectName;
    private String subjectDescription;
    private int mandatoryCount;
    private int subjectYear;

    public Subject(String subjectName, String subjectDescription, int mandatoryCount, int subjectYear) {
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.mandatoryCount = mandatoryCount;
        this.subjectYear = subjectYear;
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

    public int getSubjectYear() {
        return subjectYear;
    }
}
