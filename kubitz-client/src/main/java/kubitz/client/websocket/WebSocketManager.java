package kubitz.client.websocket;

import kubitz.client.gui.Config;
import kubitz.client.util.JsonUtil;
import kubitz.client.websocket.storage.JoinMessage;
import kubitz.client.websocket.storage.LobbyMessage;
import kubitz.client.websocket.storage.LobbyMessageTypes;
import org.java_websocket.drafts.Draft_6455;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketManager {

    public static KubitzWebSocketClient client;

    public WebSocketManager(){
        try {
            client = new KubitzWebSocketClient(new URI("ws://40.89.169.29:8083/player/" + "101"), new Draft_6455());
            client.connectBlocking();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendJoinLobbyMessage(String lobbyId){
        JoinMessage joinMessage = new JoinMessage(Config.getId(), lobbyId, Config.getAccount());
        LobbyMessage lm = new LobbyMessage();
        lm.setPayload(JsonUtil.toJson(joinMessage));
        lm.setType(LobbyMessageTypes.LOBBY_JOIN_MESSAGE);
        client.send(JsonUtil.toJson(joinMessage));
    }

}
