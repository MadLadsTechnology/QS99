package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

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
 * Tests for the student repository
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;
    /**
     * Testing method for finding a student by its ID (email address).
     * Both positive and negative
     */
    @Test
    void findByEmailAddress() {
        String email = "test@email.com";
        QSUser user = new QSUser("firstname", "lastname", email, "123");
        Student student = new Student(user);
        studentRepository.save(student);
        QSUser user2 = new QSUser("firstname", "lastname", "test2@email.com", "123");
        Student student2 = new Student(user2);
        studentRepository.save(student2);
        assertThat(studentRepository.findByEmailAddress(email)).isEqualTo(student);
        assertThat(studentRepository.findByEmailAddress(email)).isNotEqualTo(student2);
    }

    /**
     * Testing method for getting all students
     */
    @Test
    void findAll() {
        int numberOfStudents = 2;
        QSUser user = new QSUser("firstname", "lastname", "test@email.com", "123");
        studentRepository.save(new Student(user));
        QSUser user2 = new QSUser("firstname", "lastname", "test2@email.com", "123");
        studentRepository.save(new Student(user2));

        assertThat(studentRepository.findAll().size()).isEqualTo(numberOfStudents);
    }
}