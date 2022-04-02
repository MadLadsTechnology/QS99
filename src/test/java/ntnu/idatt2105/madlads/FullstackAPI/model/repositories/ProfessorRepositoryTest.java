package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.Professor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the professor repository
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProfessorRepositoryTest {
    @Autowired
    ProfessorRepository professorRepository;
    /**
     * Test for finding a professor by its ID (email address)
     */
    @Test
    void findByEmailAddress() {
        String email = "test@email.com";
        QSUser user = new QSUser("firstname", "lastname", email, "123");
        Professor professor = new Professor(user);
        professorRepository.save(professor);
        QSUser user2 = new QSUser("firstname", "lastname", "test2@email.com", "123");
        Professor professor2 = new Professor(user2);
        professorRepository.save(professor2);
        assertThat(professorRepository.findByEmailAddress(email)).isEqualTo(professor);
        assertThat(professorRepository.findByEmailAddress(email)).isNotEqualTo(professor2);
    }
}
