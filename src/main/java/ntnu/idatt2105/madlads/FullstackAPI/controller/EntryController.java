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

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    EntryRepository entryRepository;
    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/setIsGettingHelp")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Boolean> setIsGettingHelp(Authentication authentication,
                                                    @RequestParam("entryId") final Long entryId,
                                                    @RequestParam("isGettingHelp") final boolean isGettingHelp){
        Entry entry = entryRepository.findEntryById(entryId);
        Subject subject = entry.getQueue().getSubject();
        if (authentication!=null && (authentication.isAuthenticated() ||
                subject.getAssistants().contains(studentRepository.findByEmailAddress(authentication.getName())))
        ){
            entry.setGettingHelp(isGettingHelp);
            entryRepository.save(entry);
            return new ResponseEntity<>(entry.isGettingHelp(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
