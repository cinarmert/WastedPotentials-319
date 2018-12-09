package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.logic.BaseGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BaseGameScreen extends BaseScreen{

    protected GridUI gridUI;
    private CubeUI cubeUI;
    private CubeUI cubeEastUI;
    private CubeUI cubeWestUI;
    private CubeUI cubeNorthUI;
    private CubeUI cubeSouthUI;
    protected CardUI cardUI;
    private BaseGame baseGame;
    protected JPanel cardPanel;
    private Cube selectedCube;
    private int[] selectedCubePos;


    public BaseGameScreen( Dimension resolution) {

        super(resolution);

    }

    public void setGame( BaseGame baseGame){
        this.baseGame = baseGame;
        initializeResources();
    }

    public BaseGame getGame(){
        return baseGame;
    }


    @Override
    protected void initializeResources(){
        removeMainAll();
        if (this.baseGame != null) {

            GridBagConstraints c = new GridBagConstraints();

            setBackButton(true);

            c.anchor = GridBagConstraints.NORTH;
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1.0;
            c.weighty = 1.0;
            c.gridx = 0;
            c.gridy = 0;
            this.addMain( initializeContent(),c);

        }
    }



    private JPanel initializeContent(){

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(getMainWidth(), getMainHeight()));
        mainPanel.setOpaque(false);

        mainPanel.setLayout( new BorderLayout() );

        Card card = baseGame.getCard();
        Grid grid = baseGame.getGrid();
        Cube cube = baseGame.getCube();

        selectedCube = cube;
        selectedCubePos = new int[2];
        selectedCubePos[0] = -1;
        selectedCubePos[1] = -1;

        gridUI = new GridUI(grid);
        addGridListeners();

        cubeUI = new CubeUI(cube);
        cubeEastUI = new CubeUI(new Cube(cubeUI.getCube().getCurrentFace())){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Theme.maskColorLight);
                g.fillRect(0,0,getWidth(),getHeight());
            }
        };

        cubeEastUI.getCube().rotate(1,0,0);
        cubeWestUI = new CubeUI(new Cube(cubeUI.getCube().getCurrentFace())){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Theme.maskColorLight);
                g.fillRect(0,0,getWidth(),getHeight());
            }
        };

        cubeWestUI.getCube().rotate(-1,0,0);
        cubeNorthUI = new CubeUI(new Cube(cubeUI.getCube().getCurrentFace())){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Theme.maskColorLight);
                g.fillRect(0,0,getWidth(),getHeight());
            }
        };

        cubeNorthUI.getCube().rotate(0,1,0);
        cubeSouthUI = new CubeUI(new Cube(cubeUI.getCube().getCurrentFace())){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor( Theme.maskColorLight);
                g.fillRect(0,0,getWidth(),getHeight());
            }
        };

        cubeSouthUI.getCube().rotate(0,-1,0);

        Dimension cubeDimension = new Dimension(getMainHeight()/12,
                getMainHeight()/12);

        cubeEastUI.setPreferredSize( cubeDimension);
        cubeWestUI.setPreferredSize( cubeDimension);
        cubeNorthUI.setPreferredSize( cubeDimension);
        cubeSouthUI.setPreferredSize( cubeDimension);

        cubeUI.setPreferredSize( new Dimension(getMainHeight()/10, getMainHeight()/10));
        cubeUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                BaseGameScreen.this.selectedCube = ((CubeUI)e.getComponent()).getCube();
                selectedCubePos[0] = -1;
                selectedCubePos[1] = -1;

            }
        });

        cardUI = new CardUI(card);

        JPanel cubeGridPanel = new JPanel();
        cubeGridPanel.setLayout( new FlowLayout(FlowLayout.CENTER, 20, 20));
        cubeGridPanel.setOpaque(false);

        JPanel cubePanel = new JPanel( new GridLayout(3,3));
        cubePanel.setOpaque(false);

        cubePanel.add(Box.createHorizontalStrut(MainFrame.getInstance().getResolution().height/12));
        cubePanel.add(cubeNorthUI);
        cubePanel.add(Box.createHorizontalStrut(MainFrame.getInstance().getResolution().height/12));
        cubePanel.add(cubeWestUI, BorderLayout.WEST);
        cubePanel.add(cubeUI, BorderLayout.CENTER);
        cubePanel.add(cubeEastUI, BorderLayout.EAST);
        cubePanel.add(Box.createHorizontalStrut(MainFrame.getInstance().getResolution().height/12));
        cubePanel.add(cubeSouthUI, BorderLayout.SOUTH);
        cubePanel.add(Box.createHorizontalStrut(MainFrame.getInstance().getResolution().height/12));

        cubeGridPanel.add(cubePanel);
        cubeGridPanel.add( Box.createHorizontalStrut( MainFrame.getInstance().getResolution().width/5)) ;
        cubeGridPanel.add(gridUI);


        cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setLayout( new FlowLayout(FlowLayout.CENTER,20,50));
        cardPanel.add(cardUI);

        mainPanel.add(cardPanel, BorderLayout.PAGE_START);
        mainPanel.add(cubeGridPanel, BorderLayout.CENTER);

        return mainPanel;

    }

    public void updateCubes()
    {
        cubeEastUI.setCube(new Cube(cubeUI.getCube().getCurrentFace()));
        cubeEastUI.getCube().rotate(-1,0,0);
        cubeWestUI.setCube(new Cube(cubeUI.getCube().getCurrentFace()));
        cubeWestUI.getCube().rotate(1,0,0);
        cubeNorthUI.setCube(new Cube(cubeUI.getCube().getCurrentFace()));
        cubeNorthUI.getCube().rotate(0,1,0);
        cubeSouthUI.setCube(new Cube(cubeUI.getCube().getCurrentFace()));
        cubeSouthUI.getCube().rotate(0,-1,0);
        cubeNorthUI.repaint();
        cubeSouthUI.repaint();
        cubeWestUI.repaint();
        cubeEastUI.repaint();

    }
    private void addGridListeners(){

        Component[] cubes = gridUI.getComponents();

        for (int i = 0; i < cubes.length; i++) {
            cubes[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);

                    String[] split = e.getComponent().getName().split(",");
                    int x = Integer.parseInt(split[0]);
                    int y = Integer.parseInt(split[1]);

                    if (SwingUtilities.isLeftMouseButton(e)) {

                        if ( !baseGame.isGameFinished()) {

                            gridUI.addCube(getSelectedCube(), x, y);

                            if (getSelectedCubePos()[0] == -1 && getSelectedCubePos()[1] == -1) {
                                baseGame.addCubeToGrid(BaseGameScreen.this.getSelectedCube(), x, y);
                                newCube();
                            }
                            else {
                                baseGame.moveCube(getSelectedCubePos()[0],getSelectedCubePos()[1],x,y);
                                gridUI.removeCube(getSelectedCubePos()[0], getSelectedCubePos()[1]);

                                BaseGameScreen.this.selectedCubePos[0] = x;
                                BaseGameScreen.this.selectedCubePos[1] = y;

                                BaseGameScreen.this.selectedCube = baseGame.getGrid().getCube(x,y);
                          }

                        }

                    }
                    else if (SwingUtilities.isRightMouseButton(e)){
                        BaseGameScreen.this.selectedCube = baseGame.getGrid().getCube(x,y);
                        BaseGameScreen.this.selectedCubePos[0] = x;
                        BaseGameScreen.this.selectedCubePos[1] = y;

                    }

                    if (baseGame.isGameFinished()) {
                        onGameFinished();
                    }
                    repaint();
                }
            });

        }
    }

    public int[] getSelectedCubePos(){
        return selectedCubePos;
    }

    public Cube getSelectedCube(){
        return selectedCube;
    }

    public Cube getCube(){
        return baseGame.getCube();
    }

    public void newCube(){


        baseGame.setCube( Cube.getRandomCube() );
        cubeUI.setCube( baseGame.getCube() );
        selectedCube = baseGame.getCube();
        updateCubes();
        cubeUI.repaint();

    }

    public abstract void onGameFinished();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage( Theme.gameBackgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
