package kubitz.client.gui;

import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;
import kubitz.client.logic.ClassicMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassicModeScreen extends BaseGameScreen {

    private JPanel contentPane;

    public ClassicModeScreen(JPanel contentPane, Dimension size) {
        super( size);
        this.contentPane = contentPane;
    }

    @Override
    public void setGame( BaseGame classicMode){
        super.setGame(classicMode);

        addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int quit = JOptionPane.showConfirmDialog( ClassicModeScreen.this,
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

    public void createGame(){
        ClassicMode cm = new ClassicMode(new Grid(4), new Cube(0));
        setGame(cm);
    }

    @Override
    public void update() {

    }

    @Override
    public void onGameFinished() {

    }
}
