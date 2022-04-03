package ntnu.idatt2105.madlads.FullstackAPI.model.subjects;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(type = "number", description = "Id of the object", required = true, example = "2")
    private Long id;
    @OneToOne
    @Schema(type = "Subject", description = "Subject of the queue", required = true)
    private Subject subject;
    @Schema(type = "boolean", description = "Whether the queue is active", required = true, example = "true")
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

    public boolean getStatus(){
        return isActive;
    }

    public Subject getSubject() {
        return subject;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
