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
public class ClassicModeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getChallenge() throws Exception {
        mockMvc.perform(get("/classic/getChallenge/123")).andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void postChallenge() throws Exception {
        String body = "{\n" +
                "  \"id\": 3,\n" +
                "  \"size\": 2,\n" +
                "  \"mission\": [[4,3],[5,6],[6,4],[3,2]]\n" +
                "}";
        mockMvc.perform(post("/classic/postChallenge")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}