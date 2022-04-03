package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tests for the Entry repository
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class EntryRepositoryTest {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    QueueRepository queueRepository;

    private Queue queue;
    private LocalDateTime now;

    /**
     * Creating a subject and a queue before each test.
     * Because an entry needs a queue.
     */
    @BeforeEach
    void beforeEach(){
        Subject subject = new Subject(
                "IDATT2105", "Fullstack", "a subject", 2022
        );
        subjectRepository.save(subject);
        queue = new Queue(subject, true);
        queueRepository.save(queue);
        now = LocalDateTime.now();
    }

    /**
     * Testing method for finding an entry by its ID.
     */
    @Test
    void findEntryById() {
        Entry entry = new Entry(
                now, "room", "building", 1, "help", null, queue, null
        );
        entryRepository.save(entry);
        assertThat(entryRepository.findEntryById(entry.getId())).isEqualTo(entry);
    }

    /**
     * Testing method for finding all entries in a queue.
     */
    @Test
    void findDistinctByQueue() {
        int numberOfEntries = 10;
        for(int i = 0; i < numberOfEntries; i++){
            Entry entry = new Entry(
                    now, "room", "building", i, "help", null, queue, null
            );
            entryRepository.save(entry);
        }
        assertThat(entryRepository.findDistinctByQueue(queue).size()).isEqualTo(numberOfEntries);
    }

    /**
     * Testing method for deleting all entries in a queue.
     */
    @Test
    void deleteAllByQueue() {
        int numberOfEntries = 10;
        for(int i = 0; i < numberOfEntries; i++){
            Entry entry = new Entry(
                    now, "room", "building", i, "help", null, queue, null
            );
            entryRepository.save(entry);
        }
        assertThat(entryRepository.findDistinctByQueue(queue).size()).isEqualTo(numberOfEntries);
        entryRepository.deleteAllByQueue(queue);
        assertThat(entryRepository.findDistinctByQueue(queue).size()).isEqualTo(0);
    }

    /**
     * Testing method for deleting an entry by its ID.
     */
    @Test
    void deleteById() {
        Entry entry = new Entry(
                now, "room", "building", 1, "help", null, queue, null
        );
        entryRepository.save(entry);
        assertThat(entryRepository.findEntryById(entry.getId())).isEqualTo(entry);
        entryRepository.deleteById(entry.getId());
        assertThat(entryRepository.findEntryById(entry.getId())).isNull();
    }
}