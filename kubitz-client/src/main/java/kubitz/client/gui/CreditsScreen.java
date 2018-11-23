package kubitz.client.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CreditsScreen extends JPanel implements Screen {

    private JPanel contentPane;
    private Dimension size;

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
        c.insets = new Insets(20,0,0,0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.gridx = 1;
        c.gridy = 2;
        this.add( initializeCredits(),c);

    }

    private JPanel initializeCredits() {

        JPanel settingsPanel = new JPanel(){{
            setMaximumSize(new Dimension( getWidth()/5, 30));
            setBackground( new Color(0,0,0, 0));
            setLayout( new GridBagLayout());
        }};

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 0;
        settingsPanel.add( nameBox("Mertkan Akkuş"), c);
        c.gridy = 1;
        settingsPanel.add( nameBox("Yasin Alptekin Ay"), c);
        c.gridy = 2;
        settingsPanel.add( nameBox("Ahmet Furkan Bıyık"), c);
        c.gridy = 3;
        settingsPanel.add( nameBox("Ramazan Mert Çınar"), c);
        c.gridy = 4;
        settingsPanel.add( nameBox("Yaman Yağız Taşbağ"), c);
        c.gridy = 5;
        try {
            settingsPanel.add(new JButton(){{
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

        return settingsPanel;
    }

    public JPanel nameBox( String name){
        return new JPanel( new BorderLayout()){{
            add(new JLabel(name,JLabel.CENTER), BorderLayout.CENTER);
            setPreferredSize( new Dimension( size.width/6, size.height/20));
            setBorder(new LineBorder(Color.BLACK));
            setBackground(Color.WHITE);
        }};
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

        //ToDo handle size change
        this.size = size;
    }
}
