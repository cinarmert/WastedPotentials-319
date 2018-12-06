package kubitz.server.controllers;

import kubitz.server.database.accounts.repository.AccountRepository;
import kubitz.server.database.dailychallenge.model.DailyChallenge;
import kubitz.server.database.dailychallenge.repository.DailyChallengeRepository;
import kubitz.server.database.leaderboard.model.LeaderboardUser;
import kubitz.server.database.leaderboard.repository.LeaderboardRepository;
import kubitz.server.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/daily")
public class DailyChallengeController {

    private static final Logger logger = LoggerFactory.getLogger(DailyChallengeController.class);
    private final DailyChallengeRepository dailyChallengeRepository;
    private final LeaderboardRepository leaderboardRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DailyChallengeController(DailyChallengeRepository dailyChallengeRepository, LeaderboardRepository leaderboardRepository, AccountRepository accountRepository) {
        this.dailyChallengeRepository = dailyChallengeRepository;
        this.leaderboardRepository = leaderboardRepository;
        this.accountRepository = accountRepository;
    }


    @RequestMapping(value = "/getChallenge", method = RequestMethod.GET)
    @ResponseBody
    public String getChallenge() {
        String response = JsonUtil.toJson(dailyChallengeRepository.findAll());
        logger.info("returning the daily challenges: " + response);
        return response;
    }

    @RequestMapping(value = "/postScore", method = RequestMethod.POST)
    @ResponseBody
    public void postScore(@RequestBody String body) {
        LeaderboardUser user = null;
        try {
            user = JsonUtil.fromJson(body, LeaderboardUser.class);
        } catch (IOException e) {
            logger.error("could not parse the body to leaderboard user, body: " + body);
            return;
        }

        logger.info("saving to leaderboard repo: " + JsonUtil.toJson(user));
        leaderboardRepository.save(user);
    }

    @RequestMapping(value = "/getLeaderboard", method = RequestMethod.GET)
    @ResponseBody
    public String getLeaderboard() {
        List<LeaderboardUser> leaderboardUsers = leaderboardRepository.findAll();

        for (LeaderboardUser leaderboardUser : leaderboardUsers) {
            if (accountRepository.findAccountById(leaderboardUser.getId()).getName() != null) {
                leaderboardUser.setName(accountRepository.findAccountById(leaderboardUser.getId()).getName());
            }
        }

        String response = JsonUtil.toJson(leaderboardUsers);
        logger.info("returning the leaderboard: " + response);
        return response;
    }

    @RequestMapping(value = "/postDailyChallenge", method = RequestMethod.POST)
    @ResponseBody
    public void postChallenge(@RequestBody String body) {
        DailyChallenge dailyChallenge = null;
        try {
            dailyChallenge = JsonUtil.fromJson(body, DailyChallenge.class);
        } catch (IOException e) {
            logger.error("could not parse the body to daily challenge, body: " + body);
            return;
        }

        logger.info("saving to daily challenge repo: " + JsonUtil.toJson(dailyChallenge));
        dailyChallengeRepository.save(dailyChallenge);
    }

}