package kubitz.server.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "/classic")
public class ClassicModeController {

    @RequestMapping(value = "/getChallenge/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getChallenge(@PathVariable("id") String id) {
        return null;
    }

}
