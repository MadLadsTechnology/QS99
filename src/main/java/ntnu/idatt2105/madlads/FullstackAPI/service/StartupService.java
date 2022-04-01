package ntnu.idatt2105.madlads.FullstackAPI.service;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.UserRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.security.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class StartupService {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void createAdminUser() throws NoSuchAlgorithmException, InvalidKeySpecException {
        //TODO: CHANGE PASSWORD OF ADMIN!!!!
        //TODO: CHANGE PASSWORD OF ADMIN!!!!
        // TODO: CHANGE PASSWORD OF ADMIN!!!!
        String email = "admin@admin.no";

        QSUser adminUser = new QSUser("Admin", "Admin", email, PasswordHashing.generatePasswordHash("password"));
        if (userRepository.getDistinctByEmailAddress(email)==null){
            userRepository.save(adminUser);
        }
    }
}
