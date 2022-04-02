package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.ExerciseRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.ProfessorRepository;
import ntnu.idatt2105.madlads.FullstackAPI.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DataJpaTest
class ExerciseControllerTest {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProfessorRepository professorRepository;

    static String tokenAdmin;
    static String tokenStudent;
    static String tokenProfessor;

    @BeforeEach
    void before() throws Exception {
         CommonTestService cts = new CommonTestService();
         tokenAdmin = cts.getTokenAdmin();
         tokenStudent = cts.getTokenStudent();
         tokenProfessor = cts.getTokenProfessor();

        mockMvc.perform(post("http://localhost:8001/subject/create")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectName", "Test")
                .param("subjectDescription","Dette er en test")
                .param("year","2022")
                .param("subjectCode","IDATT2105"));

    }

    @Test
    void addExerciseSublist() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", "1")
                .param("numberOfExcercises","2")
                .param("numberOfMandatory","1"));

        Assertions.assertEquals(exerciseRepository.findExerciseBySubjectAndExerciseNumber());
    }

    @Test
    void deleteExercise() {
    }

    @Test
    void approveExercise() {
    }

    @Test
    void getExercisesByUser() {
    }

    @Test
    void getExercisesBySubject() {
    }
}