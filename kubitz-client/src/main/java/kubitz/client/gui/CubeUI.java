package kubitz.client.gui;

import kubitz.client.components.Cube;

import javax.swing.*;
import java.awt.*;

public class CubeUI extends JPanel {

    private Cube cube;

    public CubeUI( Cube cube) {
        // responsive size
        setPreferredSize( new Dimension(50,50));
        setBackground(Color.WHITE);
        this.cube = cube;
    }

    public void setCube(Cube cube){
        this.cube = cube;
        repaint();
    }

    @Override
    public void paintComponent( Graphics g){
        super.paintComponent(g);

        if (cube != null) {
            g.setColor(Color.RED);

            Polygon triangleLeft = new Polygon();
            triangleLeft.addPoint(0,0);
            triangleLeft.addPoint(0,getWidth());
            triangleLeft.addPoint(getHeight(),0);

            Polygon triangleRight = new Polygon();
            triangleRight.addPoint(0,0);
            triangleRight.addPoint(0,getWidth());
            triangleRight.addPoint(getHeight(),getHeight());

            switch (cube.getCurrentFace()) {
                case RED_DOT:
                    g.fillOval(0, 0, getWidth(), getHeight());
                    break;
                case RED:
                    setBackground(Color.RED);
                    break;
                case WHITE:
                    setBackground(Color.WHITE);
                    break;
                case WHITE_DOT:
                    setBackground(Color.RED);
                    g.setColor(Color.WHITE);
                    g.fillOval(0, 0, getWidth(), getHeight());
                    break;
                case TRIANGLE_LD:
                    g.fillPolygon( triangleLeft );
                    break;
                case TRIANGLE_LU:
                    setBackground(Color.RED);
                    g.setColor(Color.WHITE);
                    g.fillPolygon( triangleLeft );
                    break;
                case TRIANGLE_RD:
                    g.fillPolygon( triangleRight );
                    break;
                case TRIANGLE_RU:
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
