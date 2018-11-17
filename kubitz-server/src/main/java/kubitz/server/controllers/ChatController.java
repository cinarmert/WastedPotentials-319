package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    @RequestMapping(value = "/getMessages/{ownId}/{lobbyId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMessages(@PathVariable("ownId") String userId, @PathVariable("lobbyId") String lobbyId) {
        return null;
    }

    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    @ResponseBody
    public void postMessage(@RequestBody Map<String, Object> body) {

    }

}
