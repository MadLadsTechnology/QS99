package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.Professor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subjectName;
    private String subjectCode;
    private String subjectDescription;
    private int mandatoryCount;
    private int subjectYear;
    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Student_Subject",
            joinColumns = {@JoinColumn(name = "subject_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private Set<Student> students = new HashSet<>();

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Professor_Subject",
            joinColumns = {@JoinColumn(name = "subject_id")},
            inverseJoinColumns = {@JoinColumn(name = "professor_id")}
    )
    private Set<Professor> professors = new HashSet<>();

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Subject_Assistants",
            joinColumns = {@JoinColumn(name = "subject_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private Set<Student> assitants = new HashSet<>();

    public Subject(String subjectCode,String subjectName, String subjectDescription, int mandatoryCount, int subjectYear) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.mandatoryCount = mandatoryCount;
        this.subjectYear = subjectYear;
    }

    protected Subject() {}

    public void addStudent(Student student){
        students.add(student);
    }

    public void addProfessor(Professor professor){
        professors.add(professor);
    }

    public void addAssistant(Student student){
        assitants.add(student);
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

    public Set<Student> getStudents() {
        return students;
    }

    public String getSubjectCode() {
        return subjectCode;
    }
}
