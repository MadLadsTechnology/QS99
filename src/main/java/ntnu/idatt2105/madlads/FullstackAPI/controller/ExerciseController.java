package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.ExerciseRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.StudentRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.SubjectRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
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

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    StudentRepository studentRepository;


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addExercise(@RequestParam("subjectId") int subjectId, @RequestParam("exerciseNumber") int exerciseNumber, Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                if (exerciseRepository.findExerciseBySubjectAndExerciseNumber(subjectRepository.findById(subjectId), exerciseNumber)==null){
                    Exercise exercise = new Exercise(true, subjectRepository.findById(subjectId), exerciseNumber,null);
                    exerciseRepository.save(exercise);
                    return new ResponseEntity<>(true, HttpStatus.CREATED);
                }
            }
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
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
                Exercise exercise = exerciseRepository.findExerciseBySubjectAndExerciseNumber(subjectRepository.findById(subjectId), exerciseNumber);
                exercise.addStudent(studentRepository.findByEmailAddress(studentEmail));
                exerciseRepository.save(exercise);
                return new ResponseEntity<>(true, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
