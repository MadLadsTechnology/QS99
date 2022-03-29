package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;

@Entity
public class StudentSubject {

    @EmbeddedId
    StudentSubjectId id;
    private String emailAddress;
    private int subjectId;

    public StudentSubject() {
    }

}
