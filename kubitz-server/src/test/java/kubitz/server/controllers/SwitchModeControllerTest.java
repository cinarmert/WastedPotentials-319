package kubitz.server.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class SwitchModeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getGameState() throws Exception {
        mockMvc.perform(get("/switch/getGameState/123")).andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void postGameState() throws Exception {
        String body = "{\n" +
                "  \"id\": \"3\",\n" +
                "  \"size\": \"2\",\n" +
                "  \"from\": \"test1\",\n" +
                "  \"to\": \"test2\",\n" +
                "  \"state\": [[2,2],[2,2]]\n" +
                "}";

        mockMvc.perform(post("/switch/postGameState")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}