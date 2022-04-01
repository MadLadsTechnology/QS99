package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.ExerciseRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.ExerciseSubListRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.StudentRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.SubjectRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.ExerciseSubList;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ExerciseSubList> addExerciseSublist(@RequestParam("subjectId") final int subjectId,
                                               @RequestParam("numberOfExercises") final int numberOfExercises,
                                               @RequestParam("numberOfMandatory") final int numberOfMandatory,
                                               Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                int startNumber = exerciseRepository.findExerciseBySubject(subjectRepository.findById(subjectId)).size();
                ExerciseSubList exerciseSubList = exerciseSubListRepository.save(new ExerciseSubList(numberOfMandatory));
                for (int i = startNumber; i < startNumber + numberOfExercises; i++) {
                    if (exerciseRepository.findExerciseBySubjectAndExerciseNumber(subjectRepository.findById(subjectId), i+1)==null){
                        Exercise exercise = new Exercise(subjectRepository.findById(subjectId), i+1, exerciseSubList);
                        exerciseRepository.save(exercise);
                    } else {
                        logger.info("Exercise already exist");
                    }
                }
                return new ResponseEntity<>(exerciseSubList, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> deleteExercise(@RequestParam("subjectId") int subjectId, @RequestParam("exerciseNumber") int exerciseNumber, Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                exerciseRepository.deleteBySubjectAndExerciseNumber(subjectRepository.findById(subjectId), exerciseNumber);
                return new ResponseEntity<>(true, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PostMapping("/approveExercise")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> approveExercise(@RequestParam("subjectId") int subjectId, @RequestParam("exerciseNumber") int exerciseNumber, @RequestParam("studentEmail") String studentEmail, Authentication authentication){
        if (authentication!=null) {
            if (authentication.isAuthenticated()) {
                Subject subject = subjectRepository.findById(subjectId);
                if(subject.getAssistants().contains(studentRepository.findByEmailAddress(authentication.getName()))){
                    Exercise exercise = exerciseRepository.findExerciseBySubjectAndExerciseNumber(subject, exerciseNumber);
                    exercise.addStudent(studentRepository.findByEmailAddress(studentEmail));
                    exerciseRepository.save(exercise);
                    return new ResponseEntity<>(true, HttpStatus.CREATED);
                }
            }
        }
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }
}
