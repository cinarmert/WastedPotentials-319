package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToPlayScreen extends JPanel implements Screen {

    JPanel contentPane;
    Dimension size;

    public HowToPlayScreen(JPanel contentPane, Dimension size) {

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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.MAINMENU);

            }
        });

        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.1;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add( backButton,c);

        c.weightx = 1.0;
        c.gridx = 1;
        c.gridy = 0;
        this.add( initializeHowToPlay(),c);

    }

    private JPanel initializeHowToPlay() {

        JPanel howToPlayPanel = new JPanel();
        howToPlayPanel.setLayout( new GridLayout(1,1, 10, 10));
        howToPlayPanel.setMaximumSize(new Dimension( getWidth()-20, 30));
        howToPlayPanel.setBackground( new Color(0,0,0, 0));


        return howToPlayPanel;
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
