package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findById(int id);
    List<Subject> findAll();
    void deleteById(int id);
}