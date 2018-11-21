package kubitz.client.controllers;

import kubitz.client.gui.BaseGameScreen;

import java.awt.event.*;

public class MoveController implements KeyListener, MouseListener {

    BaseGameScreen baseGameScreen;

    public MoveController(BaseGameScreen baseGameScreen) {
        this.baseGameScreen = baseGameScreen;
    }

    public void keyTyped(KeyEvent e) {
        System.out.println( e.getKeyCode());
    }

    public void keyPressed(KeyEvent e) {

        System.out.println( e.getKeyCode());

    }

    public void keyReleased(KeyEvent e) {
        System.out.println( e.getKeyCode());
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
