package kubitz.client.gui;

import kubitz.client.logic.ClassicMode;
import kubitz.client.logic.DailyChallengeMode;

public class DailyChallengeUI extends BaseGameUI implements Screen {

    private DailyChallengeMode dcm;

    public DailyChallengeUI(DailyChallengeMode dcm) {
        super(dcm);
        this.dcm = dcm;
    }

    @Override
    public void update() {

    }
}
