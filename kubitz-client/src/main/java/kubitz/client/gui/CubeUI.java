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
            setBackground(Theme.nullColor);
        else
            setBackground(Theme.secondaryColor);
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

        setBackground( Theme.nullColor);

        if (cube != null) {
            g.setColor(Theme.primaryColor);
            setBackground( Theme.secondaryColor);

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
                    setBackground(Theme.primaryColor);
                    break;
                case Cube.WHITE:
                    setBackground(Theme.secondaryColor);
                    break;
                case Cube.WHITE_DOT:
                    setBackground(Theme.primaryColor);
                    g.setColor(Theme.secondaryColor);
                    g.fillOval(0, 0, getWidth(), getHeight());
                    break;
                case Cube.TRIANGLE_LD:
                    setBackground(Theme.primaryColor);
                    g.setColor(Theme.secondaryColor);
                    g.fillPolygon( triangleRight );
                    break;
                case Cube.TRIANGLE_LU:
                    g.fillPolygon( triangleLeft );
                    break;
                case Cube.TRIANGLE_RD:
                    setBackground(Theme.primaryColor);
                    g.setColor(Theme.secondaryColor);
                    g.fillPolygon( triangleLeft );
                    break;
                case Cube.TRIANGLE_RU:
                    g.fillPolygon( triangleRight );
                    break;
                default:
                    setBackground(Theme.secondaryColor);
                    break;
            }
        }
    }
}
