package kubitz.client.gui;

import kubitz.client.logic.SwitchMode;

public class SwitchModeScreen extends BaseGameScreen implements Screen{

    SwitchMode sm;

    public SwitchModeScreen(SwitchMode sm) {
        super(sm);
        this.sm = sm;
    }

    @Override
    public void update() {

    }
}
