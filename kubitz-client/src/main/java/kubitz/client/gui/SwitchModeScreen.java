package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.SwitchMode;
import kubitz.client.storage.Lobby;
import kubitz.client.websocket.WebSocketManager;

import javax.swing.*;
import java.awt.*;

public class SwitchModeScreen extends BaseGameScreen{

    public SwitchModeScreen( Dimension resolution, Lobby lobby) {
        super(resolution);
        this.requiresConnection = true;
        createGame(lobby);
    }

    @Override
    public void backButtonAction(){

        int quit = JOptionPane.showConfirmDialog( SwitchModeScreen.this,
                "Are you sure you want to leave the game?",
                "Leave?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if( quit == 0 ) {

            ScreenManager.doubleBack();

            setGame(null);
            MainFrame.getInstance().getMoveController().setBaseGameScreen(null);
        }

    }

    public void createGame(Lobby lobby){
        MainFrame.getInstance().getMoveController().setBaseGameScreen(this);

        SwitchMode sm = new SwitchMode(new Cube(0), lobby);
        setGame(sm);

        ((SwitchMode)getGame()).start();
    }


    public void setCard(int[][] card){
        Grid grid = new Grid(card.length);
        grid.setGrid(card);
        getGame().setCard(new Card(grid));
        updateCardUI();
    }

    @Override
    public void onGameFinished() {

        String winner = ((SwitchMode)getGame()).getWhoWon();

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
