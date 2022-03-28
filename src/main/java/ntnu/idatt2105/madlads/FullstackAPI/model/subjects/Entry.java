package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;

@Entity
public class Entry {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Queue queue;

    public Long getId() {
        return id;
    }
}
