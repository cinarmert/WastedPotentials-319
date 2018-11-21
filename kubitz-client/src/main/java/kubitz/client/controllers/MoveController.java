package kubitz.client.controllers;

import kubitz.client.gui.BaseGameScreen;

import java.awt.event.*;

public class MoveController implements KeyListener, MouseListener {

    private BaseGameScreen baseGameScreen;

    public static final int LEFT = KeyEvent.VK_A;
    public static final int RIGHT = KeyEvent.VK_D;
    public static final int UP = KeyEvent.VK_W;
    public static final int DOWN = KeyEvent.VK_S;
    public static final int ROTATECLOCKVISE = KeyEvent.VK_Q;
    public static final int ROTATECOUNTERCLOCKVISE = KeyEvent.VK_E;

    public MoveController() {
        baseGameScreen = null;
    }

    public void setBaseGameScreen(BaseGameScreen baseGameScreen){
        this.baseGameScreen = baseGameScreen;
    }


    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if (baseGameScreen != null) {

            switch (e.getKeyCode()){
                case LEFT:
                    baseGameScreen.getSelectedCube().rotate(1,0,0);
                    break;
                case RIGHT:
                    baseGameScreen.getSelectedCube().rotate(-1,0,0);
                    break;
                case UP:
                    baseGameScreen.getSelectedCube().rotate(0,1,0);
                    break;
                case DOWN:
                    baseGameScreen.getSelectedCube().rotate(0,-1,0);
                    break;
                case ROTATECLOCKVISE:
                    baseGameScreen.getSelectedCube().rotate(0,0,1);
                    break;
                case ROTATECOUNTERCLOCKVISE:
                    baseGameScreen.getSelectedCube().rotate(0,0,-1);
                    break;
                default:
                    break;
            }

            baseGameScreen.repaint();

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
