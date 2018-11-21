package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.logging.LoggingSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value = "/classic")
public class ClassicModeController {

    private static final Logger logger = LoggerFactory.getLogger(ClassicModeController.class);

    @RequestMapping(value = "/getChallenge/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getChallenge(@PathVariable("id") String id) {
        return null;
    }

}
