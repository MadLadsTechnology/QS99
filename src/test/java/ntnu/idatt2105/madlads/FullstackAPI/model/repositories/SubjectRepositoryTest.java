package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the subject repository
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class SubjectRepositoryTest {
    @Autowired
    SubjectRepository subjectRepository;

    /**
     * Testing method for finding subject by its ID.
     */
    @Test
    void findById() {
        Subject subject = new Subject(
                "IDATT2105", "Fullstack", "a subject", 2022
        );
        subjectRepository.save(subject);
        assertThat(subjectRepository.findById(subject.getId())).isNotNull();
    }

    /**
     * Testing method for getting all subjects stored in the db.
     */
    @Test
    void findAll() {
        int numberOfSubjects = 10;
        for (int i = 0; i < numberOfSubjects; i++) {
            Subject subject = new Subject(
                    "IDATT2105", "Fullstack", "a subject", 2022
            );
            subjectRepository.save(subject);
        }
        assertThat(subjectRepository.findAll().size()).isEqualTo(numberOfSubjects);
    }

    /**
     * Testing method for deleting a subject by its ID.
     * Both positive and negative.
     */
    @Test
    void deleteById() {
        Subject subject = new Subject(
                "IDATT2105", "Fullstack", "a subject", 2022
        );
        Subject subject2 = new Subject(
                "IDATT2105", "Fullstack", "a subject", 2022
        );
        subjectRepository.save(subject);
        subjectRepository.save(subject2);
        subjectRepository.deleteById(subject.getId());
        assertThat(subjectRepository.findById(subject.getId())).isNull();
        assertThat(subjectRepository.findById(subject2.getId())).isNotNull();
    }
}