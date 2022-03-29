package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;

@Entity
public class StudentSubject {

    @EmbeddedId
    StudentSubjectId id;

    public StudentSubject() {
    }

}
