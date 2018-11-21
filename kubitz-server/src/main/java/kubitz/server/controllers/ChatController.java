package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.logging.LoggingSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @RequestMapping(value = "/getMessages/{ownId}/{lobbyId}", method = RequestMethod.GET)
    @ResponseBody
    public String getMessages(@PathVariable("ownId") String userId, @PathVariable("lobbyId") String lobbyId) {
        return null;
    }

    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    @ResponseBody
    public void postMessage(@RequestBody String body) {

    }

}
