package ntnu.idatt2105.madlads.FullstackAPI.controller;

import ntnu.idatt2105.madlads.FullstackAPI.FullstackApiApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the SubjectController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
class SubjectControllerTest {

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

    /**
     * Testing authentication for addUser
     * @throws Exception
     */
    @Test
    void addUser() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", "1")
                .param("email","test@email.com")).andExpect(status().isNotFound());
    }

    /**
     * Testing authentication for addStudentAssistant
     * @throws Exception
     */
    @Test
    void addStudentAssistant() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/addStudentAssistant")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectId", "1")
                .param("email","test@email.com")).andExpect(status().isNotFound());
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
        mockMvc.perform(get("http://localhost:8001/subject/getByUser")
                .header("authorization", "Bearer " + tokenStudent)).andExpect(status().isNotAcceptable());
    }

    @Test
    void getSubject() throws Exception {
        mockMvc.perform(get("http://localhost:8001/subject/getSubject")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectId", "1")).andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAllSubject() throws Exception {
        mockMvc.perform(get("http://localhost:8001/subject/getAllSubjects")
                .header("authorization", "Bearer " + tokenAdmin)).andExpect(status().isOk());
    }

    @Test
    void deleteSubject() throws Exception {
        mockMvc.perform(delete("http://localhost:8001/subject")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", "1")).andExpect(status().isNotFound());
    }

    @Test
    void removeUserFromSubject() throws Exception {
        mockMvc.perform(delete("http://localhost:8001/deleteUserFromSubject")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", "1")
                .param("emailAddress", "test@email.com")).andExpect(status().isNotFound());
    }

    @Test
    void TestUnauthorized() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/create")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectName", "Fullstack")
                .param("subjectDescription","description")
                .param("year","2022")
                .param("subjectCode", "IDATT2105")).andExpect(status().isUnauthorized());
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectId", "1")
                .param("email","test@email.com")).andExpect(status().isForbidden());
    }
}