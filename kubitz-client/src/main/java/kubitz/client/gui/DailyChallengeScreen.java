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

    private JPanel contentPane;
    private JLabel time;
    private Timer timer;
    private JPanel timerPanel;

    public DailyChallengeScreen( JPanel contentPane, Dimension size) {
        super(size);
        this.contentPane = contentPane;

        time = new JLabel("00:00:00");
        time.setForeground(Color.BLACK);
        time.setFont( new Font( time.getFont().getName(), Font.PLAIN, 50));

        timerPanel = new JPanel();
        timerPanel.setLayout( new FlowLayout( FlowLayout.LEFT));
        timerPanel.setPreferredSize( new Dimension(300,80));
        timerPanel.setOpaque(false);
    }



    public void startTimer(){

        ((DailyChallengeMode)getGame()).setTime();

//        int refreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
        timer = new Timer( 1000/60,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                long timePassedLong = ((DailyChallengeMode)getGame()).getTimePassed();
                String timePassed = (timePassedLong / (1000 * 60)) % 60 + " : "+ ((timePassedLong / 1000) % 60)  + " : " + timePassedLong % 1000;

                time.setText( timePassed);

                if(getGame().isGameFinished())
                {
                    DailyChallengeScreen.this.timer.stop();

                }

            }
        });
        timer.start();
    }

    @Override
    public void setGame(BaseGame game){
        super.setGame(game);
        timerPanel.add(time);
        super.cardPanel.add(timerPanel, 0);

        addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int quit = JOptionPane.showConfirmDialog( DailyChallengeScreen.this,
                        "Are you sure you want to leave the game?",
                        "Leave?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if( quit == 0 ) {
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.show(contentPane, MainFrame.PLAY);
                    timer.stop();
                    setGame(null);
                    MainFrame.getInstance().getMoveController().setBaseGameScreen(null);
                }

            }
        });
    }

    public void createGame(){
        MainFrame.getInstance().getMoveController().setBaseGameScreen(this);

        DailyChallengeMode dm = new DailyChallengeMode(new Grid(4), new Cube(0));
        setGame(dm);
        startTimer();
    }

    @Override
    public void onGameFinished(){
        long timePassedLong = ((DailyChallengeMode)getGame()).getTimePassed();
        String timePassed = (timePassedLong / (1000 * 60)) % 60 + " : "+ ((timePassedLong / 1000) % 60)  + " : " + timePassedLong % 1000;

        JOptionPane.showMessageDialog( DailyChallengeScreen.this,
                "You finished game in: " + timePassed,
                "GAME OVER",
                JOptionPane.INFORMATION_MESSAGE);

        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, MainFrame.LEADERBOARD);

    }

    @Override
    public void update() {

    }
}
