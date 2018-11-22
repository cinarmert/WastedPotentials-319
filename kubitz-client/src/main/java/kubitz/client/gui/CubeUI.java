package kubitz.client.gui;

import kubitz.client.components.Cube;

import javax.swing.*;
import java.awt.*;

public class CubeUI extends JPanel {

    private Cube cube;

    public CubeUI( Cube cube) {
        // responsive size
        setPreferredSize( new Dimension(MainFrame.getInstance().getResolution().height/20,
                MainFrame.getInstance().getResolution().height/20));

        if ( cube == null)
            setBackground(Color.GRAY);
        else
            setBackground(Color.WHITE);
        this.cube = cube;
    }

    public void setCube(Cube cube){
        this.cube = cube;
        repaint();
    }

    public Cube getCube(){
        return cube;
    }

    @Override
    public void paintComponent( Graphics g){
        super.paintComponent(g);

        setBackground( Color.GRAY);

        if (cube != null) {
            g.setColor(Color.RED);
            setBackground( Color.WHITE);

            Polygon triangleLeft = new Polygon();
            triangleLeft.addPoint(0,0);
            triangleLeft.addPoint(0,getWidth());
            triangleLeft.addPoint(getHeight(),0);

            Polygon triangleRight = new Polygon();
            triangleRight.addPoint(0,0);
            triangleRight.addPoint(0,getWidth());
            triangleRight.addPoint(getHeight(),getHeight());

            switch (cube.getCurrentFace()) {
                case Cube.RED_DOT:
                    g.fillOval(0, 0, getWidth(), getHeight());
                    break;
                case Cube.RED:
                    setBackground(Color.RED);
                    break;
                case Cube.WHITE:
                    setBackground(Color.WHITE);
                    break;
                case Cube.WHITE_DOT:
                    setBackground(Color.RED);
                    g.setColor(Color.WHITE);
                    g.fillOval(0, 0, getWidth(), getHeight());
                    break;
                case Cube.TRIANGLE_LD:
                    g.fillPolygon( triangleLeft );
                    break;
                case Cube.TRIANGLE_LU:
                    setBackground(Color.RED);
                    g.setColor(Color.WHITE);
                    g.fillPolygon( triangleLeft );
                    break;
                case Cube.TRIANGLE_RD:
                    g.fillPolygon( triangleRight );
                    break;
                case Cube.TRIANGLE_RU:
                    setBackground(Color.RED);
                    g.setColor(Color.WHITE);
                    g.fillPolygon( triangleRight );
                    break;
                default:
                    setBackground(Color.WHITE);
                    break;
            }
        }
    }
}
