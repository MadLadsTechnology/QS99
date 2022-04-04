package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.ExerciseSubList;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findExerciseBySubject(Subject subject);

    List<Exercise> findExerciseBySubList(ExerciseSubList subList);

    Exercise findExerciseBySubjectAndExerciseNumber(Subject subject, int exerciseNumber);

    void deleteBySubjectAndExerciseNumber(Subject subject, int exerciseNumber);

    void deleteById(Long id);

    void deleteAllBySubject(Subject subject);
}