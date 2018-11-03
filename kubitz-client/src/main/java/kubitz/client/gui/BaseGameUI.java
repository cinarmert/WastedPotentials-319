package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;

import javax.swing.*;

public class BaseGameUI extends JPanel {

    BaseGame baseGame;

    public BaseGameUI(BaseGame baseGame) {
        this.baseGame = baseGame;
    }

}
