package kubitz.client.gui;

import kubitz.client.logic.DailyChallengeMode;

import javax.swing.*;
import java.awt.*;

public class DailyChallengeScreen extends BaseGameScreen{

    private DailyChallengeMode dcm;

    public DailyChallengeScreen(DailyChallengeMode dcm, JPanel contentPane, Dimension size) {
        super(dcm,size);
        this.dcm = dcm;
    }

    @Override
    public void update() {

    }
}
