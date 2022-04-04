package ntnu.idatt2105.madlads.FullstackAPI.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class ExerciseControllerTest {

    static CommonTestService cts;
    String tokenAdmin;
    String tokenStudent;
    String tokenProfessor;

    String token;
    String subjectId;

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    static void beforeAll() throws IOException {
        cts = new CommonTestService();
        cts.deleteDatabase();
    }

    @AfterAll
    static void afterAll() throws IOException {
        cts.deleteDatabase();
    }

    /**
     * Before each method create a student, subject and assistant (student).
     * Using registerOLD because then we can access the password easier.
     * @throws Exception
     */
    @BeforeEach
    void before() throws Exception{
        tokenAdmin = cts.getTokenAdmin();
        tokenStudent = cts.getTokenStudent();
        tokenProfessor = cts.getTokenProfessor();
        mockMvc.perform(post("http://localhost:8001/user/registerStudentOLD")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("firstname", "Ola")
                .param("lastname", "Nordmann")
                .param("email", "student@student.no")
                .param("password", "password")).andReturn();


        mockMvc.perform(post("http://localhost:8001/user/registerStudentOLD")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("firstname", "Assist")
                .param("lastname", "Ant")
                .param("email", "assistant@student.no")
                .param("password", "password")).andReturn();

        MvcResult subject = mockMvc.perform(post("http://localhost:8001/subject/create")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectName", "Fullstack")
                .param("subjectDescription","description")
                .param("year","2022")
                .param("subjectCode", "IDATT2105")).andReturn();
        String subjectString = subject.getResponse().getContentAsString();
        JSONObject subjectJSON = new JSONObject(subjectString);
        System.out.println(subjectJSON);
        subjectId = String.valueOf(subjectJSON.get("id"));
    }

    @AfterEach
    void after() throws Exception{
        mockMvc.perform(delete("http://localhost:8001/user/")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("email", "student@student.no"));
        mockMvc.perform(delete("http://localhost:8001/user/")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("email", "assistant@student.no"));
        mockMvc.perform(delete("http://localhost:8001/subject/")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId));
    }

    @Test
    void addExerciseSuccessfully() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("numberOfExercises", "2")
                .param("numberOfMandatory", "1")).andExpect(status().isCreated());
    }

    @Test
    void addExerciseNegative() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", "1020143")
                .param("numberOfExercises", "2")
                .param("numberOfMandatory", "1")).andExpect(status().isUnprocessableEntity());
    }

    @Test
    void approveExercise() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("numberOfExercises", "2")
                .param("numberOfMandatory", "1")).andExpect(status().isCreated());
        mockMvc.perform(post("http://localhost:8001/exercise/approveExercise")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("exerciseNumber", "1")
                .param("studentEmail", "student@student.no")
                .param("isApproved", "true")).andExpect(status().isOk());
    }

    @Test
    void approveExerciseNegative() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("numberOfExercises", "2")
                .param("numberOfMandatory", "1")).andExpect(status().isCreated());
        mockMvc.perform(post("http://localhost:8001/exercise/approveExercise")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", "222")
                .param("exerciseNumber", "1")
                .param("studentEmail", "asdads")
                .param("isApproved", "true")).andExpect(status().isNotFound());
    }

    @Test
    void deleteExercise() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("numberOfExercises", "2")
                .param("numberOfMandatory", "1")).andExpect(status().isCreated());
        mockMvc.perform(delete("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("exerciseNumber", "1")).andExpect(status().isOk());
    }

    @Test
    void deleteExerciseNegative() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("numberOfExercises", "2")
                .param("numberOfMandatory", "1")).andExpect(status().isCreated());
        mockMvc.perform(delete("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + token)
                .param("subjectId", subjectId)
                .param("exerciseNumber", "1")).andExpect(status().isForbidden());
    }

    @Test
    void getExercisesByUser() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("numberOfExercises", "2")
                .param("numberOfMandatory", "1")).andExpect(status().isCreated());
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("email","student@student.no")).andExpect(status().isOk());
    }

    @Test
    void getExercisesByUserNegative() throws Exception {
        mockMvc.perform(get("http://localhost:8001/exercise/getByUser")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectId", "1")).andExpect(status().isForbidden());
    }

    @Test
    void getExercisesBySubject() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("numberOfExercises", "2")
                .param("numberOfMandatory", "1")).andExpect(status().isCreated());
        mockMvc.perform(get("http://localhost:8001/exercise/getBySubject")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)).andExpect(status().isOk());
    }

    @Test
    void getExercisesBySubjectNegative() throws Exception {
        mockMvc.perform(get("http://localhost:8001/exercise/getBySubject")
                .header("authorization", "Bearer " + "asdd")
                .param("subjectId", "102022")).andExpect(status().isForbidden());
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