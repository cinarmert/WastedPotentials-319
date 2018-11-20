package kubitz.client.rest;

import kubitz.client.response.Account;
import kubitz.client.util.JsonUtil;

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

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    private static Client client = ClientBuilder.newClient();

    public static void login(int id, String name){
        Account account = new Account(id, name);
        makeServerRequest(METHOD_POST, ACCOUNT_LOGIN, JsonUtil.toJson(account));
    }

    private static void makeServerRequest(String method, String path, String body){
        client.target(BASE_URL).path(path).request().post(Entity.json(body), String.class);
    }

}
