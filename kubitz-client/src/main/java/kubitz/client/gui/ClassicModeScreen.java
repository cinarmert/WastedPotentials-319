package kubitz.client.gui;

import kubitz.client.logic.ClassicMode;

public class ClassicModeScreen extends BaseGameScreen implements Screen {

    private ClassicMode cm;

    public ClassicModeScreen(ClassicMode cm) {
        super(cm);
        this.cm = cm;
    }

    @Override
    public void update() {

    }
}
