package ntnu.idatt2105.madlads.FullstackAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class EntryControllerTest {

    @Autowired
    EntryController entryController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void setIsGettingHelp() throws Exception {

        String token = "Test";

        mockMvc.perform(post("/setIsGettingHelp")
                .header("authorization", "Bearer " + token));

    }

    @Test
    void deleteEntry() {
    }
}