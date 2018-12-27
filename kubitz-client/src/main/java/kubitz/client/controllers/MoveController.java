package kubitz.client.controllers;

import kubitz.client.gui.BaseGameScreen;
import kubitz.client.gui.Screen;
import kubitz.client.gui.ScreenManager;

import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MoveController implements KeyListener, MouseListener {

    private BaseGameScreen baseGameScreen;

    public final String BINDINGS_NAME = "/bindings.properties";
    private Properties props;


    public static int left = KeyEvent.VK_A;
    public static int right = KeyEvent.VK_D;
    public static int up = KeyEvent.VK_W;
    public static int down = KeyEvent.VK_S;
    public static int rotateclockwise = KeyEvent.VK_Q;
    public static int rotatecounterclockwise = KeyEvent.VK_E;

    public MoveController() {
        baseGameScreen = null;
        updateControls();
    }

    public void setBaseGameScreen(BaseGameScreen baseGameScreen){
        this.baseGameScreen = baseGameScreen;
    }


    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(!(ScreenManager.getCurrentScreen() instanceof BaseGameScreen))
            return;

        BaseGameScreen baseGameScreen = (BaseGameScreen) ScreenManager.getCurrentScreen();


        if (baseGameScreen != null) {


            if (i == left) {
                baseGameScreen.getSelectedCube().rotate(1, 0, 0);

            } else if (i == right) {
                baseGameScreen.getSelectedCube().rotate(-1, 0, 0);

            } else if (i == up) {
                baseGameScreen.getSelectedCube().rotate(0, 1, 0);

            } else if (i == down) {
                baseGameScreen.getSelectedCube().rotate(0, -1, 0);

            } else if (i == rotateclockwise) {
                baseGameScreen.getSelectedCube().rotate(0, 0, 1);

            } else if (i == rotatecounterclockwise) {
                baseGameScreen.getSelectedCube().rotate(0, 0, -1);

            } else {
            }
            baseGameScreen.updateCubes();
            baseGameScreen.revalidate();
            baseGameScreen.repaint();

        }
    }

    public void updateControls(){
        props = new Properties();

        InputStream inputStream = null;
        try {
            inputStream = getClass().getResource(BINDINGS_NAME).openStream();
            props.load(inputStream);

            left = Integer.parseInt( props.getProperty("rotateLeft") );
            right = Integer.parseInt( props.getProperty("rotateRight") );
            up = Integer.parseInt( props.getProperty("rotateUp") );
            down = Integer.parseInt( props.getProperty("rotateDown") );
            rotateclockwise = Integer.parseInt( props.getProperty("rotateCounterclockwise") );
            rotatecounterclockwise = Integer.parseInt( props.getProperty("rotateClockwise") );

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void keyReleased(KeyEvent e) {
    }


    // mouse listener not necessary right now
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
