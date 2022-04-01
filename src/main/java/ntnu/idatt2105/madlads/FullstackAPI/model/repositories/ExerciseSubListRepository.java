package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.ExerciseSubList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseSubListRepository extends JpaRepository<ExerciseSubList, Long> {
}