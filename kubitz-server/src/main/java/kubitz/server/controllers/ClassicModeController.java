package kubitz.server.controllers;

import kubitz.server.database.challenges.repository.ChallengeRepository;
import kubitz.server.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value = "/classic")
public class ClassicModeController {

    private static final Logger logger = LoggerFactory.getLogger(ClassicModeController.class);
    private final ChallengeRepository challengeRepository;

    @Autowired
    public ClassicModeController(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @RequestMapping(value = "/getChallenge/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getChallenge(@PathVariable("id") int id) {
        String response = JsonUtil.toJson(challengeRepository.findChallengeById(id));
        logger.info("returning the challenge: " + response);
        return response;
    }

}
