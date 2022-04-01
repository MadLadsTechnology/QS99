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
    private int subjectYear;
    @ManyToMany(mappedBy = "studentSubjects", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "professorSubjects", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Professor> professors = new HashSet<>();

    @ManyToMany(mappedBy = "assistantSubjects", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Student> assistants = new HashSet<>();

    public Subject(String subjectCode,String subjectName, String subjectDescription, int subjectYear) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.subjectYear = subjectYear;
    }

    protected Subject() {}

    public boolean addStudent(Student student){
        if(!((students.contains(student)) || assistants.contains(student))){
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
        if(!((students.contains(student)) || assistants.contains(student))){
            assistants.add(student);
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

    public Set<Student> getAssistants() {
        return assistants;
    }
}
