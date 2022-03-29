package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Queue {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    private Subject subject;
    private boolean isActive;

    public Long getId() {
        return id;
    }


}
