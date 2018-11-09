package kubitz.client.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    public MainFrame(){
        initializeResources();
    }

    private void initializeResources(){
        this.setSize(new Dimension(600,600));
        this.setContentPane(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/backgrounds/menu_background.png")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT))));

        this.setLayout(new GridLayout(3,3));
        MainMenuScreen mainMenuScreen = new MainMenuScreen();

        JPanel[][] panelHolder = new JPanel[3][3];

        for(int m = 0; m < 3; m++) {
            for(int n = 0; n < 3; n++) {
                if (m == 2 && n == 1) {
                    panelHolder[m][n] = mainMenuScreen;
                    panelHolder[m][n].setOpaque(true);
                    this.add(panelHolder[m][n]);
                }
                else {
                    panelHolder[m][n] = new JPanel();
                    (panelHolder[m][n]).setOpaque(false);
                    this.add(panelHolder[m][n]);
                }
            }
        }

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
