package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean mandatory;
    private int exerciseNumber;
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private ExerciseSubList subList;

    public Exercise(boolean mandatory, Subject subject, int exerciseNumber, ExerciseSubList ExerciseSubList){
        this.mandatory = mandatory;
        this.subject = subject;
        this.exerciseNumber = exerciseNumber;
        this.subList = ExerciseSubList;
    }

    protected Exercise() {}

    public Long getId() {
        return id;
    }

    public boolean isMandatory() {
        return mandatory;
    }
}
