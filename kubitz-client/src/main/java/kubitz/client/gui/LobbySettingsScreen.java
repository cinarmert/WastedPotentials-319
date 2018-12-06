package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;

public class LobbySettingsScreen extends BaseScreen {

    public LobbySettingsScreen(Dimension resolution) {
        super(resolution);
        this.requiresConnection = true;
        initializeResources();
    }

    @Override
    protected void initializeResources(){

    }
}
