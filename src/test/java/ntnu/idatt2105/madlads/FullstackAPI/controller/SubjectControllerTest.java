package ntnu.idatt2105.madlads.FullstackAPI.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the SubjectController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SubjectControllerTest {

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

        MvcResult login = mockMvc.perform(post("http://localhost:8001/user/login")
                .param("email", "student@student.no")
                .param("password", "password")).andReturn();
        String loginResponseString = login.getResponse().getContentAsString();
        JSONObject loginResponse = new JSONObject(loginResponseString);
        token = (String) loginResponse.get("token");

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

    /**
     * Testing authentication for createSubject
     * @throws Exception
     */
    @Test
    void createSubject() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/create")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectName", "Fullstack")
                .param("subjectDescription","description")
                .param("year","2022")
                .param("subjectCode", "IDATT2105")).andExpect(status().isCreated());
    }

    @Test
    void createSubjectNegative() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/create")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectName", "Fullstack")
                .param("subjectDescription","description")
                .param("year","2022")
                .param("subjectCode", "IDATT2105")).andExpect(status().isForbidden());
    }

    /**
     * Testing authentication for addUser
     * @throws Exception
     */
    @Test
    void addUser() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("email","student@student.no")).andExpect(status().isOk());
    }

    /**
     * Testing authentication for addStudentAssistant
     * @throws Exception
     */
    @Test
    void addStudentAssistant() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/addStudentAssistant")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("email","assistant@student.no")).andExpect(status().isOk());
    }

    @Test
    void addStudents() throws Exception {

    }

    /**
     * Test authentication for method for getting all subjects by user.
     * Will return not acceptable because the "user" is not found by the method
     * and therefore is not an instance of Student or Professor.
     * @throws Exception
     */
    @Test
    void getSubjectsByUser() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("email","student@student.no")).andExpect(status().isOk());
        mockMvc.perform(get("http://localhost:8001/subject/getByUser")
                .header("authorization", "Bearer " + token)).andExpect(status().isOk());
    }

    @Test
    void getSubject() throws Exception {
        mockMvc.perform(get("http://localhost:8001/subject/getSubject")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)).andExpect(status().isOk());
    }

    @Test
    void getAllSubject() throws Exception {
        mockMvc.perform(get("http://localhost:8001/subject/getAllSubject")
                .header("authorization", "Bearer " + tokenAdmin)).andExpect(status().isOk());
    }

    @Test
    void removeUserFromSubject() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("email","student@student.no")).andExpect(status().isOk());
        mockMvc.perform(delete("http://localhost:8001/subject/deleteUserFromSubject")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("emailAddress", "student@student.no")).andExpect(status().isOk());
    }

    @Test
    void TestUnauthorized() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/create")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectName", "Fullstack")
                .param("subjectDescription","description")
                .param("year","2022")
                .param("subjectCode", "IDATT2105")).andExpect(status().isForbidden());
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectId", subjectId)
                .param("email","test@email.com")).andExpect(status().isForbidden());
    }
}