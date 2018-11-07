package kubitz.client;

import kubitz.client.gui.MainMenuScreen;

import javax.swing.*;
import java.awt.*;

public class Test {

    public static void main(String[] args){

        JFrame frame = new JFrame("Card Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();

        JPanel mainMenuPanel = new MainMenuScreen(contentPane, new Dimension(640,480));

        frame.add(mainMenuPanel);

        frame.pack();
        frame.setVisible(true);

    }

}
