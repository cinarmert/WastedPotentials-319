package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.logging.LoggingSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/daily")
public class DailyChallengeController {

    private static final Logger logger = LoggerFactory.getLogger(DailyChallengeController.class);

    @RequestMapping(value = "/getChallenge", method = RequestMethod.GET)
    @ResponseBody
    public String getChallenge() {
        return null;
    }

    @RequestMapping(value = "/postScore", method = RequestMethod.POST)
    @ResponseBody
    public void postScore(@RequestBody String body) {

    }

    @RequestMapping(value = "/getLeaderboard", method = RequestMethod.GET)
    @ResponseBody
    public String getLeaderboard() {
        return null;
    }

}