package kubitz.client.gui;

import kubitz.client.logic.BaseGame;
import kubitz.client.logic.SurvivalMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurvivalModeScreen extends BaseGameScreen {

    private SurvivalMode sm;
    private JLabel time;
    private Timer timer;
    private JPanel timerPanel;

    public SurvivalModeScreen(SurvivalMode sm, JPanel contentPane, Dimension size) {
        super(sm, size);

        time = new JLabel("00:00:00");
        time.setForeground(Color.BLACK);
        time.setFont( new Font( time.getFont().getName(), Font.PLAIN, 50));

        timerPanel = new JPanel();
        timerPanel.setLayout( new FlowLayout( FlowLayout.LEFT));
        timerPanel.setPreferredSize( new Dimension(300,80));
        timerPanel.setOpaque(false);
    }

    public void startTimer(){

        sm.startCountDown();

        int refreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
        timer = new Timer( 1000/refreshRate,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                long timePassedLong = sm.getTime();
                String timePassed = (timePassedLong / (1000 * 60)) % 60 + " : "+ ((timePassedLong / 1000) % 60)  + " : " + timePassedLong % 1000;

                time.setText( timePassed);

                if(sm.isGameFinished())
                {
                    SurvivalModeScreen.this.timer.stop();


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

        this.sm = ((SurvivalMode)getGame());
    }

    @Override
    public void update() {

    }
}
