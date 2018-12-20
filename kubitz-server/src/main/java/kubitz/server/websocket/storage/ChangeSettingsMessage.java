package kubitz.server.websocket.storage;

import kubitz.server.database.lobby.model.Lobby;

public class ChangeSettingsMessage {

    private Lobby lobby;

    public ChangeSettingsMessage(){}

    public Lobby getLobby(){
        return lobby;
    }

    public void setLobby(Lobby lobby){
        this.lobby = lobby;
    }
}
