package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseScreen extends JPanel implements Screen {

    private Dimension resolution;
    private CustomButton backButton;
    private boolean isBackButton;
    private JPanel left;
    private JPanel right;

    public BaseScreen(Dimension resolution) {

        this.resolution = resolution;
        this.isBackButton = false;
        this.left = new JPanel();
        this.right = new JPanel();

        setLayout( new GridBagLayout());
        right.setLayout( new GridBagLayout());

        left.setOpaque(false);
        right.setOpaque(false);

        createBackButton();
        left.add(backButton);

        setOpaque(false);
        setSize(resolution);
        setMaximumSize(resolution);
        setMinimumSize(resolution);
        setPreferredSize(resolution);

        right.setPreferredSize(MainFrame.getResolution());
        left.setPreferredSize( new Dimension( backButton.getPreferredSize().width+20, resolution.height));

        setBackButton(isBackButton);

    }

    public int getRightWidth(){
        return right.getPreferredSize().width;
    }

    public int getRightHeight(){
        return right.getPreferredSize().height;
    }

    @Override
    public void removeAll(){
        right.removeAll();
    }

    @Override
    public void add(Component component, Object constraints){
        right.add(component, constraints);
    }

    private void createBackButton(){

        this.backButton = new CustomButton("BACK");
        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                backButtonAction();
            }
        });

    }

    protected void backButtonAction(){
        ScreenManager.back();
    }

    public Dimension getResolution() {
        return resolution;
    }


    public void setBackButton( boolean isBackButton) {

        this.isBackButton = isBackButton;
        GridBagConstraints c = new GridBagConstraints();

        if (isBackButton) {
            super.removeAll();

            right.setPreferredSize( new Dimension( getWidth() - 20 - backButton.getPreferredSize().width, resolution.height));

            c.fill = GridBagConstraints.VERTICAL;
            c.weightx = 0.001;
            c.weighty = 1.0;
            c.gridx = 0;
            c.gridy = 0;
            super.add(left,c);

            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1.0;
            c.gridx = 1;
            super.add(right, c);
        }
        else {
            super.removeAll();

            c.weightx = 1.0;
            c.weighty = 1.0;
            c.fill = GridBagConstraints.BOTH;

            super.add(right, c);
        }
    }

    @Override
    public void update(){
        this.removeAll();
        initializeResources();
        revalidate();
        repaint();
    }

    @Override
    public void updateResolution(Dimension resolution){
        this.resolution = resolution;
        this.setPreferredSize(resolution);
        update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage( MainFrame.getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
    }

    protected abstract void initializeResources();

}
