package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue, Long> {
    Queue findBySubject(Subject subject);
}