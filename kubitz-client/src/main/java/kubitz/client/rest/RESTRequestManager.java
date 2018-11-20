package kubitz.client.rest;

import kubitz.client.response.Account;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class RESTRequestManager {

    private static final String BASE_URL = "http://127.0.0.1:8083";

    private static final String ACCOUNT_LOGIN = "/accounts/login";
    private static final String CHAT_GET_MSG = "/chat/getMessages/%s/%s";
    private static final String CHAT_POST_MSG = "/chat/postMessage";
    private static final String CLASSIC_GET_CHALLENGE = "/classic/getChallenge/%s";
    private static final String DAILY_GET_CHALLENGE = "/daily/getChallenge";
    private static final String DAILY_GET_LEADERBOARD = "/daily/getLeaderboard";
    private static final String DAILY_POST_SCORE = "/daily/postScore";
    private static final String LOBBY_CHANGE_SETTINGS = "/lobby/changeSettings";
    private static final String LOBBY_GET_LOBBIES = "/lobby/getLobbies";
    private static final String LOBBY_KICK_PLAYER = "/lobby/kickPlayer";
    private static final String SWITCH_GET_GAME_STATE = "/switch/getGameState/%s";
    private static final String SWITCH_POST_GAME_STATE = "/switch/postGameState";

    private static Client client = ClientBuilder.newClient();

    public static boolean login(int id, String name){
        Account account = new Account(id, name);
        Account a = client.target(BASE_URL).path(ACCOUNT_LOGIN).request().post(Entity.entity(account, MediaType.APPLICATION_JSON), Account.class);
        return a != null;
    }

}
