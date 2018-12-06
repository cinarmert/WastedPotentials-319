package kubitz.client.main;


import kubitz.client.gui.MainFrame;
import kubitz.client.util.JsonUtil;
import kubitz.client.websocket.KubitzWebSocketClient;
import kubitz.client.websocket.storage.ChangeSettingsMessage;
import kubitz.client.websocket.storage.LobbyMessage;
import kubitz.client.websocket.storage.LobbyMessageTypes;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        new MainFrame();
        //RESTRequestManager.login(new Account(45, "mert2cli"));

        KubitzWebSocketClient client = new KubitzWebSocketClient(new URI("ws://localhost:8083/player/33" ), new Draft_6455());


        LobbyMessage lobbyMessage = new LobbyMessage();
        lobbyMessage.setType(LobbyMessageTypes.LOBBY_CHANGE_SETTINGS);

        ChangeSettingsMessage changeSettingsMessage = new ChangeSettingsMessage();
        changeSettingsMessage.setGameMode("test");
        changeSettingsMessage.setLobbySize("6");
        changeSettingsMessage.setLobbyId("testLobby");
        changeSettingsMessage.setPlayerId("bentest");
        changeSettingsMessage.setPrivateLobby(false);
        changeSettingsMessage.setStatus("statutest");
        changeSettingsMessage.setPlayers(new ArrayList<>());


        lobbyMessage.setPayload(JsonUtil.toJson(changeSettingsMessage));
//        client.connectBlocking();

//        client.send(JsonUtil.toJson(lobbyMessage));
//        while (true){}
    }

}
