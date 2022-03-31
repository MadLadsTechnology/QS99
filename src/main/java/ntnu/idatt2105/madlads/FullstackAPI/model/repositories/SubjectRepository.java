package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findById(int id);
    Subject findBySubjectCodeAndSubjectYear(String subjectCode, int subjectYear);
    List<Subject> findAll();
    Subject findBySubjectCodeAndSubjectYear(String subjectCode, int subjectYear);
}