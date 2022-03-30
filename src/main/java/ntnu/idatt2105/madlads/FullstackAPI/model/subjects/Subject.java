package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subjectName;
    private String subjectDescription;
    private int mandatoryCount;
    private int subjectYear;
    @ManyToMany( mappedBy = "studentSubjects")
    private List<Student> students = new ArrayList<>();

    public Subject(String subjectName, String subjectDescription, int mandatoryCount, int subjectYear) {
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.mandatoryCount = mandatoryCount;
        this.subjectYear = subjectYear;
    }

    protected Subject() {}

    public void addStudent(Student student){
        students.add(student);
    }

    public int getId() {
        return id;
    }

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
