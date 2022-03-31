package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QueueRepository extends JpaRepository<Queue, Long> {
    Queue findBySubject(Subject subject);

    @Modifying
    @Transactional
    @Query("UPDATE Queue q SET q.isActive = :status WHERE q.subject.id=:id")
    void changeQueue(boolean status, int id);


}