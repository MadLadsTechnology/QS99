package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.dto.GetSubjectsDTO;
import ntnu.idatt2105.madlads.FullstackAPI.dto.SubjectDTO;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.*;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    QueueController queueController = new QueueController();

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Subject> createUser(@RequestParam("subjectName") final String subjectName,
                                              @RequestParam("subjectDescription") final String description,
                                              @RequestParam("mandatoryCount") final int mandatoryCount,
                                              @RequestParam("year") final int year,
                                              @RequestParam("subjectCode") final String subjectCode,
                                              Authentication authentication) {
        if (authentication != null) {
            if (authentication.isAuthenticated()){
                logger.info("subject: " + subjectCode + subjectName + " "+ year);
                try {
                    Subject newSubject = subjectRepository
                            .save(new Subject(subjectCode, subjectName, description, mandatoryCount, year));
                    queueController.createQueue( newSubject.getId(), true, subjectRepository, queueRepository);
                    return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
                } catch (Exception e) {
                    logger.info(e.getMessage());
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @PostMapping("/addStudent")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addStudents(@RequestParam("subjectName") final String subjectName,
                                               @RequestParam("year") final int subjectYear,
                                               @RequestParam("email") final String email,
                                               Authentication authentication
    ) {
        if (authentication != null) {
            if (authentication.isAuthenticated()){
                Subject subject = subjectRepository.findBySubjectNameAndSubjectYear(subjectName, subjectYear);
                if(subject == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else{
                    logger.info("Adding students to subject: " + subjectName);
                    boolean response = subject.addStudent(studentRepository.findByEmailAddress(email));
                    if(response){
                        logger.info(subject.toString());
                        subjectRepository.save(subject);
                        return new ResponseEntity<>(true, HttpStatus.OK);
                    } else {
                        logger.info("Student already registered");
                        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
                    }
                }
            }
        }
       return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PostMapping("/addStudentAssistant")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addStudentAssistant(@RequestParam("subjectName") final String subjectName,
                                                       @RequestParam("year") final int subjectYear,
                                                       @RequestParam("email") final String email,
                                                       Authentication authentication

    ) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                Subject subject = subjectRepository.findBySubjectNameAndSubjectYear(subjectName, subjectYear);
                if(subject == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else{
                    logger.info("Adding studentassistant to subject: " + subjectName);
                    subject.addAssistant(studentRepository.findByEmailAddress(email));
                    logger.info(subject.toString());
                    subjectRepository.save(subject);
                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PostMapping("/addProfessor")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addProfessor(@RequestParam("subjectName") final String subjectName,
                                               @RequestParam("year") final int subjectYear,
                                               @RequestParam("email") final String email,
                                                Authentication authentication
    ) {
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                Subject subject = subjectRepository.findBySubjectNameAndSubjectYear(subjectName, subjectYear);
                if(subject == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else{
                    logger.info("Adding students to subject: " + subjectName);
                    boolean response = subject.addProfessor(professorRepository.findByEmailAddress(email));
                    if(response){
                        logger.info(subject.toString());
                        subjectRepository.save(subject);
                        return new ResponseEntity<>(true, HttpStatus.OK);
                    } else {
                        logger.info("Professor already registered");
                        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/getByUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<GetSubjectsDTO>> getSubjectsByUser(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            logger.info("Trying to get subjects for " + email);
            if (userRepository.findByEmailAddress(email) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Student student = studentRepository.findByEmailAddress(email);
                ArrayList<Integer> subjectIds = student.getStudentSubjects();
                ArrayList<GetSubjectsDTO> subjects = new ArrayList<>();
                for(int id: subjectIds){
                    Subject subject = subjectRepository.findById(id);
                    Queue queue = queueRepository.findBySubject(subject);
                    GetSubjectsDTO getSubjectsDTO = new GetSubjectsDTO(subject,queue);
                    subjects.add(getSubjectsDTO);
                }
                return new ResponseEntity<>(subjects, HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getSubject")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<SubjectDTO> getSubject (@RequestParam("subjectId") int subjectId, Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                Subject subject;
                if ((subject = subjectRepository.findById(subjectId))!=null){
                    SubjectDTO subjectDTO = new SubjectDTO(subject);
                    return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
