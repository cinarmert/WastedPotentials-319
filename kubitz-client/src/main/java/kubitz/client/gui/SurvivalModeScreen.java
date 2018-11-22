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
    private JPanel contentPane;

    public SurvivalModeScreen( JPanel contentPane, Dimension size) {

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

        SurvivalMode sm = new SurvivalMode(new Grid(4), new Cube(0), this::onGameFinished );
        setGame(sm);
        startTimer();
    }

    @Override
    public void setGame(BaseGame game){
        super.setGame(game);
        timerPanel.add(time);
        super.cardPanel.add(timerPanel, 0);

        addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int quit = JOptionPane.showConfirmDialog( SurvivalModeScreen.this,
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

    @Override
    public void update() {

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


    public Void onGameFinished( Void v){

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
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, MainFrame.PLAY);
            setGame(null);
            MainFrame.getInstance().getMoveController().setBaseGameScreen(null);
        }

        return null;
    }
}

