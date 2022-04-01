package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;

@Entity
public class ExerciseSubList {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numberOfMandatory;

    public ExerciseSubList(int numberOfMandatory) {
        this.numberOfMandatory = numberOfMandatory;
    }

    public ExerciseSubList() {

    }

    public Long getId() {
        return id;
    }
}
