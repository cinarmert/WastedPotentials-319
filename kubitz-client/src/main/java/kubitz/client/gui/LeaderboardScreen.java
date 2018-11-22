package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Leaderboard;
import kubitz.client.storage.LeaderboardUser;
import kubitz.client.storage.Lobby;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardScreen extends JPanel implements Screen {

    JPanel contentPane;
    Dimension size;
    private Leaderboard leaderboard;

    public LeaderboardScreen(JPanel contentPane, Dimension size) {
        super();
        this.contentPane = contentPane;
        this.size = size;
        initializeResources();
    }

    private void initializeResources(){

        setLeaderBoard();
        this.setSize( size );
        this.setBackground( new Color(0,0,0,0));
        this.setLayout( new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        CustomButton backButton = new CustomButton("BACK");
        backButton.addActionListener(e -> {

            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, MainFrame.MAINMENU);

        });

        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.1;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add( backButton,c);

        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 1;
        this.add( initializeLeaderboard(),c);

    }

    private void setLeaderBoard(){
        leaderboard = RESTRequestManager.getDailyChallengeLeaderboard();
        if (leaderboard == null) {
            leaderboard = new Leaderboard();
        }
        Collections.sort(leaderboard.getLeaderboard(), new LeaderBoardUserScoreComparator());
        leaderboard.setLeaderboard(leaderboard.getLeaderboard());

    }
    
    private JPanel initializeLeaderboard(){

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( new GridLayout(1,1, 10, 10));
        mainPanel.setPreferredSize(new Dimension( getWidth()-250, getHeight()-200));
        mainPanel.setBackground(new Color(0,0,0,200));
        JTable table = new JTable(new LobbyTableModel()){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(220,221,221);
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())){
                    Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
                    returnComp .setBackground(bg);
                    bg = null;
                }
                return returnComp;
            }
        };;
        table.setAutoCreateRowSorter(true);
        table.setShowGrid(false);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(153,153,153));
        table.setSelectionBackground(new Color(153,153,153));
        table.setOpaque(true);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(221,221,221));
        scrollPane.setOpaque(false);
        mainPanel.add(scrollPane);
        return mainPanel;
        
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage( MainFrame.background, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateResolution(Dimension size) {
        this.size = size;
    }

    class LobbyTableModel extends AbstractTableModel {



        private String[] columns = {"Place", "Name", "Score"};

        public String getColumnName(int col) {
            return columns[col];
        }


        @Override
        public int getRowCount() {
            return leaderboard.getLeaderboard().size();
        }



        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object obj;
            switch (columnIndex){
                case 0 : obj = rowIndex + 1;
                    break;
                case 1 : obj = leaderboard.getLeaderboard().get(rowIndex).getName();
                    break;
                case 2 : obj = leaderboard.getLeaderboard().get(rowIndex).getScore();
                    break;
                default : obj = null;
                    break;
            }
            return obj;
        }
    }
    public class LeaderBoardUserScoreComparator implements Comparator<LeaderboardUser> {
        @Override
        public int compare(LeaderboardUser u1, LeaderboardUser u2) {
            if(u1.getScore() > u2.getScore())
                return -1;
            else if(u1.getScore() == u2.getScore())
                return 0;
            else
                return 1;
        }
    }
}
