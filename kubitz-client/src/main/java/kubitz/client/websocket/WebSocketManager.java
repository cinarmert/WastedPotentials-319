package kubitz.client.websocket;

import kubitz.client.gui.Config;
import kubitz.client.util.JsonUtil;
import kubitz.client.websocket.storage.JoinMessage;
import org.java_websocket.drafts.Draft_6455;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketManager {

    private static KubitzWebSocketClient client;

    public WebSocketManager(){
        try {
            client = new KubitzWebSocketClient(new URI("ws://localhost:8083/player/" + Config.getId()), new Draft_6455());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void sendJoinLobbyMessage(String lobbyId){
        JoinMessage joinMessage = new JoinMessage(Config.getId(), lobbyId, Config.getAccount());
        client.send(JsonUtil.toJson(joinMessage));
    }

}
