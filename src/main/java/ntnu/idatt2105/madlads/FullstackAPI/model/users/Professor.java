package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor extends QSUser {
    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "Subject_Professors",
            joinColumns = {@JoinColumn(name = "professor_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    private Set<Subject> professorSubjects = new HashSet<>();

    public Professor(QSUser user) {
        super(user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getPassword());
        dtype = "Professor";
    }

    public Professor() {

    }
    public ArrayList<Integer> getProfessorSubjects(){
        ArrayList<Integer> subjects = new ArrayList<>();
        for(Subject subject: professorSubjects){
            subjects.add(subject.getId());
        }
        return subjects;
    }
    public boolean addSubject(Subject subject){
        if(professorSubjects.contains(subject)){
            return false;
        }else{
            professorSubjects.add(subject);
            return true;
        }
    }

}
