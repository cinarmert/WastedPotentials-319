package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/lobby")
public class LobbyController {

    private static final Logger logger = LoggerFactory.getLogger(LobbyController.class);

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
