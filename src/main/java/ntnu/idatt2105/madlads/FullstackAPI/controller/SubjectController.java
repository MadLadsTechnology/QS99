package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.QueueRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.SubjectRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.UserRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
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

@RestController
@EnableAutoConfiguration
@RequestMapping("/subject")
public class SubjectController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Subject> createUser(@RequestParam("subjectName") final String subjectName,
                                            @RequestParam("subjectDescription") final String description,
                                            @RequestParam("mandatoryCount") final int mandatoryCount,
                                            @RequestParam("year") final int year
                                            ) {
        logger.info("subject: " + subjectName + " "+ year);
        try {
            Subject newSubject = subjectRepository
                    .save(new Subject(subjectName, description, mandatoryCount, year));
            return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getByUser")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<Subject>> getSubjectsByUser(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            logger.info("Trying to get subjects for " + email);
            if (userRepository.findByEmailAddress(email) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                ArrayList<Subject> subjects = (ArrayList<Subject>) subjectRepository.findAllSubjectsByStudent(email);
                return new ResponseEntity<>(subjects, HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
