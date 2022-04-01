package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.dto.GetSubjectsDTO;
import ntnu.idatt2105.madlads.FullstackAPI.dto.SubjectDTO;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.*;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Professor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import ntnu.idatt2105.madlads.FullstackAPI.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    QueueController queueController = new QueueController();

    /**
     * Creates a subject. Can be called by a professor.
     * @param subjectName
     * @param description
     * @param mandatoryCount
     * @param year
     * @param subjectCode
     * @param authentication
     * @return HTTP status and the subject if it was created.
     */
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Subject> createSubject(@RequestParam("subjectName") final String subjectName,
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

    /**
     * Method for adding a student to a subject.
     * @param subjectYear
     * @param email
     * @param authentication
     * @return
     */
    @PostMapping("/addUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addUser(@RequestParam("subjectCode") final String subjectCode,
                                               @RequestParam("year") final int subjectYear,
                                               @RequestParam("email") final String email,
                                               Authentication authentication
    ) {
        if (authentication != null) {
            if (authentication.isAuthenticated()){
                QSUser user = userRepository.findByEmailAddress(email);
                Subject subject = subjectRepository.findBySubjectCodeAndSubjectYear(subjectCode, subjectYear);
                if(subject == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else{
                    logger.info("Trying to add user to subject: " + subjectCode);
                    if (user instanceof Student){
                        boolean response = subject.addStudent(studentRepository.findByEmailAddress(email));
                        if(response){
                            logger.info(subject.toString());
                            subjectRepository.save(subject);
                            return new ResponseEntity<>(true, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
                        }
                    }
                    else if(user instanceof Professor){
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
        }
       return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Method for adding a student assistant to a subject
     * @param subjectYear
     * @param email
     * @param authentication
     * @return
     */
    @PostMapping("/addStudentAssistant")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addStudentAssistant(@RequestParam("subjectCode") final String subjectCode,
                                                       @RequestParam("year") final int subjectYear,
                                                       @RequestParam("email") final String email,
                                                       Authentication authentication

    ) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                Subject subject = subjectRepository.findBySubjectCodeAndSubjectYear(subjectCode, subjectYear);
                if(subject == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else{
                    logger.info("Adding studentassistant to subject: " + subjectCode);
                    boolean response = subject.addAssistant(studentRepository.findByEmailAddress(email));
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
    public ResponseEntity<Boolean> addStudents(@RequestParam("subjectCode") String subjectCode, @RequestParam("year") int year, @RequestBody Map<String, String> payload, Authentication authentication) {
        if (authentication != null) {
            if (authentication.isAuthenticated()){


                String[] list = payload.get("data").split("\n");
                Subject subject = subjectRepository.findBySubjectCodeAndSubjectYear(subjectCode, year);
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
                        user = new Student(new QSUser(firstName, lastName, email, password));
                        studentRepository.save((Student) user);
                        logger.info("Sent email to:" +email);
                        sendEmail(email,"Test Your password is:"+ password);
                    }
                    logger.info("user: " + user.toString());
                    if (user instanceof Student){
                        if (!subject.getStudents().contains(user)){
                            subject.addStudent((Student) user);
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
            if (userRepository.findByEmailAddress(email) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                QSUser user = userRepository.findByEmailAddress(email);
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
}
