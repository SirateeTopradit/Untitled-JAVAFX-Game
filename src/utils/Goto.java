package utils;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.GamePanel;
import Pane.TitleScreen;
import pane.RootPane;

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

    public static void clear(){
        rootPane.getChildren().clear();
    }

}