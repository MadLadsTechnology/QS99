package ntnu.idatt2105.madlads.FullstackAPI.model;

import javax.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //TODO: Is this correct
    private boolean mandatory;

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
