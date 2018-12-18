package kubitz.server.websocket.handlers;

import kubitz.server.database.accounts.model.Account;
import kubitz.server.database.chat.repository.ChatRepository;
import kubitz.server.database.gamestate.repository.GameStateRepository;
import kubitz.server.database.lobby.model.Lobby;
import kubitz.server.database.lobby.repository.LobbyRepository;
import kubitz.server.util.JsonUtil;
import kubitz.server.util.RandomUtil;
import kubitz.server.websocket.storage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class LobbyWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(LobbyWebSocketHandler.class);
    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final LobbyRepository lobbyRepository;
    private final ChatRepository chatRepository;
    private final GameStateRepository gameStateRepository;

    @Autowired
    public LobbyWebSocketHandler(LobbyRepository lobbyRepository, ChatRepository chatRepository, GameStateRepository gameStateRepository) {
        this.lobbyRepository = lobbyRepository;
        this.chatRepository = chatRepository;
        this.gameStateRepository = gameStateRepository;
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        logger.error("error occured at sender " + session, throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        logger.info("Connected ... " + session.getId());
//        session.sendMessage(new TextMessage("Hello " + ("name") + " !"));

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonTextMessage) throws Exception {
        LobbyMessage lm = JsonUtil.fromJson(jsonTextMessage.getPayload(), LobbyMessage.class);
        logger.info("got message with type: " + lm.getType());
        logger.info("message payload: " + lm.getPayload());

        if(lm.isChangeSettingsMessage())             handleChangeSettingsMessage(lm, session);
        else if(lm.isChatMessage())                  handleChatMessage(lm, session);
        else if(lm.isFinishGameMessage())            handleFinishGameMessage(lm, session);
        else if(lm.isInviteMessage())                handleInviteMessage(lm, session);
        else if(lm.isJoinMessage())                  handleJoinMessage(lm, session);
        else if(lm.isKickMessage())                  handleKickMessage(lm, session);
        else if(lm.isLeaveMessage())                 handleLeaveMessage(lm, session);
        else if(lm.isStartGameMessage())             handleStartGameMessage(lm, session);
        else if(lm.isStateMessage())                 handleStateMessage(lm, session);
    }

    private void handleStartGameMessage(LobbyMessage lm, WebSocketSession session) throws IOException {
        StartGameMessage startGameMessage = JsonUtil.fromJson(lm.getPayload().toString(), StartGameMessage.class);
        logger.info("got start message, " + startGameMessage.getPlayerId() + " is starting the game: " + startGameMessage.getLobbyId());

        Lobby lobby = lobbyRepository.findLobbyByName(startGameMessage.getLobbyId());
        //ToDo get size from lobby properties
        startGameMessage.setCard(RandomUtil.getRandomCard(4));
        notifyLobbyParticipants(lobby, JsonUtil.toJson(startGameMessage), LobbyMessageTypes.LOBBY_START_GAME_MSG);
    }

    private void handleStateMessage(LobbyMessage lm, WebSocketSession session) throws IOException {
        StateMessage stateMessage = JsonUtil.fromJson(lm.getPayload().toString(), StateMessage.class);
        if (stateMessage == null)
            return;
        Lobby lobby = lobbyRepository.findLobbyByName(stateMessage.getLobbyId());

        logger.info("player " + stateMessage.getPlayerId() + " in lobby " + stateMessage.getLobbyId() + " is ready to switch");

        notifyLobbyParticipants(lobby, JsonUtil.toJson(stateMessage), LobbyMessageTypes.LOBBY_STATE_MESSAGE);

    }

    private void handleFinishGameMessage(LobbyMessage lm, WebSocketSession session) throws IOException {
        FinishGameMessage finishGameMessage = JsonUtil.fromJson(lm.getPayload().toString(), FinishGameMessage.class);
        if(finishGameMessage == null) return;

        Lobby lobby = lobbyRepository.findLobbyByName(finishGameMessage.getLobbyId());
        logger.info("player " + finishGameMessage.getPlayerId() + " in lobby " + finishGameMessage.getLobbyId() + " finished game in " + finishGameMessage.getFinishTime());

        notifyLobbyParticipants(lobby, JsonUtil.toJson(finishGameMessage), LobbyMessageTypes.LOBBY_FINISH_GAME);
    }

    private void handleLeaveMessage(LobbyMessage lm, WebSocketSession session) throws IOException {
        LeaveMessage leaveMessage = JsonUtil.fromJson(lm.getPayload().toString(), LeaveMessage.class);
        logger.info("got leave message, " + leaveMessage.getPlayerId() + " is leaving from the lobby " + leaveMessage.getLobbyId());

        Lobby lobbyToLeave = lobbyRepository.findLobbyByName(leaveMessage.getLobbyId());
        lobbyToLeave.removePlayer(leaveMessage.getAccount());

        if (lobbyToLeave.getPlayerCount() == 0) {
            logger.info("no players in the lobby, deleting... lobbyId: " + leaveMessage.getLobbyId());
            lobbyRepository.delete(lobbyToLeave);
        } else {
            lobbyRepository.save(lobbyToLeave);
            notifyLobbyParticipants(lobbyToLeave, JsonUtil.toJson(leaveMessage), LobbyMessageTypes.LOBBY_LEAVE_MESSAGE);
        }

    }

    private void handleKickMessage(LobbyMessage lm, WebSocketSession session) throws IOException {
        KickMessage kickMessage = JsonUtil.fromJson(lm.getPayload().toString(), KickMessage.class);
        logger.info("got kick message, " + kickMessage.getPlayerId() + " is being kicked from the lobby " + kickMessage.getLobbyId());

        if (kickMessage.getAccountToKick() == null) return;

        Lobby lobby = lobbyRepository.findLobbyByName(kickMessage.getLobbyId());
        notifyLobbyParticipants(lobby, JsonUtil.toJson(kickMessage), LobbyMessageTypes.LOBBY_KICK_MESSAGE);

        lobby.removePlayer(kickMessage.getAccountToKick());
        lobbyRepository.save(lobby);
    }

    private void handleJoinMessage(LobbyMessage lm, WebSocketSession session) throws IOException {
        JoinMessage joinMessage = JsonUtil.fromJson(lm.getPayload().toString(), JoinMessage.class);
        logger.info("got join message, " + joinMessage.getPlayerId() + " is joining the lobby " + joinMessage.getLobbyId());

        Lobby lobbyToJoin = lobbyRepository.findLobbyByName(joinMessage.getLobbyId());
        if (lobbyToJoin.isFull()) {
            logger.info("the lobby " + joinMessage.getLobbyId() + " is full, player " + joinMessage.getPlayerId() + " cannot join the lobby");
//            String errorMsg = JsonUtil.toJson(new WebSocketResponseMessage(false));
            session.sendMessage(getTextLobbyMessage(null, LobbyMessageTypes.LOBBY_AS_RESPONSE));
        } else {
            lobbyToJoin.addPlayer(joinMessage.getAccount());
            lobbyRepository.save(lobbyToJoin);
            notifyLobbyParticipants(lobbyToJoin, JsonUtil.toJson(joinMessage), LobbyMessageTypes.LOBBY_JOIN_MESSAGE);
        }

    }

    private void handleInviteMessage(LobbyMessage lm, WebSocketSession session) throws IOException {
        InviteMessage inviteMessage = JsonUtil.fromJson(lm.getPayload().toString(), InviteMessage.class);
        logger.info("got invite message, " + inviteMessage.getInvitedPlayerId() + " is inviting " + inviteMessage.getPlayerId() + " to the lobby " + inviteMessage.getLobbyId());

        session = getSessionByPlayerId(inviteMessage.getInvitedPlayerId());
        if (session != null){
            logger.info("sending invite to the player " + inviteMessage.getInvitedPlayerId());
            session.sendMessage(getTextLobbyMessage(JsonUtil.toJson(inviteMessage), LobbyMessageTypes.LOBBY_INVITE_MESSAGE));
        }

    }

    private void handleChatMessage(LobbyMessage lm, WebSocketSession session) throws IOException {
        ChatMessage chatMessage = JsonUtil.fromJson(lm.getPayload().toString(), ChatMessage.class);
        logger.info("got chat message from player " + chatMessage.getPlayerId() + " in lobby " + chatMessage.getLobbyId());

        Lobby lobby = lobbyRepository.findLobbyByName(chatMessage.getLobbyId());
        notifyLobbyParticipants(lobby, JsonUtil.toJson(chatMessage), LobbyMessageTypes.LOBBY_CHAT_MESSAGE);
    }

    private void handleChangeSettingsMessage(LobbyMessage lm, WebSocketSession session) throws IOException {
        ChangeSettingsMessage changeSettingsMessage = JsonUtil.fromJson(lm.getPayload().toString(), ChangeSettingsMessage.class);
        logger.info("got change settings message for lobby " + changeSettingsMessage.getLobbyId());

        lobbyRepository.save(changeSettingsMessage.getLobby());
        notifyLobbyParticipants(changeSettingsMessage.getLobby(), JsonUtil.toJson(changeSettingsMessage), LobbyMessageTypes.LOBBY_CHANGE_SETTINGS);
    }


    private void notifyLobbyParticipants(Lobby lobby, String payload, String type) throws IOException {
        ArrayList<Account> participants = lobby.getPlayers();
        for (Account player : participants){
            for (WebSocketSession session : sessions) {
                logger.info(getPlayerId(session.getUri().getPath()));
                if (player.getId().equals(getPlayerId(session.getUri().getPath()))){
                    logger.info("sending message to " + player.getId());
                    try {
                        session.sendMessage(getTextLobbyMessage(payload, type));
                    } catch( Exception ex ) {
                        logger.warn( "could not send message to id: ", session.getAttributes().get("playerId") );
                        sessions.remove( session );

                    }
                }
            }
        }
    }

    private TextMessage getTextLobbyMessage(String payload, String type) {
        return new TextMessage(JsonUtil.toJson(new LobbyMessage(payload, type)));
    }

    private WebSocketSession getSessionByPlayerId(String playerId){
        for(WebSocketSession s : sessions){
            if (s.getUri() == null) continue;
            if(playerId.equals(getPlayerId(s.getUri().getPath()))){
                return s;
            }
        }
        return null;
    }

    private String getPlayerId(String s){
        return s.substring(s.lastIndexOf('/') + 1);
    }
}

