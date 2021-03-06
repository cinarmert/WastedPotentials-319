package kubitz.client.gui;

import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;
import kubitz.client.logic.DailyChallengeMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DailyChallengeScreen extends BaseGameScreen{

    private JLabel time;
    private Timer timer;
    private JPanel timerPanel;

    public DailyChallengeScreen( Dimension resolution) {
        super(resolution);
        this.requiresConnection = true;
        time = new JLabel("00:00:00");
        time.setForeground(Theme.gameClockColor);
        time.setFont( new Font( time.getFont().getName(), Font.PLAIN, 50));

        timerPanel = new JPanel();
        timerPanel.setLayout( new FlowLayout( FlowLayout.LEFT));
        timerPanel.setPreferredSize( new Dimension(300,80));
        timerPanel.setOpaque(false);


    }

    public void startTimer(){

        ((DailyChallengeMode)getGame()).setTime();

        timer = new Timer( 1000/60,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                long timePassedLong = ((DailyChallengeMode)getGame()).getTimePassed();
                String timePassed = (timePassedLong / (1000 * 60)) % 60 + " : "+ ((timePassedLong / 1000) % 60)  + " : " + timePassedLong % 1000;

                time.setText( timePassed);
            }
        });
        timer.start();
    }

    @Override
    public void setGame(BaseGame game){
        super.setGame(game);
        timerPanel.add(time);
        super.cardPanel.add(timerPanel, 0);
    }

    @Override
    protected void backButtonAction(){
        int quit = JOptionPane.showConfirmDialog( DailyChallengeScreen.this,
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

    public void createGame(){
        MainFrame.getInstance().getMoveController().setBaseGameScreen(this);

        DailyChallengeMode dm = new DailyChallengeMode(new Cube(0));
        setGame(dm);
        startTimer();
    }

    @Override
    public void onShow(){
        this.createGame();
    }

    @Override
    public void onGameFinished(){
        DailyChallengeScreen.this.timer.stop();
        long timePassedLong = ((DailyChallengeMode)getGame()).getTimePassed();
        String timePassed = (timePassedLong / (1000 * 60)) % 60 + " : "+ ((timePassedLong / 1000) % 60)  + " : " + timePassedLong % 1000;

        JOptionPane.showMessageDialog( this,
                "You finished game in: " + timePassed,
                "GAME OVER",
                JOptionPane.INFORMATION_MESSAGE);

        ScreenManager.doubleBack(); // go main menu
        ScreenManager.getScreen(ScreenManager.LEADERBOARD_SCREEN).update();
        ScreenManager.show(ScreenManager.LEADERBOARD_SCREEN);

    }

    @Override
    public void onError(){
        JOptionPane.showMessageDialog( ScreenManager.getScreen( ScreenManager.PLAY_SCREEN),
                "Connection Failed!",
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }

}
