package kubitz.client.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CreditsScreen extends BaseScreen{

    public CreditsScreen(Dimension resolution) {

        super(resolution);
        initializeResources();
    }

    @Override
    protected void initializeResources(){

        GridBagConstraints c = new GridBagConstraints();

        setBackButton(true);

        c.insets = new Insets(20,0,0,0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.addMain( initializeCredits(),c);

    }

    private JPanel initializeCredits() {

        JPanel creditsPanel = new JPanel();
        creditsPanel.setMaximumSize(new Dimension( getMainWidth()/2, getMainHeight()));
        creditsPanel.setOpaque(false);
        creditsPanel.setLayout( new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 0;
        creditsPanel.add( nameBox("Mertkan Akkuş"), c);
        c.gridy = 1;
        creditsPanel.add( nameBox("Yasin Alptekin Ay"), c);
        c.gridy = 2;
        creditsPanel.add( nameBox("Ahmet Furkan Bıyık"), c);
        c.gridy = 3;
        creditsPanel.add( nameBox("Ramazan Mert Çınar"), c);
        c.gridy = 4;
        creditsPanel.add( nameBox("Yaman Yağız Taşbağ"), c);
        c.gridy = 5;
        try {
            creditsPanel.add(new JButton(){{
                setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/logos/github.png"))));
                addActionListener( e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/cinarmert/WastedPotentials-319"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                });
                setPreferredSize(new Dimension(96,96));
            }}, c);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return creditsPanel;
    }

    public JPanel nameBox( String name){
        return new JPanel( new BorderLayout()){{
            add(new JLabel(name,JLabel.CENTER), BorderLayout.CENTER);
            setPreferredSize( new Dimension( getMainWidth()/5, getMainHeight()/20));
            setBorder(new LineBorder(Theme.borderColor));
            setBackground(Theme.backgroundColor);
        }};
    }

}
