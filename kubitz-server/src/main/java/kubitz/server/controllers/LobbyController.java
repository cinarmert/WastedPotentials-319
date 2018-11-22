package kubitz.server.controllers;

import kubitz.server.database.lobby.model.Lobby;
import kubitz.server.database.lobby.repository.LobbyRepository;
import kubitz.server.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RestController
@RequestMapping(value = "/lobby")
public class LobbyController {

    private static final Logger logger = LoggerFactory.getLogger(LobbyController.class);
    private final LobbyRepository lobbyRepository;

    @Autowired
    public LobbyController(LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    @RequestMapping(value = "/createLobby", method = RequestMethod.POST)
    @ResponseBody
    public void createLobby(@RequestBody String body) {
        Lobby lobby = null;
        try {
            lobby = JsonUtil.fromJson(body, Lobby.class);
        } catch (IOException e) {
            logger.error("could not parse the body to lobby, body: " + body);
            return;
        }
        lobbyRepository.save(lobby);
    }

    @RequestMapping(value = "/getLobbies", method = RequestMethod.GET)
    @ResponseBody
    public String getLobbies() {
        String response = JsonUtil.toJson(lobbyRepository.findAll());
        logger.info("returning the lobbies: " + response);
        return response;
    }

    @RequestMapping(value = "/kickPlayer", method = RequestMethod.POST)
    @ResponseBody
    public void kickPlayer(@RequestBody String body) {
        Lobby lobby = null;
        try {
            lobby = JsonUtil.fromJson(body, Lobby.class);
        } catch (IOException e) {
            logger.error("could not parse the body to lobby, body: " + body);
            return;
        }
        lobbyRepository.save(lobby);
    }

    @RequestMapping(value = "/changeSettings", method = RequestMethod.POST)
    @ResponseBody
    public void changeSettings(@RequestBody String body) {
        Lobby lobby = null;
        try {
            lobby = JsonUtil.fromJson(body, Lobby.class);
        } catch (IOException e) {
            logger.error("could not parse the body to lobby, body: " + body);
            return;
        }
        lobbyRepository.save(lobby);
    }

}
