package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Subject subject;
    private boolean isActive;

    public Queue(Subject subject, boolean isActive) {
        this.subject = subject;
        this.isActive = isActive;
    }

    protected Queue() {

    }

    public Long getId() {
        return id;
    }


}
