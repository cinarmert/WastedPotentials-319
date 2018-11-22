package kubitz.client.gui;

class Lobby{
    private String name;
    private int playerCount;
    private String mode;
    private String status;
    private int noOfPlayers;
    private boolean privateLobby;

    final static String SWITCH = "SWITCH";
    final static String CLASSIC = "CLASSIC";


    public Lobby( String name, String mode, int noOfPlayers, boolean privateLobby ){
        this.name = name;
        this.mode = mode;
        this.noOfPlayers = noOfPlayers;
        this.privateLobby = privateLobby;
        playerCount = 1;
        status = "Waiting";
    }

    public Lobby(String name, String mode, int noOfPlayers, boolean privateLobby, int playerCount, String status ){
        this.name = name;
        this.mode = mode;
        this.noOfPlayers = noOfPlayers;
        this.privateLobby = privateLobby;
        this.playerCount = playerCount;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public String getMode() {
        return mode;
    }

    public String getStatus() {
        return status;
    }

    public boolean isPrivate() {
        return privateLobby;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public boolean isFull(){
        return(noOfPlayers == playerCount);
    }

    public boolean isPlaying(){
        return !status.equals("Waiting");
    }
}