package ntnu.idatt2105.madlads.FullstackAPI.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    static void before() throws Exception {
         CommonTestService cts = new CommonTestService();
         tokenAdmin = cts.getTokenAdmin();
         tokenStudent = cts.getTokenStudent();
         tokenProfessor = cts.getTokenProfessor();
         cts.deleteDatabase();
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
    void approveExerciseCheckIfAuthorized() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise/approveExercise")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", "1")
                .param("exerciseNumber","1")
                .param("studentEmail","adsads@dsaasd.no")).andExpect(status().isOk());
    }

    @Test
    void deleteExercise() throws Exception {
        mockMvc.perform(delete("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", "1")
                .param("exerciseNumber","1")).andExpect(status().isOk());
    }

    @Test
    void getExercisesByUser() throws Exception {
        mockMvc.perform(get("http://localhost:8001/exercise/getByUser")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectId","1")).andExpect(status().isNotFound());
    }

    @Test
    void getExercisesBySubject() throws Exception {
        mockMvc.perform(get("http://localhost:8001/exercise/getByUser")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectId","1")).andExpect(status().isNotFound());
    }

    @Test
    void testAllMethodsUnauthorized() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + "wrong")).andExpect(status().isForbidden());

        mockMvc.perform(post("http://localhost:8001/exercise/approveExercise")
                .header("authorization", "Bearer " + "wrong")).andExpect(status().isForbidden());

        mockMvc.perform(get("http://localhost:8001/exercise/getByUser")
                .header("authorization", "Bearer " + "wrong")).andExpect(status().isForbidden());

        mockMvc.perform(delete("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + "wrong")).andExpect(status().isForbidden());
    }
}