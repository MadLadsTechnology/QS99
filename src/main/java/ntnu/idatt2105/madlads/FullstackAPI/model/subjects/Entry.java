package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Entry {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Date startTime;
    private String room;
    private String status;
    private String type;
    private String building;
    private String table;
    @ManyToOne
    private Queue queue;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="entry_exercises",
            joinColumns = @JoinColumn(name = "entry_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "exercise_id", referencedColumnName = "id"))
    private Collection<Exercise> exercises;

    public Long getId() {
        return id;
    }
}
