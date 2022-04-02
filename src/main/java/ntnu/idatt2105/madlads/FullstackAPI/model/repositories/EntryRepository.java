package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    Entry findEntryById(Long id);
    Collection<Entry> findDistinctByQueue(Queue queue);
    void deleteAllByQueue(Queue queue);
}