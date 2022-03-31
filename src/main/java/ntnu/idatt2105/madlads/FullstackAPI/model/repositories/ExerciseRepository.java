package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Exercise findExerciseById(Long id);

    List<Exercise> findExerciseBySubject(Subject subject);

    void deleteDistinctByExerciseNumber(int exerciseNumber);

    Exercise findDistinctBySubjectAndExerciseNumber(Subject subject, int exerciseNumber);
}