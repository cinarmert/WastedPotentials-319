package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen extends JPanel implements Screen {

    public MainMenuScreen() {
        super();
        initializeResources();
    }

    private void initializeResources(){
        this.setLayout(new GridLayout(5,1));
        this.setVisible(true);
        this.setSize(new Dimension(150,150));

        initializeButtons();
    }

    private void initializeButtons() {
        CustomButton play = new CustomButton("Play");
        play.setPreferredSize(new Dimension(30,30));

        CustomButton howToPlay = new CustomButton("How to Play");
        howToPlay.setPreferredSize(new Dimension(30,30));

        CustomButton leaderboard = new CustomButton("Leaderboard");
        leaderboard.setPreferredSize(new Dimension(30,30));

        CustomButton settings = new CustomButton("Settings");
        settings.setPreferredSize(new Dimension(15,15));

        CustomButton credits = new CustomButton("Credits");
        credits.setPreferredSize(new Dimension(15,15));

        CustomButton quit = new CustomButton("Quit");
        quit.setPreferredSize(new Dimension(30,30));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel settingsAndCredits = new JPanel(new GridLayout(1,2));
        settingsAndCredits.add(settings);
        settingsAndCredits.add(credits);

        this.add(play);
        this.add(howToPlay);
        this.add(leaderboard);
        this.add(settingsAndCredits);
        this.add(quit);

        JLabel test = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/backgrounds/menu_background.png")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT)));
        this.add(test);
    }

    @Override
    public void update() {

    }
}
