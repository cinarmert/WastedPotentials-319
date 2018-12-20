package kubitz.client.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollbarUI extends BasicScrollBarUI{

    private JScrollPane sp;

    public CustomScrollbarUI(JScrollPane sp) {
        this.sp = sp;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {

        CustomButton up = new CustomButton("\u2BC5");
        up.setBorder(BorderFactory.createMatteBorder(
                0, 0, 2, 0, Theme.borderColor));
        return up;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        CustomButton down = new CustomButton("\u2BC6");
        down.setBorder(BorderFactory.createMatteBorder(
                2, 0, 0, 0, Theme.borderColor));
        return down;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

        c.setBorder(new LineBorder(Theme.borderColor,2));
        g.setColor(Theme.normalColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        int x = thumbBounds.x;
        int y = thumbBounds.y;

        int width = thumbBounds.width;
        int height = thumbBounds.height;

        g.setColor(Theme.pressColor);
        g.fillRect(x, y, width, height);
    }

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
        sp.repaint();
    }

}
