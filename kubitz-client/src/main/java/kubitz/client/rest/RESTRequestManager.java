package kubitz.client.rest;

import kubitz.client.gui.Config;
import kubitz.client.storage.*;
import kubitz.client.util.JsonUtil;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;


//ToDo to be tested
public class RESTRequestManager {

    private static final String BASE_URL = "http://" + Config.getServer();

    private static final String ACCOUNT_LOGIN = "/accounts/login";
    private static final String CHAT_GET_MSG = "/chat/getMessages/%s/%s";
    private static final String CHAT_POST_MSG = "/chat/postMessage";
    private static final String CLASSIC_GET_CHALLENGE = "/classic/getChallenge/%d";
    private static final String DAILY_GET_CHALLENGE = "/daily/getChallenge";
    private static final String DAILY_GET_LEADERBOARD = "/daily/getLeaderboard";
    private static final String DAILY_POST_SCORE = "/daily/postScore";
    private static final String LOBBY_CREATE_LOBBY = "/lobby/createLobby";
    private static final String LOBBY_CHANGE_SETTINGS = "/lobby/changeSettings";
    private static final String LOBBY_GET_LOBBIES = "/lobby/getLobbies";
    private static final String LOBBY_GET_LOBBYBYNAME = "/lobby/getLobbyByName/%s";
    private static final String LOBBY_KICK_PLAYER = "/lobby/kickPlayer";
    private static final String SWITCH_GET_GAME_STATE = "/switch/getGameState/%s";
    private static final String SWITCH_POST_GAME_STATE = "/switch/postGameState";

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    private static Client client = ClientBuilder.newClient();

    public static void login(Account account){
        makeServerRequest(METHOD_POST, ACCOUNT_LOGIN, JsonUtil.toJson(account));
    }

    public static Lobby getLobbyByName(String name){
        String urlFragment = String.format(LOBBY_GET_LOBBYBYNAME, name);
        String response = makeServerRequest(METHOD_GET, urlFragment, null);

        try {
            return JsonUtil.fromJson(response, Lobby.class);
        } catch (IOException e) {
            System.err.println("Could not parse the body to Lobby, storage: " + response);
            return null;
        }
    }

    public static List<Message> getMessages(Lobby lobby){
        String urlFragment = String.format(CHAT_GET_MSG, Config.getId(), lobby.getName());
        String response = makeServerRequest(METHOD_GET, urlFragment, null);

        try {
            return JsonUtil.fromListOfJson(response, Message.class);
        } catch (IOException e) {
            System.err.println("Could not parse the body to Message, storage: " + response);
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Storage class not found: " + Message.class.getName());
            return null;
        }
    }

    public static void postMesage(Message message){
        makeServerRequest(METHOD_POST, CHAT_POST_MSG, JsonUtil.toJson(message));
    }

    public static void createLobby(Lobby lobby){
        makeServerRequest(METHOD_POST, LOBBY_CREATE_LOBBY, JsonUtil.toJson(lobby));
    }

    public static void changeSettings(Lobby lobby){
        makeServerRequest(METHOD_POST, LOBBY_CHANGE_SETTINGS, JsonUtil.toJson(lobby));
    }

    public static List<Lobby> getLobbies(){
        String response = makeServerRequest(METHOD_GET, LOBBY_GET_LOBBIES, null);

        try {
            return JsonUtil.fromListOfJson(response, Lobby.class);
        } catch (IOException e) {
            System.err.println("Could not parse the storage to Lobby, storage: " + response);
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Storage class not found: " + Lobby.class.getName());
            return null;
        }
    }

    public static void kickPlayer(Lobby lobby){
        makeServerRequest(METHOD_POST, LOBBY_KICK_PLAYER, JsonUtil.toJson(lobby));
    }

    public static ClassicChallenge getClassicChallenge(int id){
        String urlFragment = String.format(CLASSIC_GET_CHALLENGE, id);
        String response = makeServerRequest(METHOD_GET, urlFragment, null);

        try {
            return JsonUtil.fromJson(response, ClassicChallenge.class);
        } catch (IOException e) {
            System.err.println("Could not parse the storage to ClassicChallenge, storage: " + response);
            return null;
        }
    }

    public static Leaderboard getDailyChallengeLeaderboard(){
        String response = makeServerRequest(METHOD_GET, DAILY_GET_LEADERBOARD, null);

        try {
            List<LeaderboardUser> leaderboardUserList = JsonUtil.fromListOfJson(response, LeaderboardUser.class);
            Leaderboard leaderboard = new Leaderboard();
            leaderboard.setLeaderboard(leaderboardUserList);
            return leaderboard;
        } catch (IOException e) {
            System.err.println("Could not parse the storage to Leaderboard, storage: " + response);
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Storage class not found: " + LeaderboardUser.class.getName());
            return null;
        }
    }

    public static void postDailyChallengeScore(LeaderboardUser user){
        makeServerRequest(METHOD_POST, DAILY_POST_SCORE, JsonUtil.toJson(user));
    }

    public static DailyChallenges getDailyChallenge(){
        String response = makeServerRequest(METHOD_GET, DAILY_GET_CHALLENGE, null);

        try {
            List<ClassicChallenge> classicChallengeList = JsonUtil.fromListOfJson(response, ClassicChallenge.class);
            DailyChallenges dailyChallenges = new DailyChallenges();
            dailyChallenges.setClassicChallenges(classicChallengeList);
            return dailyChallenges;
        } catch (IOException e) {
            System.err.println("Could not parse the storage to ClassicChallenge, storage: " + response);
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Storage class not found: " + ClassicChallenge.class.getName());
            return null;
        }
    }

    public static GameState getSwitchOpponentGameState(String stateId){
        String urlFragment = String.format(SWITCH_GET_GAME_STATE, stateId);
        String response = makeServerRequest(METHOD_GET, urlFragment, null);

        try {
            return JsonUtil.fromJson(response, GameState.class);
        } catch (IOException e) {
            System.err.println("Could not parse the storage to GameState, storage: " + response);
            return null;
        }
    }

    public static void postSwitchGameState(GameState gameState){
        makeServerRequest(METHOD_POST, SWITCH_POST_GAME_STATE, JsonUtil.toJson(gameState));
    }

    private static String makeServerRequest(String method, String path, String body){

        Invocation.Builder target = client.target(BASE_URL).path(path).request();
        Response response = null;

        switch (method) {
            case METHOD_GET:
                response = target.get();
                break;
            case METHOD_POST:
                response = target.post(Entity.json(body), Response.class);
                break;
        }

        if (response == null) {
            System.err.println("could not get storage from server");
            return null;
        } else if (response.getStatus() != Response.Status.OK.getStatusCode()){
            System.err.println("Got storage code: " + response.getStatus());
            return null;
        }

        return response.readEntity(String.class);
    }

    public static boolean checkServerConnection()
    {
        DailyChallenges challenge;
        try
        {
            challenge = getDailyChallenge(); //TODO: implement simple hello message instead
        }
        catch (Exception e)
        {
            return false;
        }
        return challenge != null;
    }

}
