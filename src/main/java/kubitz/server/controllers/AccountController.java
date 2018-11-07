package kubitz.server.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/classic")
public class AccountController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getChallenge(@RequestBody Map<String, Object> body) {
        return null;
    }

}
