package kubitz.client.gui;

import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;
import kubitz.client.logic.SwitchMode;
import kubitz.client.storage.Lobby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchModeScreen extends BaseGameScreen{

    private JPanel contentPane;

    public SwitchModeScreen(JPanel contentPane, Dimension size) {
        super(size);
        this.contentPane = contentPane;
    }

    @Override
    public void setGame( BaseGame classicMode){
        super.setGame(classicMode);

        addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int quit = JOptionPane.showConfirmDialog( SwitchModeScreen.this,
                        "Are you sure you want to leave the game?",
                        "Leave?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if( quit == 0 ) {
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.show(contentPane, MainFrame.LOBBIES);
                    setGame(null);
                    MainFrame.getInstance().getMoveController().setBaseGameScreen(null);
                }

            }
        });
    }

    public void createGame(Lobby lobby){
        MainFrame.getInstance().getMoveController().setBaseGameScreen(this);

        SwitchMode sm = new SwitchMode(new Grid(4), new Cube(0), lobby, this::switchGames );
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
    public void update() {

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


        // ToDO go to the lobby

    }
}
