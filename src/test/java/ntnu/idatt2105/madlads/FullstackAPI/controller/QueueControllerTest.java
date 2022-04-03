package ntnu.idatt2105.madlads.FullstackAPI.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class QueueControllerTest {


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
    void setQueueStatus() throws Exception {
        mockMvc.perform(post("http://localhost:8001/subject/create")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("subjectName", "test")
                .param("subjectDescription","test")
                .param("year","1")
                .param("subjectCode","IDATT2105"));

        mockMvc.perform(post("http://localhost:8001/queue/setQueueStatus")
                .header("authorization", "Bearer " + tokenAdmin)
                .param("subjectId", "1")
                .param("isActive","true")).andExpect(status().isNoContent());
    }

    @Test
    void addEntryToQueue() {
    }

    @Test
    void deleteEntry() {
    }

    @Test
    void getEntriesBySubject() {
    }
}