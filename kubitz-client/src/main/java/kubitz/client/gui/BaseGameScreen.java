package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;

import javax.swing.*;
import java.awt.*;

public class BaseGameScreen extends JPanel implements Screen{

    BaseGame baseGame;

    public BaseGameScreen(BaseGame baseGame) {
        this.baseGame = baseGame;
    }

    @Override
    public void update() {

    }

    @Override
    public void updateResolution(Dimension size) {

    }
}
