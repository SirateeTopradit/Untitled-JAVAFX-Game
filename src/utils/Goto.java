package utils;

import main.GamePanel;
import pane.TitleScreen;
import pane.RootPane;
import pane.GameOver;

/**
 * Goto class is responsible for managing the game screens.
 * This includes setting the root pane, transitioning to the title screen, game panel, and game over screen.
 */
public class Goto {
    private static RootPane rootPane;

    /**
     * Sets the root pane for the game screens.
     *
     * @param pane  the root pane instance
     */
    public static void setPane(RootPane pane){
        Goto.rootPane = pane;
    }

    /**
     * Transitions to the title screen.
     * Clears the root pane and adds the title screen to it.
     */
    public static void titleScreen(){
        clear();
        TitleScreen titleScreen = new TitleScreen();
        rootPane.getChildren().add(titleScreen);
    }

    /**
     * Transitions to the game panel.
     * Clears the root pane, sets up the game, adds the game panel to the root pane, and starts the game thread.
     */
    public static void gamePanel(){
        clear();
        GamePanel gamePanel = new GamePanel();
        gamePanel.setUpGame();
        rootPane.getChildren().add(gamePanel);
        gamePanel.startGameThread();
    }

    /**
     * Transitions to the game over screen.
     * Removes the game panel from the root pane, creates a new game over screen with the given score and kill count, and adds it to the root pane.
     *
     * @param score  the final score
     * @param killCount  the final kill count
     */
    public static void gameOver(long score, int killCount){
        rootPane.getChildren().removeIf(child -> child instanceof GamePanel);
        GameOver gameOver = new GameOver(score, killCount);
        rootPane.getChildren().add(gameOver);
    }

    /**
     * Clears the root pane by removing all its children.
     */
    public static void clear(){
        rootPane.getChildren().clear();
    }

}