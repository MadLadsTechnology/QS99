package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the exercise repository
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class ExerciseRepositoryTest {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    SubjectRepository subjectRepository;

    private Subject subject;

    @BeforeEach
    void beforeEach(){
        subject = new Subject(
                "IDATT2105", "Fullstack", "a subject", 2022
        );
        subjectRepository.save(subject);
    }

    /**
     * Testing method for finding all exercises by a subject
     */
    @Test
    void findExerciseBySubject() {
        int numberOfExercises = 10;
        for (int i = 0; i < numberOfExercises; i++) {
            Exercise exercise = new Exercise(subject, i, null);
            exerciseRepository.save(exercise);
        }
        assertThat(exerciseRepository.findExerciseBySubject(subject).size()).isEqualTo(numberOfExercises);
    }

    /**
     * Testing method for finding an exercise by its subject and the number within that subject.
     */
    @Test
    void findExerciseBySubjectAndExerciseNumber() {
        int exerciseNumber = 1;
        Exercise exercise = new Exercise(subject, exerciseNumber, null);
        exerciseRepository.save(exercise);
        assertThat(exerciseRepository.findExerciseBySubjectAndExerciseNumber(subject, exerciseNumber)).isEqualTo(exercise);
    }

    /**
     * Testing deletion of an exercise by its subject and number.
     */
    @Test
    void deleteBySubjectAndExerciseNumber() {
        int exerciseNumber = 1;
        Exercise exercise = new Exercise(subject, exerciseNumber, null);
        exerciseRepository.save(exercise);
        exerciseRepository.deleteBySubjectAndExerciseNumber(subject, exerciseNumber);
        assertThat(exerciseRepository.findExerciseBySubjectAndExerciseNumber(subject, exerciseNumber)).isNull();
    }

    /**
     * Testing deletion of an exercise by its ID.
     */
    @Test
    void deleteById() {
        int exerciseNumber = 1;
        Exercise exercise = new Exercise(subject, exerciseNumber, null);
        exerciseRepository.save(exercise);
        exerciseRepository.deleteById(exercise.getId());
        assertThat(exerciseRepository.findExerciseBySubjectAndExerciseNumber(subject, exerciseNumber)).isNull();
    }

    /**
     * Testing deletion of all exercises within a subject.
     */
    @Test
    void deleteAllBySubject() {
        int numberOfExercises = 10;
        for (int i = 0; i < numberOfExercises; i++) {
            Exercise exercise = new Exercise(subject, i, null);
            exerciseRepository.save(exercise);
        }
        assertThat(exerciseRepository.findExerciseBySubject(subject).size()).isEqualTo(numberOfExercises);
        exerciseRepository.deleteAllBySubject(subject);
        assertThat(exerciseRepository.findExerciseBySubject(subject).size()).isEqualTo(0);
    }
}