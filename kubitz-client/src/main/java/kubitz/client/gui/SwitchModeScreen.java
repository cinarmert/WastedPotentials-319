package kubitz.client.gui;

import kubitz.client.components.Cube;
import kubitz.client.logic.SwitchMode;
import kubitz.client.storage.Lobby;

import javax.swing.*;
import java.awt.*;

public class SwitchModeScreen extends BaseGameScreen{

    public SwitchModeScreen( Dimension resolution, Lobby lobby) {
        super(resolution);
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

        SwitchMode sm = new SwitchMode(new Cube(0), lobby, this::switchGames );
        setGame(sm);

        ((SwitchMode)getGame()).start();
    }

    public Void switchGames( Void v){

        ((SwitchMode)getGame()).stop();
        //setGame( ((SwitchMode)getGame()).getOtherGame() );
        ((SwitchMode)getGame()).start();
        return null;

    }

    @Override
    public void onGameFinished() {
        ((SwitchMode)getGame()).stop();

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
