package kubitz.client.gui;

import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;
import kubitz.client.logic.SurvivalMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurvivalModeScreen extends BaseGameScreen {

    private JLabel time;
    private Timer timer;
    private JPanel timerPanel;

    public SurvivalModeScreen( Dimension resolution) {

        super(resolution);

        time = new JLabel("00:00:00");
        time.setForeground(Color.BLACK);
        time.setFont( new Font( time.getFont().getName(), Font.PLAIN, 50));

        timerPanel = new JPanel();
        timerPanel.setLayout( new FlowLayout( FlowLayout.LEFT));
        timerPanel.setPreferredSize( new Dimension(300,80));
        timerPanel.setOpaque(false);

        createGame();
    }

    public void startTimer(){

        ((SurvivalMode)getGame()).startCountDown();

        timer = new Timer( 1000/60,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                long timePassedLong = ((SurvivalMode)getGame()).getTime();
                String timePassed = (timePassedLong / (1000 * 60)) % 60 + " : "+ ((timePassedLong / 1000) % 60)  + " : " + timePassedLong % 1000;

                time.setText( timePassed);

            }
        });
        timer.start();
    }

    public void createGame(){
        MainFrame.getInstance().getMoveController().setBaseGameScreen(this);

        SurvivalMode sm = new SurvivalMode(new Cube(0), this::gameFinished );
        setGame(sm);
        startTimer();
    }

    @Override
    public void setGame(BaseGame game){
        super.setGame(game);
        timerPanel.add(time);
        super.cardPanel.add(timerPanel, 0);

    }

    @Override
    public void backButtonAction(){
        int quit = JOptionPane.showConfirmDialog( this,
                "Are you sure you want to leave the game?",
                "Leave?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if( quit == 0 ) {

            super.backButtonAction();

            timer.stop();
            setGame(null);
            MainFrame.getInstance().getMoveController().setBaseGameScreen(null);
        }

    }

    @Override
    public void onGameFinished() {

        if (getGame().isGameFinished()){

            ((SurvivalMode)getGame()).stopCountDown();

            ((SurvivalMode)getGame()).createNewChallenge();

            cardUI.setCard( getGame().getCard() );
            gridUI.resetGrid();

            gridUI.repaint();
            cardUI.repaint();

            repaint();
        }
    }


    public Void gameFinished( Void v){

        timer.stop();

        time.setText("0 : 0 : 0");

        JOptionPane.showMessageDialog( this,
                "Your score is : " + ((SurvivalMode)getGame()).getScore(),
                "GAME OVER",
                JOptionPane.INFORMATION_MESSAGE);

        int newGame = JOptionPane.showConfirmDialog( this,
                "New game?",
                "GAME OVER",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if( newGame == 0 ) {
            createGame();
        }
        else {
            super.backButtonAction();
            setGame(null);
            MainFrame.getInstance().getMoveController().setBaseGameScreen(null);
        }

        return null;
    }
}

