package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Exercise findExerciseById(Long id);

    List<Exercise> findExerciseBySubject(Subject subject);

    Exercise findDistinctBySubjectAndExerciseNumber(Subject subject, int exerciseNumber);

    Exercise findExerciseBySubjectAndExerciseNumber(Subject subject, int exerciseNumber);

    void deleteBySubjectAndExerciseNumber(Subject subject, int exerciseNumber);

    void deleteById(Long id);

    void deleteAllBySubject(Subject subject);
}