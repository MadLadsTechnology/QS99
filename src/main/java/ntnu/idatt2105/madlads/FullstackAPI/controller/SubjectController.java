package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.*;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/subject")
public class SubjectController {
    Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    QueueRepository queueRepository;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Subject> createUser(@RequestParam("subjectName") final String subjectName,
                                            @RequestParam("subjectDescription") final String description,
                                            @RequestParam("mandatoryCount") final int mandatoryCount,
                                            @RequestParam("year") final int year,
                                              @RequestParam("subjectCode") final String subjectCode
                                            ) {
        logger.info("subject: " + subjectCode + subjectName + " "+ year);
        try {
            Subject newSubject = subjectRepository
                    .save(new Subject(subjectCode, subjectName, description, mandatoryCount, year));
            return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addStudents")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addStudents(@RequestParam("subjectName") final String subjectName,
                                               @RequestParam("year") final int subjectYear,
                                               @RequestParam("email") final String email
    ) {
        Subject subject = subjectRepository.findBySubjectNameAndSubjectYear(subjectName, subjectYear);
        if(subject == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            logger.info("Adding students to subject: " + subjectName);
            subject.addStudent(studentRepository.findByEmailAddress(email));
            logger.info(subject.toString());
            subjectRepository.save(subject);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }

    @PostMapping("/addProfessor")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addProfessor(@RequestParam("subjectName") final String subjectName,
                                               @RequestParam("year") final int subjectYear,
                                               @RequestParam("email") final String email
    ) {
        Subject subject = subjectRepository.findBySubjectNameAndSubjectYear(subjectName, subjectYear);
        if(subject == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            logger.info("Adding students to subject: " + subjectName);
            subject.addProfessor(professorRepository.findByEmailAddress(email));
            logger.info(subject.toString());
            subjectRepository.save(subject);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }

    @GetMapping("/getByUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<HashMap<String,String>>> getSubjectsByUser(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            logger.info("Trying to get subjects for " + email);
            if (userRepository.findByEmailAddress(email) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Student student = studentRepository.findByEmailAddress(email);
                ArrayList<Integer> subjectIds = student.getStudentSubjects();
                ArrayList<HashMap<String,String>> subjects = new ArrayList<>();
                for(int id: subjectIds){
                    Subject subject = subjectRepository.findById(id);
                    Queue queue = queueRepository.findBySubject(subject);
                    HashMap<String,String> returnMap;
                    returnMap = new HashMap<>();
                    returnMap.put("id", String.valueOf(subject.getId()));
                    returnMap.put("subjectCode",subject.getSubjectCode());
                    returnMap.put("subjectName",subject.getSubjectName());
                    returnMap.put("subjectDescription",subject.getSubjectDescription());
                    returnMap.put("subjectYear", String.valueOf(subject.getSubjectYear()));
                    returnMap.put("isQueueActive", String.valueOf(queue.getStatus()));
                    subjects.add(returnMap);
                }

                return new ResponseEntity<>(subjects, HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
