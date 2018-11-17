package kubitz.client.gui;

import kubitz.client.logic.SurvivalMode;

import javax.swing.*;
import java.awt.*;

public class SurvivalModeScreen extends BaseGameScreen {

    SurvivalMode sm;

    public SurvivalModeScreen(SurvivalMode sm, JPanel contentPane, Dimension size) {
        super(sm, size);
        this.sm = sm;
    }

    @Override
    public void update() {

    }
}
