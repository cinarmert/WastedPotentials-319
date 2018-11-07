package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;

import javax.swing.*;
import java.awt.*;

public class BaseGameScreen extends JPanel {

    BaseGame baseGame;

    public BaseGameScreen(BaseGame baseGame, JPanel contentPane, Dimension size) {
        this.baseGame = baseGame;
    }

}
