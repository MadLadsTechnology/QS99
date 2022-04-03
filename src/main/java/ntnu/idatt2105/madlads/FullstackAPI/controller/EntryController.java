package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.EntryRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.StudentRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.SubjectRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * Class for api calls related to an entry.
 */
@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    EntryRepository entryRepository;
    @Autowired
    StudentRepository studentRepository;

    /**
     * Method for changing the status of an entry
     * @param authentication
     * @param entryId
     * @param isGettingHelp indicates if an assistant is helping this entry.
     * @return
     */
    @PostMapping("/setIsGettingHelp")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Boolean> setIsGettingHelp(Authentication authentication,
                                                    @RequestParam("entryId") final Long entryId,
                                                    @RequestParam("isGettingHelp") final boolean isGettingHelp){
        if(authentication == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try{
            Entry entry = entryRepository.findEntryById(entryId);
            Subject subject = entry.getQueue().getSubject();
            if (
                    authentication.isAuthenticated() ||
                    subject.getAssistants().contains(studentRepository.findByEmailAddress(authentication.getName()))
            ) {
                entry.setGettingHelp(isGettingHelp);
                entryRepository.save(entry);
                return new ResponseEntity<>(entry.isGettingHelp(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method for deleting an entry from the queue.
     * @param authentication
     * @param entryId
     * @return
     */
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Boolean> deleteEntry(Authentication authentication,
                                               @RequestParam("entryId") final Long entryId
                                               ){
        try{
            Entry entry = entryRepository.findEntryById(entryId);
            Subject subject = entry.getQueue().getSubject();
            if(authentication.isAuthenticated() ||
                    subject.getAssistants().contains(studentRepository.findByEmailAddress(authentication.getName()))
            ) {
                if(entryRepository.findEntryById(entryId) == null){
                    return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
                }
                entry.removeExercises();
                entry.setQueue(null); //Removing entry from queue
                entry.setStudent(null); //Removing entry from student
                entryRepository.deleteById(entryId);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
