package ntnu.idatt2105.madlads.FullstackAPI.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Operation;
import ntnu.idatt2105.madlads.FullstackAPI.dto.GetExerciseDTO;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.ExerciseRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.ExerciseSubListRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.StudentRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.SubjectRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.ExerciseSubList;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/exercise")
public class ExerciseController {
    Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ExerciseSubListRepository exerciseSubListRepository;


    /**
     * Create a new exercise
     *
     * @param subjectId
     * @param numberOfExercises
     * @param numberOfMandatory
     * @param authentication
     * @return Returns if a creation was succesfull or not
     */

    @PostMapping("/qs")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Add exercise ", description = "Adds an exercise to a given subject")
    public ResponseEntity<ExerciseSubList> addExerciseSublist(@RequestParam("subjectId") final int subjectId,
                                                              @RequestParam("numberOfExercises") final int numberOfExercises,
                                                              @RequestParam("numberOfMandatory") final int numberOfMandatory,
                                                              Authentication authentication) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                int startNumber = exerciseRepository.findExerciseBySubject(subjectRepository.findById(subjectId)).size();
                ExerciseSubList exerciseSubList = exerciseSubListRepository.save(new ExerciseSubList(numberOfMandatory));
                for (int i = startNumber; i < startNumber + numberOfExercises; i++) {
                    if (exerciseRepository.findExerciseBySubjectAndExerciseNumber(subjectRepository.findById(subjectId), i + 1) == null) {
                        Exercise exercise = new Exercise(subjectRepository.findById(subjectId), i + 1, exerciseSubList);
                        exerciseRepository.save(exercise);
                    } else {
                        logger.info("Exercise already exist");
                    }
                }
                return new ResponseEntity<>(exerciseSubList, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Deletes an exercise
     *
     * @param subjectId
     * @param exerciseNumber
     * @param authentication
     * @return Returns if a deletion was successfully or not
     */

    @Operation(summary = "Delete an exercise", description = "Deletes an exercise from the subject")
    @DeleteMapping("/qs")
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Boolean> deleteExercise(@RequestParam("subjectId") int subjectId,
                                                  @RequestParam("exerciseNumber") int exerciseNumber,
                                                  Authentication authentication) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                exerciseRepository.deleteBySubjectAndExerciseNumber(subjectRepository.findById(subjectId), exerciseNumber);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Approve an exercise
     * @param subjectId
     * @param exerciseNumber
     * @param studentEmail
     * @param authentication
     * @return Returns whether it could approve an exercise or not
     */
    @Operation(summary = "Approve an exercise", description = "Approve a given exercise in a given subject")
    @PostMapping("/qs/student/approveExercise")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> approveExercise(@RequestParam("subjectId") int subjectId,
                                                   @RequestParam("exerciseNumber") int exerciseNumber,
                                                   @RequestParam("studentEmail") String studentEmail,
                                                   @RequestParam("isApproved") boolean isApproved,
                                                   Authentication authentication) {
        if (authentication != null) {
            Subject subject = subjectRepository.findById(subjectId);

            if (authentication.isAuthenticated() || subject.getAssistants().contains(studentRepository.findByEmailAddress(authentication.getName()))) {

                Exercise exercise = exerciseRepository.findExerciseBySubjectAndExerciseNumber(subject, exerciseNumber);

                Student student = studentRepository.findByEmailAddress(studentEmail);
                if (exercise.getStudents().contains(student) && !isApproved) {
                    exercise.removeStudent(student);
                } else if (!exercise.getStudents().contains(student) && isApproved) {
                    exercise.addStudent(student);
                }
                exerciseRepository.save(exercise);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                logger.info(authentication.getName() + " is not a student assistant in this subject");
            }
        }
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Get all exercises that belong to a user
     *
     * @param authentication
     * @param subjectId
     * @return Returns a list of all the exercises
     */

    @Operation(summary = "Get exercise per user", description = "Gets all exercises for a user within one subject")
    @GetMapping("/qs/student/getByUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<GetExerciseDTO>> getExercisesByUser(Authentication authentication,
                                                                        @RequestParam("subjectId") final int subjectId) {
        try {
            if (authentication != null) {
                if (authentication.isAuthenticated()) {
                    Student student = studentRepository.findByEmailAddress(authentication.getName());
                    Subject subject = subjectRepository.findById(subjectId);
                    logger.info("Getting exercises for " + student.getEmailAddress() + " in subject " + subject.getSubjectCode());
                    ArrayList<Exercise> exercises = (ArrayList<Exercise>) exerciseRepository.findExerciseBySubject(subject);
                    ArrayList<GetExerciseDTO> response = new ArrayList<>();
                    for (Exercise exercise : exercises) {
                        if (student.getApprovedExercises().contains(exercise)) {
                            GetExerciseDTO getExerciseDTO = new GetExerciseDTO(exercise, true);
                            response.add(getExerciseDTO);
                        } else {
                            GetExerciseDTO getExerciseDTO = new GetExerciseDTO(exercise, false);
                            response.add(getExerciseDTO);
                        }
                    }
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all exercises in a spesific subject
     *
     * @param authentication
     * @param subjectId
     * @return A list of exercises
     */

    @Operation(summary = "Get all exercises in a subject", description = "Gets all exercises given a subject")
    @GetMapping("/qs/student/getBySubject")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<GetExerciseDTO>> getExercisesBySubject(Authentication authentication,
                                                                           @RequestParam("subjectId") final int subjectId) {
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                ArrayList<Exercise> exercises = (ArrayList<Exercise>) exerciseRepository.findExerciseBySubject(subjectRepository.findById(subjectId));
                ArrayList<GetExerciseDTO> exerciseDTOS = new ArrayList<>();
                for (Exercise exercise : exercises) {
                    exerciseDTOS.add(new GetExerciseDTO(exercise, null));
                }
                return new ResponseEntity<>(exerciseDTOS, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
