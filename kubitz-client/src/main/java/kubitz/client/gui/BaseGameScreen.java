package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class BaseGameScreen extends JPanel implements Screen{

    private static BaseGameScreen instance = null;
    private GridUI gridUI;
    private CubeUI cubeUI;
    private CardUI cardUI;
    private Dimension size;
    private BaseGame baseGame;
    public Image background;

    public BaseGameScreen(BaseGame baseGame, Dimension size) {

        super();
        instance = this;
        this.size = size;

        this.baseGame = baseGame;

        background = new ImageIcon(getClass().getResource("/backgrounds/game_background.jpg")).getImage();

        if (this.baseGame != null)
            initializeResources();

    }

    public void setGame( BaseGame baseGame){
        removeAll();
        this.baseGame = baseGame;
        if (this.baseGame != null)
            initializeResources();
    }


    private void initializeResources(){

        this.setSize( size );
        this.setOpaque(false);
        this.setLayout( new BorderLayout() );

        Card card = baseGame.getCard();
        Grid grid = baseGame.getGrid();
        Cube cube = baseGame.getCube();

        gridUI = new GridUI(grid);
        addGridListeners();

        cubeUI = new CubeUI(cube);
        cardUI = new CardUI(card);

        JPanel cubeGridPanel = new JPanel();
        cubeGridPanel.setLayout( new FlowLayout(FlowLayout.CENTER, 20, 20));
        cubeGridPanel.setOpaque(false);

        JPanel cubePanel = new JPanel();
        cubePanel.setOpaque(false);
        cubePanel.setLayout( new GridBagLayout());

        cubePanel.add(cubeUI);
        cubeGridPanel.add(cubePanel);
        cubeGridPanel.add(gridUI);


        JPanel cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setLayout( new FlowLayout(FlowLayout.CENTER,20,50));
        cardPanel.add(cardUI);

        this.add(cardPanel, BorderLayout.PAGE_START);
        this.add(cubeGridPanel, BorderLayout.CENTER);

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

                    ((CubeUI) e.getComponent()).setCube(BaseGameScreen.getInstance().getCube());

                    gridUI.addCube(getCube(), x, y);

                    newCube();

                    repaint();
                }
            });
        }
    }

    public static BaseGameScreen getInstance(){
        return instance;
    }

    public Cube getCube(){
        return baseGame.getCube();
    }

    public void newCube(){
        Random random = new Random();

        baseGame.setCube( new Cube( random.nextInt(8) ) );
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
