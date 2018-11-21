package kubitz.client.gui;

import kubitz.client.logic.ClassicMode;

import javax.swing.*;
import java.awt.*;

public class ClassicModeScreen extends BaseGameScreen {

    private ClassicMode cm;

    public ClassicModeScreen(ClassicMode cm, JPanel contentPane, Dimension size) {
        super(cm, size);
        this.cm = cm;
    }

    @Override
    public void update() {

    }

    @Override
    public void onGameFinished() {

    }
}
