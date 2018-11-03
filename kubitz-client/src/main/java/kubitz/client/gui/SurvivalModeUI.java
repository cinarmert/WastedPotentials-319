package kubitz.client.gui;

import kubitz.client.logic.SurvivalMode;
import kubitz.client.logic.SwitchMode;

public class SurvivalModeUI extends BaseGameUI implements Screen {

    SurvivalMode sm;

    public SurvivalModeUI(SurvivalMode sm) {
        super(sm);
        this.sm = sm;
    }

    @Override
    public void update() {

    }
}
