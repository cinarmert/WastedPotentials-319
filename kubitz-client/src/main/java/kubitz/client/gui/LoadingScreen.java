package kubitz.client.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingScreen extends JDialog implements Runnable{

    private JLabel messageLabel;
    private String message;
    private Timer timer;

    public LoadingScreen( String message){
        super( MainFrame.getInstance(),"Loading", ModalityType.APPLICATION_MODAL);

        this.message = message;

        JPanel loadingPanel = new JPanel();
        loadingPanel.setPreferredSize( new Dimension(500,200));
        loadingPanel.setBackground(Theme.backgroundColor);
        loadingPanel.setBorder( new LineBorder(Theme.borderColor,3));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        labelPanel.setOpaque(false);

        messageLabel = new JLabel(message);
        messageLabel.setFont( new Font( messageLabel.getFont().getName(), Font.PLAIN, 30));
        messageLabel.setForeground(Theme.foregroundColor);

        labelPanel.add(messageLabel);

        loadingPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0,(500 - messageLabel.getPreferredSize().width)/2,0,0);
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        loadingPanel.add(labelPanel,c);

        add(loadingPanel);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(false);

    }

    public void stop(){
        timer.stop();
        dispose();
    }

    private void start() {

        Graphics2D g = (Graphics2D) getGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }

        timer = new Timer(200, new ActionListener() {

            int i = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                renderFrame(g, i);
                i++;
                if (i == 6) i = 0;
            }
        });

        timer.start();
        setVisible(true);


    }

    public void renderFrame(Graphics2D g, int frame) {

        // ToDo can make animation

        final String[] dots = {"", ".", "..", "...", "...."};
        messageLabel.setText(message + dots[frame%5]);
        repaint();
    }

    @Override
    public void run() {
        start();
    }

}
