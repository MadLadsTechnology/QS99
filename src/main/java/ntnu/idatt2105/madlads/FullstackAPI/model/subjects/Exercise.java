package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //TODO: Is this correct
    private boolean mandatory;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private ExerciseSubList subList;

    public Exercise(boolean mandatory){
        this.mandatory = mandatory;
    }

    protected Exercise() {}

    public Long getId() {
        return id;
    }

    public boolean isMandatory() {
        return mandatory;
    }
}
