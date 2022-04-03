package ntnu.idatt2105.madlads.FullstackAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the EntryController class
 */
@SpringBootTest
@AutoConfigureMockMvc
class EntryControllerTest {

    static String tokenAdmin;
    static String tokenStudent;
    static String tokenProfessor;

    @Autowired
    EntryController entryController;

    @Autowired
    MockMvc mockMvc;
    /**
     * Creates tokens for all roles
     * @throws Exception
     */
    @BeforeAll
    static void before() throws Exception {
        CommonTestService cts = new CommonTestService();
        tokenAdmin = cts.getTokenAdmin();
        tokenStudent = cts.getTokenStudent();
        tokenProfessor = cts.getTokenProfessor();
    }
    /**
     * Tests isGettingHelp method.
     * Expected to return 404 because it will not find the entry or the student.
     * @throws Exception
     */
    @Test
    void setIsGettingHelp() throws Exception {
        mockMvc.perform(post("http://localhost:8001/entry/setIsGettingHelp")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("entryId", "1")
                .param("isGettingHelp", String.valueOf(true))).andExpect(status().isNotFound());
    }
    /**
     * Tests delete method.
     * Expected to return 404 because it will not find the entry or the student.
     * @throws Exception
     */
    @Test
    void deleteEntry() throws Exception {
        mockMvc.perform(delete("http://localhost:8001/entry")
                .header("authorization", "Bearer " + tokenProfessor)
                .param("entryId", "1")).andExpect(status().isNotFound());
    }

}