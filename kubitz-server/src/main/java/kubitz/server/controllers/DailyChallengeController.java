package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping(value = "/daily")
public class DailyChallengeController {

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