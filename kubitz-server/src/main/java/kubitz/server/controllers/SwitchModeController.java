package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/switch")
public class SwitchModeController {

    private static final Logger logger = LoggerFactory.getLogger(SwitchModeController.class);

    @RequestMapping(value = "/getGameState/{opponentId}", method = RequestMethod.GET)
    @ResponseBody
    public String getGameState(@PathVariable("opponentId") String id) {
        return null;
    }

    @RequestMapping(value = "/postGameState", method = RequestMethod.POST)
    @ResponseBody
    public void postGameState(@RequestBody String body) {

    }

}
