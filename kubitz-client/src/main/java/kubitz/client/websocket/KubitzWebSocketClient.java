package kubitz.client.websocket;

import kubitz.client.main.Message;
import kubitz.client.storage.Lobby;
import kubitz.client.util.JsonUtil;
import kubitz.client.websocket.storage.*;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;

public class KubitzWebSocketClient extends WebSocketClient {

    public KubitzWebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    @Override
    public void onOpen( ServerHandshake handshake ) {
        System.out.println( "opened connection" );
//        send("Selam moruk");
    }

    @Override
    public void onMessage(String message) {
        try {
            LobbyMessage lm = JsonUtil.fromJson(message, LobbyMessage.class);

            if(lm.isChangeSettingsMessage())             handleChangeSettingsMessage(lm);
            else if(lm.isChatMessage())                  handleChatMessage(lm);
            else if(lm.isFinishGameMessage())            handleFinishGameMessage(lm);
            else if(lm.isInviteMessage())                handleInviteMessage(lm);
            else if(lm.isJoinMessage())                  handleJoinMessage(lm);
            else if(lm.isKickMessage())                  handleKickMessage(lm);
            else if(lm.isLeaveMessage())                 handleLeaveMessage(lm);
            else if(lm.isStartGameMessage())             handleStartGameMessage(lm);
            else if(lm.isStateMessage())                 handleStateMessage(lm);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Message obj = null;
//        try {
//            obj = JsonUtil.fromJson(message, Message.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.print(obj.getPayload());
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        System.out.println( "closed connection" );
    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
    }

    private void handleStartGameMessage(LobbyMessage lm) throws IOException {
        StartGameMessage startGameMessage = JsonUtil.fromJson(lm.getPayload().toString(), StartGameMessage.class);

    }

    private void handleStateMessage(LobbyMessage lm) throws IOException {
        StateMessage stateMessage = JsonUtil.fromJson(lm.getPayload().toString(), StateMessage.class);

    }

    private void handleFinishGameMessage(LobbyMessage lm) throws IOException {
        FinishGameMessage finishGameMessage = JsonUtil.fromJson(lm.getPayload().toString(), FinishGameMessage.class);

    }

    private void handleLeaveMessage(LobbyMessage lm) throws IOException {
        LeaveMessage leaveMessage = JsonUtil.fromJson(lm.getPayload().toString(), LeaveMessage.class);

    }

    private void handleKickMessage(LobbyMessage lm) throws IOException {
        KickMessage kickMessage = JsonUtil.fromJson(lm.getPayload().toString(), KickMessage.class);

    }

    private void handleJoinMessage(LobbyMessage lm) throws IOException {
        JoinMessage joinMessage = JsonUtil.fromJson(lm.getPayload().toString(), JoinMessage.class);

    }

    private void handleInviteMessage(LobbyMessage lm) throws IOException {
        InviteMessage inviteMessage = JsonUtil.fromJson(lm.getPayload().toString(), InviteMessage.class);

    }

    private void handleChatMessage(LobbyMessage lm) throws IOException {
        ChatMessage chatMessage = JsonUtil.fromJson(lm.getPayload().toString(), ChatMessage.class);

    }

    private void handleChangeSettingsMessage(LobbyMessage lm) throws IOException {
        ChangeSettingsMessage changeSettingsMessage = JsonUtil.fromJson(lm.getPayload().toString(), ChangeSettingsMessage.class);

    }
}
