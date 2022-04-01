package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.Professor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
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
    @ManyToMany(mappedBy = "studentSubjects", cascade =  CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "professorSubjects", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Professor> professors = new HashSet<>();

    @ManyToMany(mappedBy = "assistantSubjects", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Student> assitants = new HashSet<>();

    public Subject(String subjectCode,String subjectName, String subjectDescription, int mandatoryCount, int subjectYear) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.mandatoryCount = mandatoryCount;
        this.subjectYear = subjectYear;
    }

    protected Subject() {}

    public boolean addStudent(Student student){
        if(!((students.contains(student)) || assitants.contains(student))){
            students.add(student);
            return true;
        } else {
            return false;
        }

    }

    public boolean addProfessor(Professor professor){
        if (!professors.contains(professor)){
            professors.add(professor);
            return true;
        } else {
            return false;
        }
    }

    public boolean addAssistant(Student student){
        if(!((students.contains(student)) || assitants.contains(student))){
            assitants.add(student);
            return true;
        } else {
            return false;
        }


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

    public Set<Professor> getProfessors() {
        return professors;
    }
}
