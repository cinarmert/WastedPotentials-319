package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

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
