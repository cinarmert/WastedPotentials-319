package kubitz.server.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/lobby")
public class LobbyController {

    @RequestMapping(value = "/getLobbies", method = RequestMethod.GET)
    @ResponseBody
    public String getLobbies() {
        return null;
    }

    @RequestMapping(value = "/kickPlayer", method = RequestMethod.POST)
    @ResponseBody
    public void kickPlayer(@RequestBody String body) {

    }

    @RequestMapping(value = "/changeSettings", method = RequestMethod.POST)
    @ResponseBody
    public void changeSettings(@RequestBody String body) {

    }

}
