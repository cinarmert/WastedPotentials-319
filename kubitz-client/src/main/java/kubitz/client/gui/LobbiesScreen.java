package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;
import kubitz.client.storage.Lobby;
import kubitz.client.websocket.WebSocketManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LobbiesScreen extends BaseScreen {

    private Filter filter;
    private JTable table;

    public LobbiesScreen(Dimension resolution, Filter filter) {

        super(resolution);
        this.requiresConnection = true;
        this.filter = filter;
        initializeResources();

    }

    @Override
    public void onError(){
        JOptionPane.showMessageDialog( ScreenManager.getScreen( ScreenManager.PLAY_SCREEN),
                "Connection Failed!",
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    protected void initializeResources(){

        GridBagConstraints c = new GridBagConstraints();

        setBackButton(true);

        c.insets = new Insets(30,30,0,0);
        c.anchor = GridBagConstraints.NORTH;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.addMain(initializeContainer(),c);
    }

    private JPanel initializeContainer(){
        JPanel lobbies = new JPanel();

        lobbies.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        lobbies.setBackground( Theme.tablePanelColor);
        lobbies.setBorder(new LineBorder(Theme.borderColor, 2));
        lobbies.setPreferredSize( new Dimension( getMainWidth()-(getMainWidth()/3), getMainHeight()-(getMainHeight()/3)));

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridx = 0;
        c.weighty = 1.0;
        c.weightx = 1.0;
        lobbies.add(initializeList(), c);

        c.gridx = 1;
        c.weightx = 1.0;
        lobbies.add(initializeButtons(), c);

        return lobbies;
    }

    private JPanel initializeButtons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground( Theme.tablePanelColor);
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(new CustomButton("Create"){{
            setPreferredSize( new Dimension(100,20));
            addActionListener(e->{

                ScreenManager.show(ScreenManager.CREATE_LOBBY_SCREEN);

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

                ScreenManager.show(ScreenManager.LOBBIES_FILTER_SCREEN);

            });
        }}, c);
        c.gridy = 3;
        //buttonPanel.add( Box.createVerticalStrut( getMainHeight()/4),c);
        buttonPanel.setPreferredSize( new Dimension(100, 340));
        return buttonPanel;
    }

    private JPanel initializeList(){
        JPanel list = new JPanel(new GridLayout(1,1));
        table = new JTable(null){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                Component returnComp = super.prepareRenderer(renderer, row, column);
                if (!returnComp.getBackground().equals(getSelectionBackground())){
                    Color bg = (row % 2 == 0 ? Theme.alternateColor1 : Theme.alternateColor2);
                    returnComp.setBackground(bg);
                }
                return returnComp;
            }
        };

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                joinLobby(e);
            }
        } );

        table.setAutoCreateRowSorter(true);
        table.setShowGrid(false);
        table.setRowHeight(30);
        table.getTableHeader().setBackground(Theme.tableHeaderColor);
        table.setSelectionBackground(Theme.tableHeaderColor);
        table.setOpaque(true);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Theme.alternateColor1);
        scrollPane.setOpaque(false);
        list.add(scrollPane);
        list.setPreferredSize(new Dimension(getMainWidth()-(getMainWidth()/3) - 150,
                getMainHeight()-(getMainHeight()/3)-10));
        return list;
    }

    private void joinLobby(MouseEvent e){
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

            if (lobby != null) {
                lobby.addPlayer(new Account(Config.getId(), Config.getName()));
                WebSocketManager.sendJoinLobbyMessage(lobby.getName());
            }

            ((LobbyScreen)ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN)).setCurrentLobby(lobby);

            ScreenManager.show(ScreenManager.LOBBY_SCREEN);
        }
    }

    private ArrayList<Lobby> getLobbies(){

        List<Lobby> gottenLobbies = RESTRequestManager.getLobbies();
        ArrayList<Lobby> lobbies = null;

        if (gottenLobbies != null) {
            lobbies = new ArrayList<>(gottenLobbies);

            lobbies.removeIf(l -> !(Arrays.asList(filter.getFilteredModeList()).contains(l.getMode())));
            lobbies.removeIf(l -> l.isFull() && !filter.getShowFull());
            lobbies.removeIf(l -> l.isPrivate() && !filter.getShowPrivate());
            lobbies.removeIf(l -> l.isPlaying() && !filter.getShowPlaying());

        }
        return lobbies;
    }

    public void refresh(){
        table.setModel(new LobbyTableModel());
    }


    class LobbyTableModel extends AbstractTableModel {
        private String[] columns = {"Name", "Players", "Mode", "Status"};

        ArrayList<Lobby> lobbies;

        public LobbyTableModel(){
            lobbies = getLobbies();
        }

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
                case 2 : obj = lobbies.get(rowIndex).getMode().substring(11);
                    break;
                case 3 : obj = lobbies.get(rowIndex).getStatus().substring(13);
                    break;
                default : obj = null;
                    break;
            }
            return obj;
        }
    }
}



