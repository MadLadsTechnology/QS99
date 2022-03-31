package ntnu.idatt2105.madlads.FullstackAPI.model.users;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor extends QSUser {
    @ManyToMany(mappedBy = "professors", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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
    public void addSubject(Subject subject){
        professorSubjects.add(subject);
    }

}
