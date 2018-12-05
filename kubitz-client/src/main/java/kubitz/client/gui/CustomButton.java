package kubitz.client.gui;


import kubitz.client.sound.SoundManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomButton extends JButton {
    private SoundManager soundManager;

    public CustomButton(String label) {

        super(label);

        soundManager = new SoundManager();
        addActionListener(e->{
            soundManager.startMenuClickSound();
        });

        setUI( new CustomButtonUI());
        setFocusable( false);
        setBorder( new LineBorder( Color.BLACK, 2) ); // if different borders will be used, this can be added to mouse listener.
    }

    private class CustomButtonUI extends BasicButtonUI implements MouseListener{

        private final Color NORMALCOLOR = Color.WHITE;
        private final Color HOVERCOLOR = Color.RED;
        private final Color PRESSEDCOLOR = new Color( 140, 0, 0, 255);
        private final Color NORMALFONTCOLOR = Color.RED;
        private final Color HOVERFONTCOLOR = Color.WHITE;
        private final Color PRESSEDFONTCOLOR = Color.WHITE;

        public CustomButtonUI() {
            super();
        }

        @Override
        public void installUI(JComponent comp) {
            super.installUI(comp);
            comp.addMouseListener(this);
        }

        @Override
        public void uninstallUI(JComponent comp) {
            super.uninstallUI(comp);
            comp.removeMouseListener(this);
        }

        @Override
        protected void installDefaults(AbstractButton btn) {
            super.installDefaults(btn);
            btn.setBackground(this.NORMALCOLOR);
            btn.setForeground(this.NORMALFONTCOLOR);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
        }

        private void changeButtonStyle(JButton btn, Color background, Color foreground ){
            btn.setBackground( background);
            btn.setForeground( foreground);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            changeButtonStyle( (JButton) e.getComponent(), this.PRESSEDCOLOR, this.PRESSEDFONTCOLOR);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            changeButtonStyle( (JButton) e.getComponent(), this.PRESSEDCOLOR, this.PRESSEDFONTCOLOR);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            changeButtonStyle( (JButton) e.getComponent(), this.NORMALCOLOR, this.NORMALFONTCOLOR);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            changeButtonStyle( (JButton) e.getComponent(), this.HOVERCOLOR, this.HOVERFONTCOLOR);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            changeButtonStyle( (JButton) e.getComponent(), this.NORMALCOLOR, this.NORMALFONTCOLOR);
        }


    }


}
