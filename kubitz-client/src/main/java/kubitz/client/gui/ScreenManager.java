package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScreenManager extends JPanel {

    private static ArrayList<BaseScreen> screens;
    private static ScreenStack stack;
    private static ScreenManager instance = null;


    public static final int MAIN_MENU_SCREEN = 0;
    public static final int PLAY_SCREEN = 1;
    public static final int HOW_TO_PLAY_SCREEN = 2;
    public static final int CREDITS_SCREEN = 3;
    public static final int SETTINGS_SCREEN = 4;
    public static final int LOBBIES_SCREEN = 5;
    public static final int LOBBY_SCREEN = 6;
    public static final int LOBBY_SETTINGS_SCREEN = 7;
    public static final int LOBBIES_FILTER_SCREEN = 8;
    public static final int CREATE_LOBBY_SCREEN = 9;
    public static final int LEADERBOARD_SCREEN = 10;
    public static final int KEY_BINDING_SCREEN = 11;

    public ScreenManager() {
        super();

        instance = this;

        stack = new ScreenStack();
        screens = new ArrayList<>();
        Filter filter = new Filter();

        ScreenManager.screens.add( MAIN_MENU_SCREEN, new MainMenuScreen(MainFrame.getResolution()));
        ScreenManager.screens.add( PLAY_SCREEN, new PlayScreen(MainFrame.getResolution()));
        ScreenManager.screens.add( HOW_TO_PLAY_SCREEN, new HowToPlayScreen(MainFrame.getResolution()));
        ScreenManager.screens.add( CREDITS_SCREEN, new CreditsScreen(MainFrame.getResolution()));
        ScreenManager.screens.add( SETTINGS_SCREEN, new SettingsScreen(MainFrame.getResolution()));
        ScreenManager.screens.add( LOBBIES_SCREEN, new LobbiesScreen(MainFrame.getResolution(), filter));
        ScreenManager.screens.add( LOBBY_SCREEN, new LobbyScreen(MainFrame.getResolution()));
        ScreenManager.screens.add( LOBBY_SETTINGS_SCREEN, new LobbySettingsScreen(MainFrame.getResolution()));
        ScreenManager.screens.add( LOBBIES_FILTER_SCREEN, new LobbiesFilterScreen(MainFrame.getResolution(), filter ));
        ScreenManager.screens.add( CREATE_LOBBY_SCREEN, new CreateLobbyScreen(MainFrame.getResolution()));
        ScreenManager.screens.add( LEADERBOARD_SCREEN, new LeaderboardScreen(MainFrame.getResolution()));
        ScreenManager.screens.add( KEY_BINDING_SCREEN, new KeyBindingScreen(MainFrame.getResolution()));

        setLayout( new BorderLayout());

        show(MAIN_MENU_SCREEN);
    }

    public static BaseScreen getScreen( int screen){
        return screens.get(screen);
    }

    public static void back(){
        BaseScreen screen_ = stack.peek();
        screen_.onHide();
        stack.pop();
        screen_ = stack.peek();

        while(!canShown(screen_)){
            screen_.onHide();
            stack.pop();
            screen_ = stack.peek();
        }
        synchronizeStackWithScreen();
    }

    public static boolean canShown(BaseScreen screen){
        if(screen.doesRequireConnection() && !RESTRequestManager.checkServerConnection()) {
            return false;
        }

        return true;
    }

    public static void synchronizeStackWithScreen(){
        instance.removeAll();
        instance.add(stack.peek());
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }

    public static boolean show(int screen){
        BaseScreen screen_ = screens.get(screen);

        if(!canShown(screen_))
            return false;

        screen_.onShow();
        stack.push(screen_);
        synchronizeStackWithScreen();
        return true;
    }

    public static void openGameScreen(BaseGameScreen screen){

        screen.onShow();

        instance.removeAll();
        stack.push( screen);
        instance.add(stack.peek());

        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();

    }

    public static void doubleBack(){

        BaseScreen screen_ = stack.peek();
        screen_.onHide();
        stack.pop();
        back();

    }

    public static BaseScreen getCurrentScreen(){
        return stack.peek();
    }

    public static void updateResolutions(){
        for (BaseScreen screen : screens){
            screen.updateResolution(MainFrame.getResolution());
        }

        instance.removeAll();
        instance.add(stack.peek());

        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }

}
