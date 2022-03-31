package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExerciseSubList {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private int numberOfMandatory;

    public Long getId() {
        return id;
    }
}
