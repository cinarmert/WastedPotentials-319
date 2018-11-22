package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;

public class LobbyScreen extends JPanel implements Screen {

    private JPanel contentPane;
    private static Lobby currentLobby;
    private Dimension size;

    public LobbyScreen(JPanel contentPane, Dimension size) {
        super();
        this.contentPane = contentPane;
        this.size = size;
        initializeResources();
    }

    private void initializeResources() {
        setSize(size);
    }


    @Override
    public void update() {

    }

    @Override
    public void updateResolution(Dimension size) {

    }

    public static Lobby getCurrentLobby() {
        return currentLobby;
    }

    public static void setCurrentLobby(Lobby currentLobby) {

        LobbyScreen.currentLobby = currentLobby;
    }
}
