package kubitz.server.controllers;

import kubitz.server.database.lobby.model.Lobby;
import kubitz.server.database.lobby.model.LobbyStatus;
import kubitz.server.database.lobby.repository.LobbyRepository;
import kubitz.server.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.server.PathParam;
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

    @RequestMapping(value = "/getLobbyByName/{lobbyname}", method = RequestMethod.GET)
    @ResponseBody
    public String getLobbyByName(@PathParam("lobbyname") String lobbyname) {
        String response = JsonUtil.toJson(lobbyRepository.findLobbyById(lobbyname));
        logger.info("returning the lobbies: " + response);
        return response;
    }

    @RequestMapping(value = "/canJoinLobby/{lobbyname}", method = RequestMethod.GET)
    @ResponseBody
    public String canJoinLobby(@PathParam("lobbyname") String lobbyname) {
        Lobby lobby = lobbyRepository.findLobbyById(lobbyname);
        logger.info("found lobby for lobby name " + lobbyname + ": " + JsonUtil.toJson(lobby));
        boolean isFull = lobby.isFull();
        boolean isPrivate = lobby.isPrivateLobby();
        boolean isPlaying = LobbyStatus.PLAYING.equals(lobby.getStatus());
        String reason = "OK";
        if(isFull)
        {
            reason = "Lobby is full";
        }

        if(isPrivate)
        {
            reason = "Lobby is private";
        }

        if(isPlaying)
        {
            reason = "Lobby is in a game";
        }

        LobbyJoinResponseMessage message = new LobbyJoinResponseMessage(!(isFull || isPlaying || isPrivate), reason);
        logger.info("returning the lobby join response msg: " + JsonUtil.toJson(message) + "for " + message + "and lobby " + JsonUtil.toJson(lobby));
        return JsonUtil.toJson(message);
    }

}
