package kubitz.client.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoadingScreen extends JDialog{

    private static JLabel messageLabel;
    private static JPanel loadingPanel;
    private static JPanel labelPanel;
    private static LoadingScreen instance = null;
    private static String message;
    private static Thread loading;

    public LoadingScreen(){
        super( MainFrame.getInstance());

        instance = this;
        message = "";
        loading = null;

        loadingPanel = new JPanel();
        loadingPanel.setPreferredSize( new Dimension(500,200));
        loadingPanel.setBackground(Color.WHITE);
        loadingPanel.setBorder( new LineBorder(Color.BLACK,3));

        labelPanel = new JPanel();
        labelPanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        labelPanel.setOpaque(false);

        messageLabel = new JLabel(message);
        messageLabel.setFont( new Font( messageLabel.getFont().getName(), Font.PLAIN, 30));
        messageLabel.setForeground(Color.RED);

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

    public static void setMessage(String message) {
        LoadingScreen.message = message;
        messageLabel.setText(message);

        loadingPanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0,(500 - messageLabel.getPreferredSize().width)/2,0,0);
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        loadingPanel.add(labelPanel,c);

        instance.setVisible(true);
        instance.revalidate();
        instance.repaint();


    }

    public static void stop(){
        if ( loading != null){
            loading.stop();
            instance.setVisible(false);
            loading = null;
        }
    }

    public static void start() {
        stop();

        loading = new Thread(new LoadingThread());
        loading.start();
        instance.setVisible(true);


    }

    private static class LoadingThread implements Runnable{

        @Override
        public void run() {

            Graphics2D g = (Graphics2D) instance.getGraphics();
            if (g == null) {
                System.out.println("g is null");
                return;
            }

            int i = 0;
            while (true) {
                renderFrame(g, i);
                i++;
                if (i == 4) i = 0;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public void renderFrame(Graphics2D g, int frame) {

            //System.out.println("running");

            final String[] dots = {"", ".", "..", "...", "...."};
            messageLabel.setText(message + dots[frame%5]);
        }
    }

}
