package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;

import javax.swing.*;

public class BaseGameScreen extends JPanel {

    BaseGame baseGame;

    public BaseGameScreen(BaseGame baseGame) {
        this.baseGame = baseGame;
    }

}
