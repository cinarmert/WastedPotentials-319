package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseScreen extends JPanel implements Screen {

    private Dimension resolution;
    private CustomButton backButton;
    private boolean isBackButton;
    private JPanel up;
    private JPanel down;
    protected boolean requiresConnection = false;

    public BaseScreen(Dimension resolution) {

        this.resolution = resolution;
        this.isBackButton = false;
        this.up = new JPanel();
        this.down = new JPanel();

        setLayout( new GridBagLayout());
        down.setLayout( new GridBagLayout());

        up.setOpaque(false);
        down.setOpaque(false);

        createBackButton();
        up.setLayout( new FlowLayout(FlowLayout.LEFT));
        up.add(backButton);

        setOpaque(false);
        setSize(resolution);
        setMaximumSize(resolution);
        setMinimumSize(resolution);
        setPreferredSize(resolution);

        down.setPreferredSize(resolution);
        up.setPreferredSize( new Dimension( resolution.width, backButton.getPreferredSize().height + 10));

        setBackButton(isBackButton);

    }

    public boolean doesRequireConnection()
    {
        return requiresConnection;
    }

    public int getMainWidth(){
        return down.getPreferredSize().width;
    }

    public int getMainHeight(){
        return down.getPreferredSize().height;
    }

    public void removeMainAll(){
        down.removeAll();
    }

    public void addMain(Component component, Object constraints){
        down.add(component, constraints);
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
            removeAll();

            down.setPreferredSize( new Dimension( resolution.width - 20 , resolution.height-40 - up.getPreferredSize().height));

            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.NORTH;
            c.weightx = 1.0;
            c.weighty = 0.001;
            c.gridx = 0;
            c.gridy = 0;
            add(up,c);

            c.fill = GridBagConstraints.BOTH;
            c.weighty = 1.0;
            c.gridy = 1;
            add(down, c);

        }
        else {
            super.removeAll();

            c.weightx = 1.0;
            c.weighty = 1.0;
            c.fill = GridBagConstraints.BOTH;

            super.add(down, c);
        }
    }
    public void onShow(){

    }

    public void onHide(){

    }

    public void onError(){ //ToDo maybe add different kind of errors.

    }

    @Override
    public void update(){
        backButton.resetUi();
        this.removeMainAll();
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
        g.drawImage( Theme.backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    protected abstract void initializeResources();

}
