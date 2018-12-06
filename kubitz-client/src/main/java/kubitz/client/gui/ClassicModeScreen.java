package kubitz.client.gui;

import kubitz.client.components.Cube;
import kubitz.client.logic.ClassicMode;
import kubitz.client.storage.Lobby;

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

            setGame(null);
            MainFrame.getInstance().getMoveController().setBaseGameScreen(null);
        }

    }

    public void createGame(Lobby lobby){
        MainFrame.getInstance().getMoveController().setBaseGameScreen(this);

        ClassicMode cm = new ClassicMode(new Cube(0), lobby);
        setGame(cm);
    }

    @Override
    public void onGameFinished() {

        // ToDo get who wins
        if( true)
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
