package kubitz.client.gui;

import kubitz.client.logic.ClassicMode;

import java.awt.*;

public class ClassicModeUI extends BaseGameUI implements Screen {

    private ClassicMode cm;

    public ClassicModeUI(ClassicMode cm) {
        super(cm);
        this.cm = cm;
    }

    @Override
    public void update() {

    }
}
