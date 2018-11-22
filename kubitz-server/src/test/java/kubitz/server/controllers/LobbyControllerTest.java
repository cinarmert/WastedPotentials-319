package kubitz.server.controllers;

import kubitz.server.database.lobby.model.Lobby;
import kubitz.server.util.JsonUtil;
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
public class LobbyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    private Lobby lobby;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        lobby = new Lobby("4", "test", "Switch", false);
        lobby.setStatus("Waiting");
        lobby.setNoOfPlayers(4);
    }

    @Test
    public void createLobby() throws Exception {
        String body = JsonUtil.toJson(lobby);

        mockMvc.perform(post("/lobby/createLobby")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getLobbies() throws Exception {
        mockMvc.perform(get("/lobby/getLobbies")).andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void kickPlayer() throws Exception {
        lobby.setNoOfPlayers(3);
        lobby.setId("3");
        String body = JsonUtil.toJson(lobby);

        mockMvc.perform(post("/lobby/createLobby")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void changeSettings() throws Exception {
        lobby.setMode("Classic");
        lobby.setId("2");
        String body = JsonUtil.toJson(lobby);

        mockMvc.perform(post("/lobby/createLobby")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}