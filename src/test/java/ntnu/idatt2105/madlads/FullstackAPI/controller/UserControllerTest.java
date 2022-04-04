package ntnu.idatt2105.madlads.FullstackAPI.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for endpoints in UserController (/user/**)
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
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
    void login() throws Exception {
        mockMvc.perform(post("http://localhost:8001/user/login")
                .param("email", "student@student.no")
                .param("password", "password")).andExpect(status().isOk());
    }
    @Test
    void loginWrongPassword() throws Exception {
        mockMvc.perform(post("http://localhost:8001/user/login")
                .param("email", "student@student.no")
                .param("password", "Wrongpassword")).andExpect(status().isUnauthorized());
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("http://localhost:8001/user/registerAdmin")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("firstname", "Ola")
                .param("lastname", "Nordmann")
                .param("email", "admin@email.no")
                .param("password", "password")).andExpect(status().isCreated());
    }

    @Test
    void createStudentOLD() throws Exception {
        mockMvc.perform(post("http://localhost:8001/user/registerStudentOLD")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("firstname", "Ola")
                .param("lastname", "Nordmann")
                .param("email", "newStudent@email.no")
                .param("password", "password")).andExpect(status().isCreated());
    }

    @Test
    void createStudent() throws Exception {
        mockMvc.perform(post("http://localhost:8001/user/registerStudent")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("firstname", "Ola")
                .param("lastname", "Nordmann")
                .param("email", "newStudent2@email.no")).andExpect(status().isCreated());
    }

    @Test
    void createProfessor() throws Exception {
        mockMvc.perform(post("http://localhost:8001/user/registerProfessor")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("firstname", "Ola")
                .param("lastname", "Nordmann")
                .param("email", "professor@email.no")).andExpect(status().isCreated());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("http://localhost:8001/user/")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("email", "student@student.no")).andExpect(status().isOk());
    }

    @Test
    void registerMultipleUsers() {

    }

    @Test
    void getAllStudents() throws Exception {
        mockMvc.perform(get("http://localhost:8001/user/getAllUsers")
                .header("authorization", "Bearer " + tokenAdmin)).andExpect(status().isOk());
    }

    @Test
    void getAllStudentsFromSubject() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("email","student@student.no")).andExpect(status().isOk());
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("email","assistant@student.no")).andExpect(status().isOk());
        mockMvc.perform(get("http://localhost:8001/user/getAllUsersFromSubject")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)).andExpect(status().isOk());
    }

    @Test
    void getUserFromSubject() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/addStudentAssistant")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("email","student@student.no")).andExpect(status().isOk());
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", subjectId)
                .param("email","assistant@student.no")).andExpect(status().isOk());
        mockMvc.perform(get("http://localhost:8001/user/getUserFromSubject")
                .header("authorization", "Bearer " + token)
                .param("subjectId", subjectId)
                .param("email", "assistant@student.no")).andExpect(status().isOk());
    }

    @Test
    void changePassword() throws Exception {
        mockMvc.perform(post("http://localhost:8001/user/changePassword")
                .header("authorization", "Bearer " + token)
                .param("newPassword", "newPassword")
                .param("oldPassword","password")).andExpect(status().isOk());
    }
}