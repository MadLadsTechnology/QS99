package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s.id, s.subjectName FROM Subject s, StudentSubject ss WHERE ss.id.emailAddress = :foundEmailAddress AND ss.id.id = s.id")
    Collection<Subject> findAllSubjectsByStudent(@Param("foundEmailAddress") String foundEmailAddress);

    Subject findById(int id);
}