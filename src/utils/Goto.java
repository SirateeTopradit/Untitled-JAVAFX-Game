package utils;

import main.GamePanel;
import pane.TitleScreen;
import pane.RootPane;
import pane.GameOver;

public class Goto {
    private static RootPane rootPane;

    public static void setPane(RootPane pane){
        Goto.rootPane = pane;
    }

    public static void titleScreen(){
        clear();
        TitleScreen titleScreen = new TitleScreen();
        rootPane.getChildren().add(titleScreen);
    }

    public static void gamePanel(){
        clear();
        GamePanel gamePanel = new GamePanel();
        gamePanel.setUpGame();
        rootPane.getChildren().add(gamePanel);
        gamePanel.startGameThread();
    }
    public static void gameOver(long score, int killCount){
        rootPane.getChildren().removeIf(child -> child instanceof GamePanel);
        GameOver gameOver = new GameOver(score, killCount);
        rootPane.getChildren().add(gameOver);
    }

    public static void clear(){
        rootPane.getChildren().clear();
    }

}