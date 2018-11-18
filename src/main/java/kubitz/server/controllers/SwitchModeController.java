package kubitz.server.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/switch")
public class SwitchModeController {

    @RequestMapping(value = "/getGameState/{opponentId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getGameState(@PathVariable("opponentId") String id) {
        return null;
    }

    @RequestMapping(value = "/postGameState", method = RequestMethod.POST)
    @ResponseBody
    public void postGameState(@RequestBody Map<String, Object> body) {

    }

}
