package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.dto.GetSubjectsDTO;
import ntnu.idatt2105.madlads.FullstackAPI.dto.SubjectDTO;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.*;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Professor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import ntnu.idatt2105.madlads.FullstackAPI.security.PasswordHashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

import static ntnu.idatt2105.madlads.FullstackAPI.service.CommonService.generateCommonLangPassword;
import static ntnu.idatt2105.madlads.FullstackAPI.service.CommonService.sendEmail;

/**
 * Controller for api calls related to subjects
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/subject")
@CrossOrigin
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

    @Autowired
    ExerciseRepository exerciseRepository;

    QueueController queueController = new QueueController();

    /**
     * Creates a subject. Can be called by a professor.
     * @param subjectName
     * @param description
     * @param year
     * @param subjectCode
     * @param authentication
     * @return HTTP status and the subject if it was created.
     */
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Subject> createSubject(@RequestParam("subjectName") final String subjectName,
                                              @RequestParam("subjectDescription") final String description,
                                              @RequestParam("year") final int year,
                                              @RequestParam("subjectCode") final String subjectCode,
                                              Authentication authentication) {
        if (authentication != null) {
            if (authentication.isAuthenticated()){
                logger.info("subject: " + subjectCode + subjectName + " "+ year);
                try {
                    Subject newSubject = subjectRepository
                            .save(new Subject(subjectCode, subjectName, description, year));
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

    /**
     * Method for adding a student to a subject.
     * @param email
     * @param authentication
     * @return
     */
    @PostMapping("/addUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addUser(@RequestParam("subjectId") final int subjectId,
                                               @RequestParam("email") final String email,
                                               Authentication authentication
    ) {
        if (authentication != null) {
            if (authentication.isAuthenticated()){
                QSUser user = userRepository.getDistinctByEmailAddress(email);
                Subject subject = subjectRepository.findById(subjectId);
                if(subject == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else{
                    logger.info("Trying to add user to subject: " + subject.getSubjectCode());
                    if (user instanceof Student){
                        boolean response = studentRepository.findByEmailAddress(email).addStudentSubject(subject);
                        if(response){
                            logger.info(subject.toString());
                            subjectRepository.save(subject);
                            return new ResponseEntity<>(true, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
                        }
                    }
                    else if(user instanceof Professor){
                        boolean response = professorRepository.findByEmailAddress(email).addSubject(subject);
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
        }
       return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Method for adding a student assistant to a subject
     * @param subjectId
     * @param email
     * @param authentication
     * @return
     */
    @PostMapping("/addStudentAssistant")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addStudentAssistant(@RequestParam("subjectId") final int subjectId,
                                                       @RequestParam("email") final String email,
                                                       Authentication authentication

    ) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                Subject subject = subjectRepository.findById(subjectId);
                if(subject == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else{
                    logger.info("Adding studentassistant to subject: " + subject.getSubjectCode());
                    boolean response = studentRepository.findByEmailAddress(email).addAssistantSubject(subject);
                    if(response){
                        logger.info(subject.toString());
                        subjectRepository.save(subject);
                        return new ResponseEntity<>(true, HttpStatus.OK);
                    } else {
                        logger.info("Assistant already registered");
                        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Method for adding a professor to a subject
     * @param authentication
     * @return
     */
    @PostMapping("/addUsers")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addStudents(@RequestParam("subjectId") int subjectId, @RequestBody Map<String, String> payload, Authentication authentication) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (authentication != null) {
            if (authentication.isAuthenticated()){


                String[] list = payload.get("data").split("\n");
                Subject subject = subjectRepository.findById(subjectId);
                ArrayList<String[]> listSplitProperly = new ArrayList<>();
                Pattern pattern = Pattern.compile("^(.+)@(.+)$");

                for (String string: list){
                    listSplitProperly.add(string.split(" "));
                }

                for (String[] strings: listSplitProperly){
                    String firstName = strings[0];
                    String lastName = strings[1];
                    String email = strings[2].trim();
                    QSUser user = userRepository.getDistinctByEmailAddress(email);

                    if (!pattern.matcher(email).matches()){
                        return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
                    }

                    if (user == null){
                        String password = generateCommonLangPassword();
                        String hashedPassword = PasswordHashing.generatePasswordHash(password);
                        user = new Student(new QSUser(firstName, lastName, email, hashedPassword));
                        studentRepository.save((Student) user);
                        logger.info("Sent email to:" +email);
                        sendEmail(email,"Test Your password is:"+ password);
                    }
                    logger.info("user: " + user.toString());
                    if (user instanceof Student){
                        if (!((Student) user).getStudentSubjects().contains(subjectId)){
                            ((Student) user).addStudentSubject(subject);
                        }
                        else{
                            Student newStudent = new Student();
                        }
                    }
                    else if (user instanceof Professor){
                        if(!subject.getProfessors().contains(user)){
                            subject.addProfessor((Professor) user);
                        }
                    }
                }
                subjectRepository.save(subject);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Method for getting all subjects a user is related to.
     * @param authentication
     * @return a list of HashMaps where each HashMap contains details of a subject.
     */
    @GetMapping("/getByUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<GetSubjectsDTO>> getSubjectsByUser(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();

            logger.info("Trying to get subjects for " + email);
            if (userRepository.getDistinctByEmailAddress(email) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                QSUser user = userRepository.getDistinctByEmailAddress(email);
                ArrayList<Integer> subjectIds;
                if(user instanceof Student){
                    Student student = studentRepository.findByEmailAddress(email);
                    subjectIds = student.getStudentSubjects();
                } else if (user instanceof Professor) {
                    Professor professor = professorRepository.findByEmailAddress(email);
                    subjectIds = professor.getProfessorSubjects();
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
                }
                ArrayList<GetSubjectsDTO> subjects = new ArrayList<>();
                for(int id: subjectIds){
                    Subject subject = subjectRepository.findById(id);
                    Queue queue = queueRepository.findBySubject(subject);
                    GetSubjectsDTO getSubjectsDTO = new GetSubjectsDTO(subject,queue);
                    subjects.add(getSubjectsDTO);
                }
                logger.info("Sending all subjects related to " + email);
                return new ResponseEntity<>(subjects, HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Method for getting a subject by its ID.
     * @param subjectId
     * @param authentication
     * @return
     */
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

    /**
     * Method for the admin to get all subjects registered in the database.
     * @param authentication
     * @return a list of the subjects.
     */
    @GetMapping("/getAllSubject")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<GetSubjectsDTO>> getAllSubject (Authentication authentication){
        if (authentication != null) {
            if (authentication.isAuthenticated()){
                ArrayList<Subject> subjects = (ArrayList<Subject>) subjectRepository.findAll();
                ArrayList<GetSubjectsDTO> getSubjectsDTO = new ArrayList<>();

                for (Subject subject: subjects){
                    getSubjectsDTO.add(new GetSubjectsDTO(subject, queueRepository.findBySubject(subject)));
                }
                return new ResponseEntity<>(getSubjectsDTO, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Boolean> deleteSubject(Authentication authentication,
                                              @RequestParam("subjectId") final int subjectId){
        if (authentication!=null) {
            if (authentication.isAuthenticated()) {
                Subject subject = subjectRepository.findById(subjectId);
                if (subject != null) {
                    ArrayList<Student> students = new ArrayList<>(subject.getStudents());
                    ArrayList<Professor> professors = new ArrayList<>(subject.getProfessors());
                    ArrayList<Exercise> exercises = (ArrayList<Exercise>) exerciseRepository.findExerciseBySubject(subject);
                    for(Student student: students){
                        for(Exercise exercise: exercises){
                            try{
                                student.removeExercise(exercise);
                            } catch(Exception e) {
                                logger.info("Couldn't remove, because student didn't have exercise approved");
                            }
                        }
                        student.setEntry(null);
                        student.removeStudentSubject(subject);
                        student.removeAssistantSubject(subject);
                        studentRepository.save(student);
                    }
                    for(Professor professor: professors){
                        professor.removeProfessorSubject(subject);
                    }
                    exerciseRepository.deleteAllBySubject(subject);
                    queueRepository.deleteBySubject(subject);
                    subjectRepository.deleteById(subjectId);

                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
