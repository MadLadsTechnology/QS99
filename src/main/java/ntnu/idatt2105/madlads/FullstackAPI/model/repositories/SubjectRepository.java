package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findById(int id);
    Subject findBySubjectNameAndSubjectYear(String subjectName, int subjectYear);
}