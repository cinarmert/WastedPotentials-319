package kubitz.client.controllers;

import kubitz.client.gui.BaseGameScreen;
import kubitz.client.gui.ScreenManager;

import java.awt.event.*;
import java.io.*;
import java.util.Properties;

public class MoveController implements KeyListener, MouseListener {

    private BaseGameScreen baseGameScreen;

    public final String BINDINGS_NAME = "/bindings.properties";
    private Properties props;

    public static final int DEFAULT_LEFT = KeyEvent.VK_A;
    public static final int DEFAULT_RIGHT = KeyEvent.VK_D;
    public static final int DEFAULT_UP = KeyEvent.VK_W;
    public static final int DEFAULT_DOWN = KeyEvent.VK_S;
    public static final int DEFAULT_ROTATECLOCKWISE = KeyEvent.VK_Q;
    public static final int DEFAULT_ROTATECOUNTERCLOCKWISE = KeyEvent.VK_E;

    public static int left = DEFAULT_LEFT;
    public static int right = DEFAULT_RIGHT;
    public static int up = DEFAULT_UP;
    public static int down = DEFAULT_DOWN;
    public static int rotateclockwise = DEFAULT_ROTATECLOCKWISE;
    public static int rotatecounterclockwise = DEFAULT_ROTATECOUNTERCLOCKWISE;

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
            baseGameScreen.repaint();

        }
    }

    public void updateControls(){

        try {
            props = new Properties();

            InputStream inputStream = getClass().getResource(BINDINGS_NAME).openStream();
            props.load(inputStream);

            left = Integer.parseInt( props.getProperty("rotateLeft") );
            right = Integer.parseInt( props.getProperty("rotateRight") );
            up = Integer.parseInt( props.getProperty("rotateUp") );
            down = Integer.parseInt( props.getProperty("rotateDown") );
            rotateclockwise = Integer.parseInt( props.getProperty("rotateCounterclockwise") );
            rotatecounterclockwise = Integer.parseInt( props.getProperty("rotateClockwise") );

        } catch (Exception e1) {
            createDefaultControls();
        }


    }

    public void createDefaultControls(){
        try {
            props = new Properties();

            props.setProperty("rotateLeft", DEFAULT_LEFT + "");
            props.setProperty("rotateRight", DEFAULT_RIGHT + "");
            props.setProperty("rotateUp", DEFAULT_UP + "");
            props.setProperty("rotateDown", DEFAULT_DOWN + "");
            props.setProperty("rotateCounterclockwise", DEFAULT_ROTATECOUNTERCLOCKWISE + "");
            props.setProperty("rotateClockwise", DEFAULT_ROTATECLOCKWISE + "");


            File f = new File(MoveController.class.getResource("/").toURI().getPath() +  BINDINGS_NAME);
            OutputStream out = new FileOutputStream( f );
            props.store(out, "KEY BINDINGS");

            left = DEFAULT_LEFT;
            right = DEFAULT_RIGHT;
            up = DEFAULT_UP;
            down = DEFAULT_DOWN;
            rotateclockwise = DEFAULT_ROTATECLOCKWISE;
            rotatecounterclockwise = DEFAULT_ROTATECOUNTERCLOCKWISE;

        } catch (Exception e) {
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
