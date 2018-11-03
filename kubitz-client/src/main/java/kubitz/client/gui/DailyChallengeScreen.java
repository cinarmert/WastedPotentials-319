package kubitz.client.gui;

import kubitz.client.logic.DailyChallengeMode;

public class DailyChallengeScreen extends BaseGameScreen implements Screen {

    private DailyChallengeMode dcm;

    public DailyChallengeScreen(DailyChallengeMode dcm) {
        super(dcm);
        this.dcm = dcm;
    }

    @Override
    public void update() {

    }
}
