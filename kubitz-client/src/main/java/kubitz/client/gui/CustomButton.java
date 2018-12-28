package kubitz.client.gui;


import kubitz.client.sound.SoundManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomButton extends JButton {

    public CustomButton(String label) {

        super(label);

        setUI( new CustomButtonUI());
        setFocusable( false);
        setBorder( new LineBorder( Color.BLACK, 2) ); // if different borders will be used, this can be added to mouse listener.
    }

    private class CustomButtonUI extends BasicButtonUI implements MouseListener{

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
            btn.setBackground(Theme.normalColor);
            btn.setForeground(Theme.normalFontColor);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
        }

        private void changeButtonStyle(JButton btn, Color background, Color foreground ){
            btn.setBackground( background);
            btn.setForeground( foreground);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (isEnabled()) {
                changeButtonStyle((JButton) e.getComponent(), Theme.pressColor, Theme.pressFontColor);
                SoundManager.startMenuClickSound();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (isEnabled()) {
                changeButtonStyle((JButton) e.getComponent(), Theme.normalColor, Theme.normalFontColor);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (isEnabled()) {
                changeButtonStyle((JButton) e.getComponent(), Theme.hoverColor, Theme.hoverFontColor);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (isEnabled()) {
                changeButtonStyle((JButton) e.getComponent(), Theme.normalColor, Theme.normalFontColor);
            }
        }


    }


}
