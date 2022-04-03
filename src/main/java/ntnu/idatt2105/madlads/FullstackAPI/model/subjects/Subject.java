package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(type = "number", description = "Id of the object", required = true, example = "2")
    private int id;
    @Schema(type = "string", description = "Name of the subject", required = true, example = "Fullstack")
    private String subjectName;
    @Schema(type = "string", description = "Code of the subject", required = true, example = "IDATT2105")
    private String subjectCode;
    @Schema(type = "string", description = "Description of the subject", required = true, example = "Dette er fullstack faget")
    private String subjectDescription;
    @Schema(type = "number", description = "Year of the subject", required = true, example = "2022")
    private int subjectYear;
    @ManyToMany(mappedBy = "studentSubjects", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(type = "set", description = "All students in given subject", required = true)
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "professorSubjects", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(type = "set", description = "All professors in given subject", required = true)
    private Set<Professor> professors = new HashSet<>();

    @ManyToMany(mappedBy = "assistantSubjects", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(type = "set", description = "All studentassistants in given subject", required = true)
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
