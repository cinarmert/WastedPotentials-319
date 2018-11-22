package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BaseGameScreen extends JPanel implements Screen{

    private static BaseGameScreen instance = null;
    private GridUI gridUI;
    private CubeUI cubeUI;
    private CardUI cardUI;
    private Dimension size;
    private BaseGame baseGame;
    public Image background;
    protected JPanel cardPanel;
    private CustomButton backButton;

    public BaseGameScreen(BaseGame baseGame, Dimension size) {

        super();
        instance = this;
        this.size = size;

        this.baseGame = baseGame;

        this.setOpaque(false);
        background = new ImageIcon(getClass().getResource("/backgrounds/game_background.jpg")).getImage();

        if (this.baseGame != null)
            setGame( baseGame);

    }

    public void addBackListener( ActionListener listener){
        backButton.addActionListener(listener);
    }

    public void setGame( BaseGame baseGame){
        removeAll();
        this.baseGame = baseGame;
        if (this.baseGame != null) {

            this.setLayout( new GridBagLayout());

            GridBagConstraints c = new GridBagConstraints();

            backButton = new CustomButton("BACK");

            c.insets = new Insets(20,20,0,0);
            c.anchor = GridBagConstraints.NORTHWEST;
            //c.gridwidth = 1;
            c.weightx = 0.1;
            c.weighty = 0.1;
            c.gridx = 0;
            c.gridy = 0;
            this.add( backButton,c);

            c.anchor = GridBagConstraints.NORTH;
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1.0;
            c.weighty = 1.0;
           // c.gridwidth = 2;
            c.gridx = 1;
            c.gridy = 0;
            this.add( initializeResources(),c);

        }
    }

    public BaseGame getGame(){
        return baseGame;
    }


    private JPanel initializeResources(){

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(size);
        mainPanel.setOpaque(false);

        mainPanel.setLayout( new BorderLayout() );

        Card card = baseGame.getCard();
        Grid grid = baseGame.getGrid();
        Cube cube = baseGame.getCube();

        gridUI = new GridUI(grid);
        addGridListeners();

        cubeUI = new CubeUI(cube);
        cubeUI.setPreferredSize( new Dimension(MainFrame.getInstance().getResolution().height/10,
                MainFrame.getInstance().getResolution().height/10));

        cardUI = new CardUI(card);

        JPanel cubeGridPanel = new JPanel();
        cubeGridPanel.setLayout( new FlowLayout(FlowLayout.CENTER, 20, 20));
        cubeGridPanel.setOpaque(false);

        JPanel cubePanel = new JPanel();
        cubePanel.setOpaque(false);
        cubePanel.setLayout( new FlowLayout(FlowLayout.CENTER,0,0));
        cubePanel.setBorder(new LineBorder(Color.BLACK, 5));

        cubePanel.add(cubeUI);
        cubeGridPanel.add(cubePanel);
        cubeGridPanel.add( Box.createHorizontalStrut( MainFrame.getInstance().getResolution().width/4)) ;
        cubeGridPanel.add(gridUI);


        cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setLayout( new FlowLayout(FlowLayout.CENTER,20,50));
        cardPanel.add(cardUI);

        mainPanel.add(cardPanel, BorderLayout.PAGE_START);
        mainPanel.add(cubeGridPanel, BorderLayout.CENTER);

        return mainPanel;

    }

    public void addGridListeners(){

        Component[] cubes = gridUI.getComponents();

        for (int i = 0; i < cubes.length; i++) {
            cubes[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mouseReleased(e);

                    String[] split = e.getComponent().getName().split(",");
                    int x = Integer.parseInt(split[0]);
                    int y = Integer.parseInt(split[1]);

                    if (baseGame.isGameFinished()){
                        // do something when game finished
                    }
                    else {
                        ((CubeUI) e.getComponent()).setCube(BaseGameScreen.this.getCube());
                        // add cube to game grid
                        gridUI.addCube(getCube(), x, y);
                        newCube();
                    }
                    repaint();
                }
            });
        }
    }

    public Cube getCube(){
        return baseGame.getCube();
    }

    public void newCube(){

        baseGame.setCube( Cube.getRandomCube() );
        cubeUI.setCube( baseGame.getCube() );

        cubeUI.repaint();

    }

    @Override
    public void update() {

    }

    @Override
    public void updateResolution(Dimension size) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage( background, 0, 0, getWidth(), getHeight(), this);
    }
}
