package kubitz.client.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class KeyBindingScreen extends BaseScreen{

    public final String BINDINGS_NAME = "/bindings.properties";
    private Properties props;

    private JPanel rotateLeftKeyBox;
    private JPanel rotateRightKeyBox;
    private JPanel rotateUpKeyBox;
    private JPanel rotateDownKeyBox;
    private JPanel rotateClockwiseKeyBox;
    private JPanel rotateCounterclockwiseKeyBox;

    private JLabel rotateLeftLabel;
    private JLabel rotateRightLabel;
    private JLabel rotateUpLabel;
    private JLabel rotateDownLabel;
    private JLabel rotateClockwiseLabel;
    private JLabel rotateCounterclockwiseLabel;

    private CustomButton rotateLeftAssign;
    private CustomButton rotateRightAssign;
    private CustomButton rotateUpAssign;
    private CustomButton rotateDownAssign;
    private CustomButton rotateClockwiseAssign;
    private CustomButton rotateCounterclockwiseAssign;

    private int leftKey;
    private int rightKey;
    private int upKey;
    private int downKey;
    private int clockwiseKey;
    private int counterclockwiseKey;

    private int bindingToChange; // -1: no change, 0: left, 1: right, 2: up, 3: down, 4: clockwise, 5: counterclockwise

    private BindingKeyListener keyListener;

    public KeyBindingScreen(Dimension resolution){

        super(resolution);
        keyListener = new BindingKeyListener();
        initializeResources();
    }

    private void getKeys() {
        try {

            props = new Properties();

            InputStream inputStream = getClass().getResource(BINDINGS_NAME).openStream();

            props.load(inputStream);

            leftKey = Integer.parseInt( props.getProperty("rotateLeft") );
            rightKey = Integer.parseInt( props.getProperty("rotateRight") );
            upKey = Integer.parseInt( props.getProperty("rotateUp") );
            downKey = Integer.parseInt( props.getProperty("rotateDown") );
            clockwiseKey = Integer.parseInt( props.getProperty("rotateClockwise") );
            counterclockwiseKey = Integer.parseInt( props.getProperty("rotateCounterclockwise") );

        } catch (Exception e) {
            createDefaultConfig();
        }
    }

    private void createDefaultConfig() {

        try {

            props = new Properties();
            props.setProperty("rotateLeft", "" + KeyEvent.VK_A);
            props.setProperty("rotateRight", "" + KeyEvent.VK_D);
            props.setProperty("rotateUp", "" + KeyEvent.VK_W);
            props.setProperty("rotateDown", "" + KeyEvent.VK_S);
            props.setProperty("rotateClockwise", "" + KeyEvent.VK_Q);
            props.setProperty("rotateCounterclockwise", "" + KeyEvent.VK_E);

            File f = new File( getClass().getResource("/").toURI().getPath() +  BINDINGS_NAME);
            OutputStream out = new FileOutputStream( f );
            props.store(out, "BINDING FILE");

            leftKey = KeyEvent.VK_A;
            rightKey = KeyEvent.VK_D;
            upKey = KeyEvent.VK_W;
            downKey = KeyEvent.VK_S;
            clockwiseKey = KeyEvent.VK_Q;
            counterclockwiseKey = KeyEvent.VK_E;
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    private void updateKeys() {

        try {

            props = new Properties();
            props.setProperty("rotateLeft", "" + leftKey);
            props.setProperty("rotateRight", "" + rightKey);
            props.setProperty("rotateUp", "" + upKey);
            props.setProperty("rotateDown", "" + downKey);
            props.setProperty("rotateClockwise", "" + clockwiseKey);
            props.setProperty("rotateCounterclockwise", "" + counterclockwiseKey);

            File f = new File( getClass().getResource("/").toURI().getPath() +  BINDINGS_NAME);
            OutputStream out = new FileOutputStream( f );
            props.store(out, "BINDING FILE");

        }
        catch (Exception e ) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initializeResources() {

        setBackButton(true);

        addKeyListener(new BindingKeyListener());

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;

        this.addMain(initializeContainer(),c);


    }

    private JPanel initializeContainer() {

        JPanel container = new JPanel( new GridBagLayout());

        container.setBackground(Color.WHITE);
        container.setBorder(new LineBorder(Color.BLACK, 3));
        container.setPreferredSize( new Dimension( getMainWidth()-(getMainWidth()/3),
                getMainHeight()-(getMainHeight()/3)));

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(20,30,0,0);
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;

        JLabel header = new JLabel("Key Bindings");
        header.setFont(container.getFont().deriveFont(32.0f));
        container.add(header,c);

        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;

        container.add(initializeBindingContainer(),c);

        return container;
    }

    private JPanel initializeBindingContainer() {

        JPanel bindingContainer = new JPanel(new GridLayout(6,1,0,20));
        bindingContainer.setOpaque(false);

        initializeKeyLabels();
        initializeButtons();

        bindingContainer.add(initializeRow("Rotate Left", rotateLeftKeyBox, rotateLeftAssign));

        bindingContainer.add(initializeRow("Rotate Right", rotateRightKeyBox, rotateRightAssign));

        bindingContainer.add(initializeRow("Rotate Up", rotateUpKeyBox, rotateUpAssign));

        bindingContainer.add(initializeRow("Rotate Down", rotateDownKeyBox, rotateDownAssign));

        bindingContainer.add(initializeRow("Rotate Clockwise", rotateClockwiseKeyBox, rotateClockwiseAssign));

        bindingContainer.add(initializeRow( "Rotate Counterclockwise", rotateCounterclockwiseKeyBox, rotateCounterclockwiseAssign));

        return bindingContainer;
    }

    private JPanel initializeRow( String name, JPanel keyBox, CustomButton assignButton){

        JPanel rowPanel = new JPanel(new GridLayout(1,3,0,0));
        rowPanel.setOpaque(false);

        rowPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT)){{
            add(new JLabel(name));
            setOpaque(false);
        }});

        rowPanel.add(new JPanel(new FlowLayout(FlowLayout.CENTER)){{
            add(keyBox);
            setOpaque(false);
        }});

        rowPanel.add(new JPanel(new FlowLayout(FlowLayout.RIGHT)){{
            add(assignButton);
            setOpaque(false);
        }});

        return rowPanel;
    }

    private void initializeButtons() {

        rotateLeftAssign = new CustomButton("Change"){{
            setPreferredSize(new Dimension(getMainWidth()/10,getMainWidth()/35));
            addActionListener(e -> bindingToChange = 0);
        }};

        rotateRightAssign = new CustomButton("Change"){{
            setPreferredSize(new Dimension(getMainWidth()/10,getMainWidth()/35));
            addActionListener(e -> bindingToChange = 1);
        }};

        rotateUpAssign = new CustomButton("Change"){{
            setPreferredSize(new Dimension(getMainWidth()/10,getMainWidth()/35));
            addActionListener(e -> bindingToChange = 2);
        }};

        rotateDownAssign = new CustomButton("Change"){{
            setPreferredSize(new Dimension(getMainWidth()/10,getMainWidth()/35));
            addActionListener(e -> bindingToChange = 3);
        }};

        rotateClockwiseAssign = new CustomButton("Change"){{
            setPreferredSize(new Dimension(getMainWidth()/10,getMainWidth()/35));
            addActionListener(e -> bindingToChange = 4);
        }};

        rotateCounterclockwiseAssign = new CustomButton("Change"){{
            setPreferredSize(new Dimension(getMainWidth()/10,getMainWidth()/35));
            addActionListener(e -> bindingToChange = 5);
        }};
    }

    private void initializeKeyLabels() {

        rotateLeftLabel = new JLabel(KeyEvent.getKeyText(leftKey));
        rotateLeftLabel.setVerticalTextPosition(JLabel.CENTER);
        rotateLeftLabel.setHorizontalAlignment(JLabel.CENTER);

        rotateRightLabel = new JLabel(KeyEvent.getKeyText(rightKey));
        rotateRightLabel.setVerticalTextPosition(JLabel.CENTER);
        rotateRightLabel.setHorizontalAlignment(JLabel.CENTER);

        rotateUpLabel = new JLabel(KeyEvent.getKeyText(upKey));
        rotateUpLabel.setVerticalTextPosition(JLabel.CENTER);
        rotateUpLabel.setHorizontalAlignment(JLabel.CENTER);

        rotateDownLabel = new JLabel(KeyEvent.getKeyText(downKey));
        rotateDownLabel.setVerticalTextPosition(JLabel.CENTER);
        rotateDownLabel.setHorizontalAlignment(JLabel.CENTER);

        rotateClockwiseLabel = new JLabel(KeyEvent.getKeyText(clockwiseKey));
        rotateClockwiseLabel.setVerticalTextPosition(JLabel.CENTER);
        rotateClockwiseLabel.setHorizontalAlignment(JLabel.CENTER);

        rotateCounterclockwiseLabel = new JLabel(KeyEvent.getKeyText(counterclockwiseKey));
        rotateCounterclockwiseLabel.setVerticalTextPosition(JLabel.CENTER);
        rotateCounterclockwiseLabel.setHorizontalAlignment(JLabel.CENTER);

        rotateLeftKeyBox = new JPanel(){{

            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(getMainWidth()/35,getMainWidth()/35));
            setBorder(new LineBorder(Color.BLACK, 2));
            add(rotateLeftLabel, BorderLayout.PAGE_START);
        }};

        rotateRightKeyBox = new JPanel(){{

            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(getMainWidth()/35,getMainWidth()/35));
            setBorder(new LineBorder(Color.BLACK, 2));
            add(rotateRightLabel, BorderLayout.PAGE_START);
        }};

        rotateUpKeyBox = new JPanel(){{

            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(getMainWidth()/35,getMainWidth()/35));
            setBorder(new LineBorder(Color.BLACK, 2));
            add(rotateUpLabel, BorderLayout.PAGE_START);
        }};

        rotateDownKeyBox = new JPanel(){{

            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(getMainWidth()/35,getMainWidth()/35));
            setBorder(new LineBorder(Color.BLACK, 2));
            add(rotateDownLabel, BorderLayout.PAGE_START);
        }};

        rotateClockwiseKeyBox = new JPanel(){{

            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(getMainWidth()/35,getMainWidth()/35));
            setBorder(new LineBorder(Color.BLACK, 2));
            add(rotateClockwiseLabel, BorderLayout.PAGE_START);
        }};

        rotateCounterclockwiseKeyBox = new JPanel(){{

            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(getMainWidth()/35,getMainWidth()/35));
            setBorder(new LineBorder(Color.BLACK, 2));
            add(rotateCounterclockwiseLabel, BorderLayout.PAGE_START);
        }};

    }

    private void updateLabels(){
        rotateLeftLabel.setText(Character.toString((char)leftKey));
        rotateRightLabel.setText(Character.toString((char)rightKey));
        rotateUpLabel.setText(Character.toString((char)upKey));
        rotateDownLabel.setText(Character.toString((char)downKey));
        rotateClockwiseLabel.setText(Character.toString((char)clockwiseKey));
        rotateCounterclockwiseLabel.setText(Character.toString((char)counterclockwiseKey));
    }

    private void setLeftKey(int key){

        leftKey = key;
        props.setProperty("rotateLeft", "" + key);
        rotateLeftLabel.setText(Character.toString((char)key));
    }
    private void setRightKey(int key){

        rightKey = key;
        props.setProperty("rotateRight", "" + key);
        rotateRightLabel.setText(Character.toString((char)key));

    }
    private void setUpKey(int key){

        upKey = key;
        props.setProperty("rotateUp", "" + key);
        rotateUpLabel.setText(Character.toString((char)key));

    }
    private void setDownKey(int key){

        downKey = key;
        props.setProperty("rotateDown", "" + key);
        rotateDownLabel.setText(Character.toString((char)key));

    }
    private void setClockwiseKey(int key){

        clockwiseKey = key;
        props.setProperty("rotateClockwise", "" + key);
        rotateClockwiseLabel.setText(Character.toString((char)key));

    }
    private void setCounterclockwiseKey(int key){

        counterclockwiseKey = key;
        props.setProperty("rotateCounterclockwise", "" + key);
        rotateCounterclockwiseLabel.setText(Character.toString((char)key));

    }

    @Override
    public void onShow(){
        MainFrame.getInstance().addKeyListener(keyListener);
        getKeys();
        updateLabels();
    }

    @Override
    public void onHide(){
        MainFrame.getInstance().removeKeyListener(keyListener);
        updateKeys();
    }

    class BindingKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            switch (bindingToChange){

                case 0 : setLeftKey(e.getKeyCode());
                    break;
                case 1 : setRightKey(e.getKeyCode());
                    break;
                case 2 : setUpKey(e.getKeyCode());
                    break;
                case 3 : setDownKey(e.getKeyCode());
                    break;
                case 4 : setClockwiseKey(e.getKeyCode());
                    break;
                case 5 : setCounterclockwiseKey(e.getKeyCode());
                    break;
                default: break;
            }
            bindingToChange = -1;

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}