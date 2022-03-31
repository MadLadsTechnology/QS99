package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.ExerciseRepository;
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


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> addExercise(@RequestParam("subjectId") int subjectId, @RequestParam("exerciseNumber") int exerciseNumber, Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                Exercise exercise = new Exercise(true, subjectRepository.findById(subjectId), exerciseNumber,null);
                exerciseRepository.save(exercise);
                return new ResponseEntity<>(true, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> deleteExercise(@RequestParam("exerciseNumber") int exerciseNumber, Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                exerciseRepository.deleteDistinctByExerciseNumber(exerciseNumber);
                return new ResponseEntity<>(true, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
