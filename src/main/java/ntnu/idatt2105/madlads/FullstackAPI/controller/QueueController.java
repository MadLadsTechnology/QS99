package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.QueueRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.SubjectRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.UserRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.security.PasswordHashing;
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
@RequestMapping("/queue")
public class QueueController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    QueueRepository queueRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Queue> createQueue(@RequestParam("subject") final int subjectId,
                                             @RequestParam("isActive") final boolean isActive,
                                             SubjectRepository subjectRepository_,
                                             QueueRepository queueRepository_) {
        logger.info("subjectId: " + subjectId + " isActive: " + isActive);
        Subject subject = subjectRepository_.findById(subjectId);
        if(subject == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if(queueRepository_.findBySubject(subject) != null ){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }else{
            try {
            Queue queue = queueRepository_
                    .save(new Queue(subject, isActive));
            return new ResponseEntity<>(queue, HttpStatus.CREATED);
            } catch (Exception e) {
                logger.info(e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/setQueueStatus")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> setQueueStatus (@RequestParam("isActive") boolean isActive, @RequestParam("subjectId") int id, Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                Subject su;
                if ((su = subjectRepository.findById(id))!=null){
                    queueRepository.changeQueue(isActive, su.getId());
                }
                return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}