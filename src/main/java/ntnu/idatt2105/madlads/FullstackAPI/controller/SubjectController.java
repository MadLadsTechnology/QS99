package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.QueueRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.SubjectRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/subject")
public class SubjectController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    SubjectRepository subjectRepository;

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
}
