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

        this.grid = grid;

        setLayout( new GridLayout(grid.getSize(), grid.getSize(), 10,10));
        setBackground( Color.BLACK);
        setBorder( new LineBorder(Color.BLACK, 20) );

        cubes = new CubeUI[ grid.getSize() ][grid.getSize()];

        for (int i = 0; i < grid.getSize(); i++){

            for (int j = 0; j < grid.getSize(); j++){

                cubes[i][j] = new CubeUI( grid.getCube(i,j) );
                cubes[i][j].setName(i + "," + j);
                add( cubes[i][j] );

            }
        }

    }

    public void addCube(Cube cube, int x, int y){
        grid.addCube(cube,x,y);
    }

}
