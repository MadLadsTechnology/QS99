package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests for the queue repository
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class QueueRepositoryTest {
    @Autowired
    QueueRepository queueRepository;

    @Autowired
    SubjectRepository subjectRepository;

    /**
     * Testing method for getting a queue by its subject.
     */
    @Test
    void findBySubject() {
        Subject subject = new Subject(
                "IDATT2105", "Fullstack", "a subject", 2022
        );
        subjectRepository.save(subject);
        Queue queue = new Queue(subject, true);
        queueRepository.save(queue);
        assertThat(queueRepository.findBySubject(subject)).isEqualTo(queue);
    }

    /**
     * Testing deletion of a queue.
     * The subject should not be deleted.
     */
    @Test
    void deleteBySubject() {
        Subject subject = new Subject(
                "IDATT2105", "Fullstack", "a subject", 2022
        );
        subjectRepository.save(subject);
        Queue queue = new Queue(subject, true);
        queueRepository.save(queue);
        queueRepository.deleteBySubject(subject);
        assertThat(queueRepository.findBySubject(subject)).isNull();
        assertThat(subjectRepository.findById(subject.getId())).isNotNull();
    }
}