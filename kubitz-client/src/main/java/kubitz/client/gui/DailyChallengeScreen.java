package kubitz.client.gui;

import kubitz.client.logic.BaseGame;
import kubitz.client.logic.DailyChallengeMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DailyChallengeScreen extends BaseGameScreen{

    private JPanel contentPane;
    private DailyChallengeMode dcm;
    private JLabel time;
    private Timer timer;
    private JPanel timerPanel;

    public DailyChallengeScreen(DailyChallengeMode dcm, JPanel contentPane, Dimension size) {
        super(dcm,size);
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

        dcm.setTime();

        int refreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
        timer = new Timer( 1000/refreshRate,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                long timePassedLong = dcm.getTimePassed();
                String timePassed = (timePassedLong / (1000 * 60)) % 60 + " : "+ ((timePassedLong / 1000) % 60)  + " : " + timePassedLong % 1000;

                time.setText( timePassed);

                if(dcm.isGameFinished())
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

        this.dcm = ((DailyChallengeMode)getGame());
    }

    @Override
    public void update() {

    }
}
