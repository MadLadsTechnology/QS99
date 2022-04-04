package ntnu.idatt2105.madlads.FullstackAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
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
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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

    static CommonTestService cts;
    String tokenAdmin;
    String tokenStudent;
    String tokenProfessor;
    String token;

    @Autowired
    EntryController entryController;

    @Autowired
    MockMvc mockMvc;
    /**
     * Creates tokens for all roles
     * @throws Exception
     */
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
    void before() {
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
    void setIsGettingHelpUnauthorized() throws Exception {
        mockMvc.perform(post("http://localhost:8001/entry/setIsGettingHelp")
                        .header("authorization", "Bearer " + "tokenProfessor"))
                .andExpect(status().isForbidden());
    }

    /**
     * Tests delete method.
     * Expected to return 404 because it will not find the entry or the student.
     * @throws Exception
     */
    @Test
    void deleteEntryUnauthorized() throws Exception {
        mockMvc.perform(delete("http://localhost:8001/entry")
                .header("authorization", "Bearer " + "tokenProfessor")
                .param("entryId", "1")).andExpect(status().isForbidden());
    }

}