package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class StudentSubjectId implements Serializable {
    private String emailAddress;
    private int id;

    public StudentSubjectId(String emailAddress, int id) {
        this.emailAddress = emailAddress;
        this.id = id;
    }

    protected StudentSubjectId() {}
}
