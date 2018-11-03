package kubitz.client.gui;

import kubitz.client.logic.SurvivalMode;

public class SurvivalModeScreen extends BaseGameScreen implements Screen {

    SurvivalMode sm;

    public SurvivalModeScreen(SurvivalMode sm) {
        super(sm);
        this.sm = sm;
    }

    @Override
    public void update() {

    }
}
