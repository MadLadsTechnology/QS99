package ntnu.idatt2105.madlads.FullstackAPI.controller;

import io.swagger.v3.oas.annotations.Operation;
import ntnu.idatt2105.madlads.FullstackAPI.dto.GetSubjectsDTO;
import ntnu.idatt2105.madlads.FullstackAPI.dto.GetSubjectsStudassCheckDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import static ntnu.idatt2105.madlads.FullstackAPI.serviceOLD.CommonService.generateCommonLangPassword;
import static ntnu.idatt2105.madlads.FullstackAPI.serviceOLD.CommonService.sendEmail;

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

    @Autowired
    EntryRepository entryRepository;

    QueueController queueController = new QueueController();

    /**
     * Creates a subject. Can be called by a professor.
     *
     * @param subjectName
     * @param description
     * @param year
     * @param subjectCode
     * @param authentication
     * @return HTTP status and the subject if it was created.
     */

    @PreAuthorize("hasAnyRole('PROFESSOR', 'ADMIN')")
    @Operation(summary = "Create a subject", description = "Creates a new subject and creates a queue for it aswell")
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Subject> createSubject(@RequestParam("subjectName") final String subjectName,
                                                 @RequestParam("subjectDescription") final String description,
                                                 @RequestParam("year") final int year,
                                                 @RequestParam("subjectCode") final String subjectCode,
                                                 Authentication authentication) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                logger.info("subject: " + subjectCode + subjectName + " " + year);
                try {
                    Subject newSubject = subjectRepository
                            .save(new Subject(subjectCode, subjectName, description, year));
                    queueController.createQueue(newSubject.getId(), true, subjectRepository, queueRepository);
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
     *
     * @param email
     * @param authentication
     * @return
     */

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @Operation(summary = "Add user to a subject", description = "Adds a given user to a given subject")
    @PostMapping("/addUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addUser(@RequestParam("subjectId") final int subjectId,
                                           @RequestParam("email") final String email,
                                           Authentication authentication
    ) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                QSUser user = userRepository.getDistinctByEmailAddress(email);
                Subject subject = subjectRepository.findById(subjectId);
                if (subject == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else {
                    logger.info("Trying to add user to subject: " + subject.getSubjectCode());
                    if (user instanceof Student) {
                        boolean response = studentRepository.findByEmailAddress(email).addStudentSubject(subject);
                        if (response) {
                            logger.info(subject.toString());
                            subjectRepository.save(subject);
                            return new ResponseEntity<>(true, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
                        }
                    } else if (user instanceof Professor) {
                        boolean response = professorRepository.findByEmailAddress(email).addSubject(subject);
                        if (response) {
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
     *
     * @param subjectId
     * @param email
     * @param authentication
     * @return
     */

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @Operation(summary = "Add a student assistant", description = "Adds an a given student as a student assistant to a given subject")
    @PostMapping("/addStudentAssistant")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addStudentAssistant(@RequestParam("subjectId") final int subjectId,
                                                       @RequestParam("email") final String email,
                                                       Authentication authentication

    ) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                Subject subject = subjectRepository.findById(subjectId);
                if (subject == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } else {
                    logger.info("Adding studentassistant to subject: " + subject.getSubjectCode());
                    boolean response = studentRepository.findByEmailAddress(email).addAssistantSubject(subject);
                    if (response) {
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
     *
     * @param authentication
     * @return
     */

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @PostMapping("/addUsers")
    @Operation(summary = "Add users to subject", description = "Adds multiple users to a given subject")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addStudents(@RequestParam("subjectId") int subjectId, @RequestBody Map<String, String> payload, Authentication authentication) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {


                String[] list = payload.get("data").split("\n");
                Subject subject = subjectRepository.findById(subjectId);
                ArrayList<String[]> listSplitProperly = new ArrayList<>();
                Pattern pattern = Pattern.compile("^(.+)@(.+)$");

                for (String string : list) {
                    listSplitProperly.add(string.split(" "));
                }

                for (String[] strings : listSplitProperly) {
                    String lastName = strings[0];
                    String firstName = strings[1];
                    String email = strings[2].trim();
                    QSUser user = userRepository.getDistinctByEmailAddress(email);

                    if (!pattern.matcher(email).matches()) {
                        return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
                    }

                    if (user == null) {
                        String password = generateCommonLangPassword();
                        String hashedPassword = PasswordHashing.generatePasswordHash(password);
                        user = new Student(new QSUser(firstName, lastName, email, hashedPassword));
                        studentRepository.save((Student) user);
                        logger.info("Sent email to:" + email);
                        sendEmail(email, "Your password to QS is:  " + password);
                    }
                    logger.info("user: " + user);
                    if (user instanceof Student) {
                        if (!((Student) user).getStudentSubjects().contains(subjectId)) {
                            ((Student) user).addStudentSubject(subject);
                        } else {
                            Student newStudent = new Student();
                        }
                    } else if (user instanceof Professor) {
                        if (!subject.getProfessors().contains(user)) {
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
     *
     * @param authentication
     * @return a list of HashMaps where each HashMap contains details of a subject.
     */

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'STUDENT')")
    @GetMapping("/getByUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Get all subjects of a user", description = "Gets all subject from a given user")
    public ResponseEntity<ArrayList<GetSubjectsStudassCheckDTO>> getSubjectsByUser(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();

            logger.info("Trying to get subjects for " + email);
            if (userRepository.getDistinctByEmailAddress(email) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                QSUser user = userRepository.getDistinctByEmailAddress(email);
                ArrayList<Integer> subjectIds;
                Student student = null;
                if (user instanceof Student) {
                    student = studentRepository.findByEmailAddress(email);
                    subjectIds = student.getStudentSubjects();
                    subjectIds.addAll(student.getAssistantSubjects());
                } else if (user instanceof Professor) {
                    Professor professor = professorRepository.findByEmailAddress(email);
                    subjectIds = professor.getProfessorSubjects();
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
                }
                ArrayList<GetSubjectsStudassCheckDTO> subjects = new ArrayList<>();
                for (int id : subjectIds) {
                    Subject subject = subjectRepository.findById(id);
                    Queue queue = queueRepository.findBySubject(subject);
                    GetSubjectsStudassCheckDTO getSubjectsDTO = new GetSubjectsStudassCheckDTO(subject, queue, student);
                    subjects.add(getSubjectsDTO);
                }
                logger.info("Sending all subjects related to " + email);
                return new ResponseEntity<>(subjects, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'STUDENT')")
    @Operation(summary = "Get a subject", description = "Gets a given subject")
    @GetMapping("/getSubject")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<GetSubjectsStudassCheckDTO> getSubject(@RequestParam("subjectId") int subjectId, Authentication authentication) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                QSUser user = userRepository.getDistinctByEmailAddress(authentication.getName());
                Student student = null;

                if (user instanceof Student) {
                    student = (Student) user;
                }
                Subject subject;
                //If you are a student, you must be a student in the subject to get access.
                if ((subject = subjectRepository.findById(subjectId)) != null) {
                    if (student == null) {
                        Queue queue = queueRepository.findBySubject(subject);
                        GetSubjectsStudassCheckDTO subjectDTO = new GetSubjectsStudassCheckDTO(subject, queue, null);
                        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
                    } else if (subject.getStudents().contains(student) || Objects.requireNonNull(student).getAssistantSubjects().contains(subjectId)) {
                        Queue queue = queueRepository.findBySubject(subject);
                        GetSubjectsStudassCheckDTO subjectDTO = new GetSubjectsStudassCheckDTO(subject, queue, student);
                        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
                    }
                }

            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Method for the admin to get all subjects registered in the database.
     *
     * @param authentication
     * @return a list of the subjects.
     */

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Get all subjects", description = "Gets all subjects that exist")
    @GetMapping("/getAllSubject")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<GetSubjectsDTO>> getAllSubject(Authentication authentication) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                ArrayList<Subject> subjects = (ArrayList<Subject>) subjectRepository.findAll();
                ArrayList<GetSubjectsDTO> getSubjectsDTO = new ArrayList<>();

                for (Subject subject : subjects) {
                    getSubjectsDTO.add(new GetSubjectsDTO(subject, queueRepository.findBySubject(subject)));
                }
                return new ResponseEntity<>(getSubjectsDTO, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Method for deleting a subject without deleting users connected to the subject
     *
     * @param authentication
     * @param subjectId
     * @return
     */

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @Operation(summary = "Delete a subject", description = "Deletes a given subject")
    @DeleteMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Boolean> deleteSubject(Authentication authentication,
                                                 @RequestParam("subjectId") final int subjectId) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                Subject subject = subjectRepository.findById(subjectId);
                if (subject != null) {
                    ArrayList<Student> students = new ArrayList<>(subject.getStudents());
                    ArrayList<Professor> professors = new ArrayList<>(subject.getProfessors());
                    ArrayList<Exercise> exercises = (ArrayList<Exercise>) exerciseRepository.findExerciseBySubject(subject);
                    //We need to remove all elements connected to the student
                    for (Student student : students) {
                        for (Exercise exercise : exercises) {
                            try {
                                //Remove approved exercises from student
                                student.removeExercise(exercise);
                            } catch (Exception e) {
                                logger.info("Couldn't remove, because student didn't have exercise approved");
                            }
                        }
                        student.setEntry(null);
                        student.removeStudentSubject(subject); //Remove subject from all students
                        student.removeAssistantSubject(subject); //Remove subject from all assistants
                        studentRepository.save(student);
                    }
                    for (Professor professor : professors) {
                        professor.removeProfessorSubject(subject); //Remove subject from all professors
                    }
                    entryRepository.deleteAllByQueue(queueRepository.findBySubject(subject)); //Delete all entries in the subject queue
                    exerciseRepository.deleteAllBySubject(subject); //Delete all exercises in the subject
                    queueRepository.deleteBySubject(subject); //Delete queue from subject
                    subjectRepository.deleteById(subjectId); //Delete subject

                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    @Operation(summary = "Delete a user from a subject", description = "Deletes a given user from a given subject")
    @DeleteMapping("/deleteUserFromSubject")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> removeUserFromSubject(@RequestParam("subjectId") int subjectId, @RequestParam("emailAddress") String emailAddress, Authentication authentication) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                Student student = studentRepository.getById(emailAddress);
                student.removeStudentSubject(subjectRepository.findById(subjectId));
                studentRepository.save(student);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
