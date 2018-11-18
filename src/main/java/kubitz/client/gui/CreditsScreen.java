package kubitz.client.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CreditsScreen extends JPanel implements Screen {

    JPanel contentPane;
    Dimension size;

    public CreditsScreen(JPanel contentPane, Dimension size) {

        super();
        this.contentPane = contentPane;
        this.size = size;
        initializeResources();
    }

    private void initializeResources(){

        this.setSize( size );
        this.setBackground( new Color(0,0,0,0));
        this.setLayout( new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        CustomButton backButton = new CustomButton("BACK");
        backButton.addActionListener(e -> {

            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, MainFrame.MAINMENU);

        });

        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add( backButton,c);

        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.gridx = 1;
        c.gridy = 2;
        this.add( initializeCredits(),c);

    }

    private JPanel initializeCredits() {

        JPanel settingsPanel = new JPanel();
        settingsPanel.setMaximumSize(new Dimension( getWidth()/5, 30));
        settingsPanel.setBackground( new Color(0,0,0, 0));
        settingsPanel.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 0;
        settingsPanel.add(new JLabel("Mertkan Akkuş",JLabel.CENTER), c);
        c.gridy = 1;
        settingsPanel.add(new JLabel("Yasin Alptekin Ay",JLabel.CENTER), c);
        c.gridy = 2;
        settingsPanel.add(new JLabel("Ahmet Furkan Bıyık",JLabel.CENTER), c);
        c.gridy = 3;
        settingsPanel.add(new JLabel("Mert Çınar",JLabel.CENTER), c);
        c.gridy = 4;
        settingsPanel.add(new JLabel("Yaman Yağız Taşbağ",JLabel.CENTER), c);
        c.gridy = 5;
        CustomButton git = new CustomButton("GitHUB Page");
        git.addActionListener( e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/cinarmert/WastedPotentials-319"));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        });
        settingsPanel.add(git, c);

        return settingsPanel;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage( MainFrame.background, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateResolution(Dimension size) {
        this.size = size;
    }
}
