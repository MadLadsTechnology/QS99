package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.EntryRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    EntryRepository entryRepository;

    @PostMapping("/setIsGettingHelp")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Boolean> setIsGettingHelp(Authentication authentication,
                                                    @RequestParam("entryId") final Long enrtyId,
                                                    @RequestParam("isGettingHelp") final boolean isGettingHelp){
        if (authentication!=null && authentication.isAuthenticated()){
            if(entryRepository.findEntryById(enrtyId) != null){
                Entry entry = entryRepository.findEntryById(enrtyId);
                entry.setGettingHelp(isGettingHelp);
                return new ResponseEntity<>(entry.isGettingHelp(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
