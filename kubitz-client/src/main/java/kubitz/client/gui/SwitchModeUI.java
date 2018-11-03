package kubitz.client.gui;

import kubitz.client.logic.SwitchMode;

public class SwitchModeUI extends BaseGameUI implements Screen{

    SwitchMode sm;

    public SwitchModeUI(SwitchMode sm) {
        super(sm);
        this.sm = sm;
    }

    @Override
    public void update() {

    }
}
