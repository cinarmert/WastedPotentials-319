package kubitz.client.main;


import kubitz.client.gui.MainFrame;
import kubitz.client.util.JsonUtil;
import kubitz.client.websocket.KubitzWebSocketClient;
import kubitz.client.websocket.WebSocketManager;
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
        new WebSocketManager();
    }

}
