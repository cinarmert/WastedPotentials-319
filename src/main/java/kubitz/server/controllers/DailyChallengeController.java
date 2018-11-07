package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping(value = "/daily")
public class DailyChallengeController {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/getChallenge", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getChallenge() {
        return null;
    }

    @RequestMapping(value = "/postScore", method = RequestMethod.POST)
    @ResponseBody
    public void postScore(@RequestBody Map<String, Object> body) {

    }

    @RequestMapping(value = "/getLeaderboard", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getLeaderboard() {
        return null;
    }

}