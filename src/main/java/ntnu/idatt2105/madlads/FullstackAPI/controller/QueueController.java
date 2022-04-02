package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.dto.GetEntryDTO;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.*;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.QueueRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.SubjectRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.EntryRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Queue;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Controller for api calls related to the queue
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/queue")
public class QueueController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    QueueRepository queueRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    /**
     * Create a queue, is always called when a subject is created
     * @param subjectId
     * @param isActive
     * @param subjectRepository_
     * @param queueRepository_
     * @return the http response depending on if the queue was successfully created, and the queue as an object
     */
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

    /**
     * Sets the queue status to active or inactive (isActive to true or false)
     * @param isActive
     * @param id
     * @param authentication
     * @return the http response and a boolean depending on if the status was changed or not
     */
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

    /**
     * Method for adding an entry to a queue
     * @param authentication
     * @param payload data needed for creating an entry, including the subject,
     *               the user, the exercises and where you are
     * @return the http response depending on if the entry was created or not, and the entry as an object
     */
    @PostMapping("/addEntry")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<GetEntryDTO> addEntryToQueue(Authentication authentication,
                                                 @RequestParam("room") final String room,
                                                 @RequestParam("building") final String building,
                                                 @RequestParam("tableNumber") final int tableNumber,
                                                 @RequestParam("type") final String type,
                                                 @RequestParam("subjectId") final int subjectId,
                                                 @RequestBody Map<String, Object> payload){
        logger.info("Trying to add an entry...");
        if(authentication!=null){
            if(authentication.isAuthenticated()){
                if(subjectRepository.findById(subjectId) != null && studentRepository.findByEmailAddress(authentication.getName()) != null){
                    LocalDateTime now = LocalDateTime.now();

                    Subject subject = subjectRepository.findById(subjectId);
                    logger.info(subject.getSubjectCode());
                    Student student = studentRepository.findByEmailAddress(authentication.getName());
                    logger.info(student.getFirstName());
                    Queue queue = queueRepository.findBySubject(subject);
                    if(queue.getStatus() && subject.getStudents().contains(student)){
                        Collection<Integer> exerciseIds = (Collection<Integer>) payload.get("exercises");
                        ArrayList<Exercise> exercises = new ArrayList<>();
                        for(int exerciseNumber: exerciseIds){
                            if(exerciseRepository.findDistinctBySubjectAndExerciseNumber(subject, exerciseNumber) == null){
                                logger.info("Couldn't find the exercise");
                            } else {

                                Exercise exercise = exerciseRepository.findDistinctBySubjectAndExerciseNumber(subject, exerciseNumber);
                                exercises.add(exercise);
                                logger.info("Exercise number: " + exercise.getId());
                            }
                        }
                        Entry entry = entryRepository.save(new Entry( now, room, building, tableNumber, type, student, queue, exercises ));
                        GetEntryDTO getEntryDTO = new GetEntryDTO(entry);
                        logger.info("entryid: " + entry.getId());

                        return new ResponseEntity<>(getEntryDTO, HttpStatus.OK);
                    } else {
                        logger.info("Queue not active, or student is not in this subject");
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                }
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Delete an entry. Can be called by a student assistant
     * @param authentication
     * @param id
     * @return the http response and a boolean depending on if the entry was deleted
     */
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> deleteEntry(Authentication authentication,
                                               @RequestParam("entryId") final Long id){
        if(authentication!=null){
            if(authentication.isAuthenticated()){
                if(entryRepository.findEntryById(id) != null){
                    entryRepository.delete(entryRepository.findEntryById(id));
                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
    @GetMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<GetEntryDTO>> getEntriesBySubject(Authentication authentication,
                                                                        @RequestParam("subjectId") final int subjectId){
        if (authentication != null && authentication.isAuthenticated()) {
            Subject subject;
            if(subjectRepository.findById(subjectId) != null ){
                subject = subjectRepository.findById(subjectId);
                Queue queue = queueRepository.findBySubject(subject);
                Collection<Entry> entries = entryRepository.findDistinctByQueue(queue);
                ArrayList<GetEntryDTO> entryDTOS = new ArrayList<>();
                for(Entry entry: entries){
                    GetEntryDTO getEntryDTO = new GetEntryDTO(entry);
                    entryDTOS.add(getEntryDTO);
                }
                return new ResponseEntity<>(entryDTOS, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
