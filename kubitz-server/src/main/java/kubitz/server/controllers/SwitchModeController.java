package kubitz.server.controllers;

import kubitz.server.database.gamestate.model.GameState;
import kubitz.server.database.gamestate.repository.GameStateRepository;
import kubitz.server.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RestController
@RequestMapping(value = "/switch")
public class SwitchModeController {

    private static final Logger logger = LoggerFactory.getLogger(SwitchModeController.class);
    private final GameStateRepository gameStateRepository;

    @Autowired
    public SwitchModeController(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    @RequestMapping(value = "/getGameState/{opponentId}", method = RequestMethod.GET)
    @ResponseBody
    public String getGameState(@PathVariable("opponentId") int id) {
        String response = JsonUtil.toJson(gameStateRepository.findGameStateById(id));
        logger.info("returning the state: " + response);
        return response;
    }

    @RequestMapping(value = "/postGameState", method = RequestMethod.POST)
    @ResponseBody
    public void postGameState(@RequestBody String body) {
        GameState gameState = null;
        try {
            gameState = JsonUtil.fromJson(body, GameState.class);
        } catch (IOException e) {
            logger.error("could not parse the body to gamestate, body: " + body);
            return;
        }

        logger.info("saving to gamestaterepo: " + JsonUtil.toJson(gameState));
        gameStateRepository.save(gameState);
    }

}
