package ntnu.idatt2105.madlads.FullstackAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ntnu.idatt2105.madlads.FullstackAPI.model.subjects.Exercise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class ExerciseControllerTest {

    static String tokenAdmin;
    static String tokenStudent;
    static String tokenProfessor;

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    static void before() {
         CommonTestService cts = new CommonTestService();
         tokenAdmin = cts.getTokenAdmin();
         tokenStudent = cts.getTokenStudent();
         tokenProfessor = cts.getTokenProfessor();
    }

    @Test
    void addExerciseSublistSuccessfully() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", "1")
                .param("numberOfExercises","2")
                .param("numberOfMandatory","1")).andExpect(status().isCreated());
    }

    @Test
    void testAllMethodsUnauthorized() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + "wrong")).andExpect(status().isForbidden());


    }

    @Test
    void deleteExercise() throws Exception {
        mockMvc.perform(delete("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", "1")
                .param("exerciseNumber","1")).andExpect(status().isOk());
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