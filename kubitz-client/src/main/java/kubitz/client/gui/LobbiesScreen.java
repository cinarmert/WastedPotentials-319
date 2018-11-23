package kubitz.client.gui;

import kubitz.client.storage.Lobby;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class LobbiesScreen extends JPanel implements Screen {

    private JPanel contentPane;
    private Dimension size;
    private Filter filter;
    private JTable table;


    public LobbiesScreen(JPanel contentPane, Dimension size, Filter filter) {

        super();
        this.filter = filter;
        this.contentPane = contentPane;
        this.size = size;
        initializeResources();

    }

    private void initializeResources(){

        setSize(size);
        setBackground( new Color(204,204,204));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        CustomButton backButton = new CustomButton("BACK");
        backButton.addActionListener(e -> {

            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, MainFrame.PLAY);

        });
        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add(backButton,c);
        c.insets = new Insets(30,30,0,0);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 1;
        this.add(initializeContainer(),c);
    }

    private JPanel initializeContainer(){
        JPanel lobbies = new JPanel(){{
            setLayout(new BorderLayout());
            setBackground( new Color(204,204,204));
            setBorder(new LineBorder(Color.BLACK, 2));
            setPreferredSize( new Dimension( size.width-(size.width/3), size.height-(size.width/3)));
            add(initializeList(), BorderLayout.LINE_START);
            add(initializeButtons(), BorderLayout.CENTER);
        }};
        return lobbies;
    }

    private JPanel initializeButtons(){
        JPanel buttonPanel = new JPanel(){{
            setBackground( new Color(204,204,204));
            setLayout(new GridBagLayout());
        }};
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(new CustomButton("Create"){{
            setPreferredSize( new Dimension(100,20));
            addActionListener(e->{

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.CREATELOBBY);

            });
        }}, c);
        c.insets = new Insets(20,0,0,0);
        c.gridy = 1;
        buttonPanel.add(new CustomButton("Refresh"){{
            setPreferredSize( new Dimension(100,20));
            addActionListener(e-> refresh());
        }}, c);
        c.gridy = 2;
        buttonPanel.add(new CustomButton("Filter"){{
            setPreferredSize( new Dimension(100,20));
            addActionListener(e->{

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.LOBBIESFILTER);

            });
        }}, c);
        c.gridy = 3;
        buttonPanel.add( Box.createVerticalStrut( size.height/4),c);
        return buttonPanel;
    }

    private JPanel initializeList(){
        JPanel list = new JPanel();
        table = new JTable(new LobbyTableModel()){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(221,221,221);
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())){
                    Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
                    returnComp .setBackground(bg);
                    bg = null;
                }
                return returnComp;
            }
        };
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){
                    String lobbyName = (String)table.getValueAt(table.getSelectedRow(),0);
                    Lobby lobby = null;
                    for(Lobby l : getLobbies())
                    {
                        if(l.getName().equals(lobbyName)) {
                            lobby = l;
                            break;
                        }
                    }
                    MainFrame.lobbyScreen.setCurrentLobby(lobby);
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.show(contentPane, MainFrame.LOBBY);
                }
            }
        } );

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
        list.add(scrollPane);


        return list;
    }

    private ArrayList<Lobby> getLobbies(){


        ArrayList<Lobby> lobbies = new ArrayList();

        for(int i = 0; i < 20;i++){
            if(i < 5)
                lobbies.add(new Lobby("Test Lobby" + i, Lobby.MODE_SWITCH, 2,false,  i%4 + 1,Lobby.STATUS_WAITING));
            else
                lobbies.add(new Lobby("Test Lobby" + i, Lobby.MODE_CLASSIC, 4,false,i%4 + 1, Lobby.STATUS_WAITING));
        }


        lobbies.removeIf(l -> !(Arrays.asList(filter.getFilteredModeList()).contains(l.getMode())));
        lobbies.removeIf(l -> l.isFull() && !filter.getShowFull());
        lobbies.removeIf(l -> l.isPrivate() && !filter.getShowPrivate());
        lobbies.removeIf(l-> l.isPlaying() &&  !filter.getShowPlaying());


        return lobbies;
    }

    private void refresh(){
        table.setModel(new LobbyTableModel());

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

        //ToDo handle size change
        this.size = size;
    }

    class LobbyTableModel extends AbstractTableModel {
        private String[] columns = {"Name", "Players", "Mode", "Status"};

        ArrayList<Lobby> lobbies = getLobbies();

        public String getColumnName(int col) {
            return columns[col];
        }

        @Override
        public int getRowCount() {
            return lobbies.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object obj;
            switch (columnIndex){
                case 0 : obj = lobbies.get(rowIndex).getName();
                    break;
                case 1 : obj = lobbies.get(rowIndex).getPlayerCount() + "/" + lobbies.get(rowIndex).getMaxPlayerLimit();
                    break;
                case 2 : obj = lobbies.get(rowIndex).getMode();
                    break;
                case 3 : obj = lobbies.get(rowIndex).getStatus();
                    break;
                default : obj = null;
                    break;
            }
            return obj;
        }
    }
}



