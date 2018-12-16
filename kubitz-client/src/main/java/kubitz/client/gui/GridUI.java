package kubitz.client.gui;

import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridUI extends JPanel {

    Grid grid;
    CubeUI[][] cubes;

    public GridUI( Grid grid) {
        super();

        setGrid(grid);
    }

    public void resetGrid(){
        for (int i = 0; i < grid.getSize(); i++){
            for (int j = 0; j < grid.getSize(); j++){

                cubes[i][j].setCube(null);
            }
        }
        repaint();
    }

    public void setGrid(Grid grid){

        removeAll();

        this.grid = grid;

        setLayout( new GridLayout(grid.getSize(), grid.getSize(), 10,10));
        setBackground( Theme.borderColor);
        setBorder( new LineBorder(Theme.borderColor, 20) );

        cubes = new CubeUI[ grid.getSize() ][grid.getSize()];

        for (int i = 0; i < grid.getSize(); i++){

            for (int j = 0; j < grid.getSize(); j++){

                cubes[i][j] = new CubeUI( grid.getCube(i,j) );
                cubes[i][j].setName(i + "," + j);
                cubes[i][j].setPreferredSize( new Dimension(MainFrame.getInstance().getResolution().height/12,
                        MainFrame.getInstance().getResolution().height/12));
                add( cubes[i][j] );

            }
        }
        repaint();
    }

    public void addCube(Cube cube, int x, int y){

        cubes[x][y].setCube(cube);
        grid.addCube(cube,x,y);
    }

    public void removeCube(int x, int y){

        cubes[x][y].setCube(null);
        grid.removeCube(x,y);
    }

}
