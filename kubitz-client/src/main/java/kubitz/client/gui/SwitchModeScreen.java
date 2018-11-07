package kubitz.client.gui;

import kubitz.client.logic.SwitchMode;

import javax.swing.*;
import java.awt.*;

public class SwitchModeScreen extends BaseGameScreen implements Screen{

    SwitchMode sm;

    public SwitchModeScreen(SwitchMode sm, JPanel contentPane, Dimension size) {
        super(sm);
        this.sm = sm;
    }

    @Override
    public void update() {

    }
}
