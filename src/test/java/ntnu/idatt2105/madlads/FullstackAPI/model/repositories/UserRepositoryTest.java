package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void getDistinctByEmailAddress() {
        String email = "test@email.com";
        String notEmail = "not@email.com";
        userRepository
                .save(
                        new QSUser("firstname", "lastname", email, "123")
                );
        assertThat(userRepository.getDistinctByEmailAddress(email)).isNotNull();
        assertThat(userRepository.getDistinctByEmailAddress(notEmail)).isNull();
    }

    @Test
    void deleteByEmailAddress() {
        String email = "test@email.com";
        userRepository
                .save(
                    new QSUser("firstname", "lastname", email, "123")
                );
        userRepository.deleteByEmailAddress(email);
        assertThat(userRepository.getDistinctByEmailAddress(email)).isNull();
    }
}