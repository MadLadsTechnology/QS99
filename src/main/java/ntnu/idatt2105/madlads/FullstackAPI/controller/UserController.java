package ntnu.idatt2105.madlads.FullstackAPI.controller;

import io.swagger.v3.oas.annotations.Operation;
import ntnu.idatt2105.madlads.FullstackAPI.dto.UserDTO;
import ntnu.idatt2105.madlads.FullstackAPI.dto.UserLoginDTO;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.ExerciseRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.StudentRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.SubjectRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.UserRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Subject;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Professor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import ntnu.idatt2105.madlads.FullstackAPI.security.PasswordHashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import static ntnu.idatt2105.madlads.FullstackAPI.service.CommonService.generateCommonLangPassword;
import static ntnu.idatt2105.madlads.FullstackAPI.service.CommonService.sendEmail;
import static ntnu.idatt2105.madlads.FullstackAPI.service.UserService.generateToken;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    /**
     * Login endpoint, logs a user in
     * @param email
     * @param password
     * @return A token
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    @PostMapping(value = "/login")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Log in", description = "Logs a user in")
    public ResponseEntity<UserLoginDTO> login(@RequestParam("email") final String email,
                                                            @RequestParam("password") final String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        QSUser foundUser = userRepository.getDistinctByEmailAddress(email);

        if (foundUser != null) {
            if (email.equals(foundUser.getEmailAddress()) && PasswordHashing.validatePassword(password, foundUser.getPassword())) {
                String role = "";
                if (userRepository.getDistinctByEmailAddress(email) instanceof Student){
                    role="ROLE_USER";
                }else if (userRepository.getDistinctByEmailAddress(email) instanceof Professor){
                    role="ROLE_PROFESSOR";
                }else{
                    role="ROLE_ADMIN";
                }
                UserLoginDTO user = new UserLoginDTO(foundUser, generateToken(email, role));
                logger.info("Logged in successfully");
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            logger.info("Wrong password");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        logger.info("Access denied, wrong credentials....");
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    /**
     * Register an admin
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @return a QSUser object
     */
    @PostMapping("/registerAdmin")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Registers an admin", description = "Registers an admin")
    public ResponseEntity<QSUser> createUser(@RequestParam("firstname") final String firstname,
                                             @RequestParam("lastname") final String lastname,
                                             @RequestParam("email") final String email,
                                             @RequestParam("password") final String password) {
        logger.info("email: " + email + " password: " + password);
        if (userRepository.getDistinctByEmailAddress(email) == null){
            try {
                String hashedPassword = PasswordHashing.generatePasswordHash(password);
                QSUser QSUser = userRepository
                        .save(new QSUser(firstname, lastname, email, hashedPassword));
                logger.info("Saved new user: " + QSUser.getEmailAddress());
                return new ResponseEntity<>(QSUser, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * UNUSED
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/registerStudentOLD")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "UNUSED", description = "UNUSED")
    public ResponseEntity<Student> createStudentOLD(@RequestParam("firstname") final String firstname,
                                              @RequestParam("lastname") final String lastname,
                                              @RequestParam("email") final String email,
                                              @RequestParam("password") final String password) {
        logger.info("email: " + email + " password: " + password);

        if (userRepository.getDistinctByEmailAddress(email) == null){
            try {
                logger.info("trying to register student");
                String hashedPassword = PasswordHashing.generatePasswordHash(password);
                QSUser user = new QSUser(firstname, lastname, email, hashedPassword);
                Student student = userRepository.save(new Student(user));
                logger.info(student.getDtype());
                logger.info("Saved new user: " + student.getEmailAddress());
                QSUser user2 = userRepository.getDistinctByEmailAddress(student.getEmailAddress());
                logger.info(user2.getDtype());
                return new ResponseEntity<>(student, HttpStatus.CREATED);
            } catch (Exception e) {
                logger.info(e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Register a student
     * @param firstname
     * @param lastname
     * @param email
     * @return A student object
     */
    @PostMapping("/registerStudent")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Creates a new student", description = "Creates a new student")
    public ResponseEntity<Student> createStudent(@RequestParam("firstname") final String firstname,
                                                 @RequestParam("lastname") final String lastname,
                                                 @RequestParam("email") final String email) {
        logger.info("email: " + email + " password: ");

        if (userRepository.getDistinctByEmailAddress(email) == null){
            try {
                logger.info("trying to register student");
                String password = generateCommonLangPassword();
                sendEmail(email, "This is your password to QS: " + password);
                String hashedPassword = PasswordHashing.generatePasswordHash(password);
                QSUser user = new QSUser(firstname, lastname, email, hashedPassword);
                Student student = userRepository.save(new Student(user));
                logger.info(student.getDtype());
                logger.info("Saved new user: " + student.getEmailAddress());
                QSUser user2 = userRepository.getDistinctByEmailAddress(student.getEmailAddress());
                logger.info(user2.getDtype());
                return new ResponseEntity<>(student, HttpStatus.CREATED);
            } catch (Exception e) {
                logger.info(e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * UNUSED
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @return UNUSED
     */
    @PostMapping("/registerProfessorOLD")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Unused", description = "Unused")
    public ResponseEntity<Professor> createProfessorOLD(@RequestParam("firstname") final String firstname,
                                                 @RequestParam("lastname") final String lastname,
                                                 @RequestParam("email") final String email,
                                                 @RequestParam("password") final String password) {
        logger.info("email: " + email + " password: " + password);

        if (userRepository.getDistinctByEmailAddress(email) == null){
            try {
                String hashedPassword = PasswordHashing.generatePasswordHash(password);
                QSUser user = new QSUser(firstname, lastname, email, hashedPassword);
                Professor professor = userRepository
                        .save(new Professor(user));
                QSUser user2 = userRepository.getDistinctByEmailAddress(professor.getEmailAddress());
                return new ResponseEntity<>(professor, HttpStatus.CREATED);
            } catch (Exception e) {
                logger.info(e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Registers a new professor Sends email with password to given emailaddress
     * @param firstname
     * @param lastname
     * @param email
     * @return
     */

    @PostMapping("/registerProfessor")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Professor> createProfessor(@RequestParam("firstname") final String firstname,
                                                     @RequestParam("lastname") final String lastname,
                                                     @RequestParam("email") final String email) {
        logger.info("email: " + email);

        if (userRepository.getDistinctByEmailAddress(email) == null){
            try {
                String password = generateCommonLangPassword();
                sendEmail(email, "This is your password to QS: " + password);
                String hashedPassword = PasswordHashing.generatePasswordHash(password);
                QSUser user = new QSUser(firstname, lastname, email, hashedPassword);
                Professor professor = userRepository
                        .save(new Professor(user));
                QSUser user2 = userRepository.getDistinctByEmailAddress(professor.getEmailAddress());
                return new ResponseEntity<>(professor, HttpStatus.CREATED);
            } catch (Exception e) {
                logger.info(e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Deletes a user
     * @param authentication
     * @param email
     * @return a boolean of whether it was successfull or not
     */
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Transactional
    @Operation(summary = "Delete a user", description = "Deletes a user from the subject")
    public ResponseEntity<Boolean> deleteUser(Authentication authentication,
                                             @RequestParam("email") final String email){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                QSUser foundUser = userRepository.getDistinctByEmailAddress(email);
                if(foundUser != null){
                    if(email.equals(foundUser.getEmailAddress())){
                        if(foundUser instanceof Student){
                            Student student = studentRepository.findByEmailAddress(email);
                            ArrayList<Integer> subjectIDs = student.getStudentSubjects();
                            for(int id: subjectIDs){
                                Subject subject = subjectRepository.findById(id);
                                student.removeStudentSubject(subject);
                            }
                        }
                        userRepository.deleteByEmailAddress(email);
                        logger.info("User " + email + " removed");
                        return new ResponseEntity<>(true, HttpStatus.OK);
                    } else {
                        logger.info("User not removed");
                        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
                    }
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Register multiple users
     * @param allUsers
     * @param authentication
     * @return A boolean of whether registration was successfull
     */
    @PostMapping("/registerMultipleUsers")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Add multiple users", description = "Adds multiple users")
    public ResponseEntity<Boolean> registerMultipleUsers(@RequestBody String allUsers, Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                String[] listOfAllUsers = allUsers.split("\n");
                ArrayList<String[]> listSplitProperly = new ArrayList<>();

                for (String listOfAllUser : listOfAllUsers) {
                    String[] temp = listOfAllUser.split(" ");

                    listSplitProperly.add(temp);
                }

                for (String[] strings: listSplitProperly){
                    String email =strings[2];
                    String firstname = strings[0];
                    String lastname =strings[1];
                    if (userRepository.getDistinctByEmailAddress(strings[2]) == null){
                        if (userRepository.getDistinctByEmailAddress(email) == null){
                            try {
                                logger.info("trying to register student");
                                String hashedPassword = PasswordHashing.generatePasswordHash("Test");
                                QSUser user = new QSUser(firstname, lastname, email, hashedPassword);
                                Student student = userRepository
                                        .save(new Student(user));
                                sendEmail(email, "Test");
                            } catch (Exception e) {
                                logger.info(e.getMessage());
                            }
                        }else{
                            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
                        }
                    }else{
                        return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                }
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Get all users that exists
     * @param authentication
     * @return All users
     */

    @GetMapping("/getAllUsers")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Get all users", description = "Gets all users that exist")
    public ResponseEntity<ArrayList<UserDTO>> getAllStudents(Authentication authentication){
        if(authentication!=null){
            if(authentication.isAuthenticated()){
                ArrayList<QSUser> users = (ArrayList<QSUser>) userRepository.findAll();
                ArrayList<UserDTO> userDto = new ArrayList<>();
                for(QSUser user : users){
                    UserDTO studentDTO = new UserDTO(user);
                    userDto.add(studentDTO);
                }
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Gets all users from a given subject
     * @param subjectId
     * @param authentication
     * @return A list of users
     */

    @GetMapping("/getAllUsersFromSubject")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Get all students from a subject", description = "Get all students from a given subject")
    public ResponseEntity<ArrayList<UserDTO>> getAllStudentsFromSubject(@RequestParam("subjectId") int subjectId, Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                Subject subject = subjectRepository.findById(subjectId);
                ArrayList<UserDTO> userDTOs = new ArrayList<>();
                ArrayList<QSUser> usersInSubject = new ArrayList<>(subject.getStudents());
                for (QSUser user: usersInSubject){
                    if(user instanceof Student){
                        Student student = studentRepository.findByEmailAddress(user.getEmailAddress());
                        userDTOs.add(new UserDTO(student, exerciseRepository.findExerciseBySubject(subject)));
                    } else {
                        userDTOs.add(new UserDTO(user));
                    }
                }
                return new ResponseEntity<>(userDTOs, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Get all users from given subject
     * @param subjectId
     * @param email
     * @param authentication
     * @return List of users
     */

    @GetMapping("/getUserFromSubject")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Get users from a subject", description = "Adds an exercise to a given subject")
    public ResponseEntity<UserDTO> getUserFromSubject(@RequestParam("subjectId") int subjectId, @RequestParam("email") String email, Authentication authentication){
        if (authentication!=null){
            if (authentication.isAuthenticated()){
                Student student = studentRepository.findByEmailAddress(email);
                Subject subject = subjectRepository.findById(subjectId);
                if (subject.getAssistants().contains(studentRepository.findByEmailAddress(authentication.getName())) || authentication.getAuthorities().contains("ROLE_ADMIN")|| authentication.getAuthorities().contains("ROLE_PROFESSOR")){
                    UserDTO user = new UserDTO(student, exerciseRepository.findExerciseBySubject(subjectRepository.findById(subjectId)));
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Change password
     * @param newPassword
     * @param oldPassword
     * @param authentication
     * @return A boolean to indicate success or failure
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */

    @PostMapping ("/changePassword")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Change password", description = "Change password of your own user")
    public ResponseEntity<Boolean> changePassword(@RequestParam("newPassword") String newPassword, @RequestParam("oldPassword") String oldPassword, Authentication authentication) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(authentication!=null){
            if(authentication.isAuthenticated()){
                QSUser user = userRepository.getDistinctByEmailAddress(authentication.getName());
                if (PasswordHashing.validatePassword(oldPassword, user.getPassword())){
                    user.setPassword(PasswordHashing.generatePasswordHash(newPassword));
                    userRepository.save(user);
                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}