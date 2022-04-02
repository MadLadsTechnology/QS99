package ntnu.idatt2105.madlads.FullstackAPI.model.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EntryRepositoryTest {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Test
    void findEntryById() {

    }

    @Test
    void findDistinctByQueue() {
    }

    @Test
    void deleteAllByQueue() {
    }

    @Test
    void deleteById() {
    }
}