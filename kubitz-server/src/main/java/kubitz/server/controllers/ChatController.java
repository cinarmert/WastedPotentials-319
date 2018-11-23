package kubitz.server.controllers;

import kubitz.server.database.chat.model.Message;
import kubitz.server.database.chat.repository.ChatRepository;
import kubitz.server.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @RequestMapping(value = "/getMessages/{ownId}/{lobbyId}", method = RequestMethod.GET)
    @ResponseBody
    public String getMessages(@PathVariable("ownId") String userId, @PathVariable("lobbyId") String lobbyId) {
        return JsonUtil.toJson(chatRepository.findAllByLobbyId(lobbyId));
    }

    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    @ResponseBody
    public void postMessage(@RequestBody String body) {
        Message message = null;
        try {
             message = JsonUtil.fromJson(body, Message.class);
        } catch (IOException e) {
            logger.error("Could not parse body to message, body: " + body);
            return;
        }
        chatRepository.save(message);
    }

}
