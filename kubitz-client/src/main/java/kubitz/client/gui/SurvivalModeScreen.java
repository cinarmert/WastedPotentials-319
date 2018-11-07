package kubitz.client.gui;

import kubitz.client.logic.SurvivalMode;

import javax.swing.*;
import java.awt.*;

public class SurvivalModeScreen extends BaseGameScreen implements Screen {

    SurvivalMode sm;

    public SurvivalModeScreen(SurvivalMode sm, JPanel contentPane, Dimension size) {
        super(sm);
        this.sm = sm;
    }

    @Override
    public void update() {

    }
}
