package ntnu.idatt2105.madlads.FullstackAPI.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class QueueControllerTest {

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
        mockMvc.perform(post("http://localhost:8001/subject/addUser")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("email", "student@student.no")
                .param("subjectId", subjectId)).andReturn();
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
    void setQueueStatus() throws Exception {
        mockMvc.perform(post("http://localhost:8001/queue/setQueueStatus")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("isActive","true")).andExpect(status().isOk());
    }

    @Test
    void setQueueStatusNegative() throws Exception {
        mockMvc.perform(post("http://localhost:8001/queue/setQueueStatus")
                .header("authorization", "Bearer " + tokenStudent)
                .param("subjectId", subjectId)
                .param("isActive","true")).andExpect(status().isForbidden());
    }


    @Test
    void addEntryToQueueNegative() throws Exception {
        mockMvc.perform(post("http://localhost:8001/exercise")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", subjectId)
                .param("numberOfExercises", "2")
                .param("numberOfMandatory", "1")).andExpect(status().isCreated());
        mockMvc.perform(post("http://localhost:8001/queue/addEntry")
                .header("authorization", "Bearer " + token)
                .param("room", "A4")
                .param("building", "RealfagsBygget")
                .param("tableNumber", "2")
                .param("type", "godkjent")
                .param("subjectId", subjectId).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"exercises\":[]}")).andExpect(status().isForbidden());
    }

    @Test
    void getEntriesBySubject() throws Exception {
        mockMvc.perform(get("http://localhost:8001/queue")
                .header("authorization", "Bearer " + token)
                .param("subjectId", subjectId)).andExpect(status().isOk());
    }

    @Test
    void getEntriesBySubjectNegative() throws Exception {
        mockMvc.perform(get("http://localhost:8001/queue")
                .header("authorization", "Bearer " + token)
                .param("subjectId", "123231")).andExpect(status().isNotFound());
    }

    @Test
    void testAllMethodsForbidden() throws Exception {
        mockMvc.perform(get("http://localhost:8001/queue")
                .header("authorization", "Bearer " + "dsa")
                .param("subjectId", "123231")).andExpect(status().isForbidden());

        mockMvc.perform(post("http://localhost:8001/queue/addEntry")
                .header("authorization", "Bearer " + "Wrong token")
                .param("room", "A4")
                .param("building", "RealfagsBygget")
                .param("tableNumber", "2")
                .param("type", "godkjent")
                .param("subjectId", subjectId).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"exercises\":[]}")).andExpect(status().isForbidden());

        mockMvc.perform(post("http://localhost:8001/queue/setQueueStatus")
                .header("authorization", "Bearer " + "Wrong token")
                .param("subjectId", subjectId)
                .param("isActive","true")).andExpect(status().isForbidden());
    }
}