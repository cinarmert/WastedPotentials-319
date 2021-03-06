package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.ClassicMode;
import kubitz.client.storage.Lobby;
import kubitz.client.websocket.WebSocketManager;

import javax.swing.*;
import java.awt.*;

public class ClassicModeScreen extends BaseGameScreen{



    public ClassicModeScreen(Dimension resolution, Lobby lobby) {
        super( resolution);
        this.requiresConnection = true;
        createGame(lobby);
    }

    @Override
    public void backButtonAction(){
        int quit = JOptionPane.showConfirmDialog( ClassicModeScreen.this,
                "Are you sure you want to leave the game?",
                "Leave?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if( quit == 0 ) {

            ScreenManager.doubleBack();

            LobbyScreen lobbyScreen = ((LobbyScreen)ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN));

            WebSocketManager.sendLeaveLobbyMessage(lobbyScreen.getCurrentLobby().getId());
            lobbyScreen.setCurrentLobby(null);
            setGame(null);
            MainFrame.getInstance().getMoveController().setBaseGameScreen(null);

            if (ScreenManager.getCurrentScreen() instanceof CreateLobbyScreen){
                ScreenManager.back();
            }
        }

    }

    public void createGame(Lobby lobby){
        MainFrame.getInstance().getMoveController().setBaseGameScreen(this);

        ClassicMode cm = new ClassicMode(new Cube(0), lobby);
        setGame(cm);
    }

    public void setCard(int[][] card){
        Grid grid = new Grid(card.length);
        grid.setGrid(card);
        getGame().setCard(new Card(grid));
        updateCardUI();
    }

    @Override
    public void onGameFinished() {

        String winner = ((ClassicMode)getGame()).getWhoWon();

        if( winner.equals(Config.getId()))
        JOptionPane.showMessageDialog( this,
                "You win!",
                "GAME OVER",
                JOptionPane.INFORMATION_MESSAGE);
        else
        JOptionPane.showMessageDialog( this,
                "You lose!",
                "GAME OVER",
                JOptionPane.INFORMATION_MESSAGE);

        ScreenManager.back();

    }
}
