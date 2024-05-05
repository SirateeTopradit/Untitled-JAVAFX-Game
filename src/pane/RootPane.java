package pane;

import javafx.scene.layout.StackPane;
import main.GamePanel;
import utils.Goto;

/**
 * RootPane class extends the StackPane class and represents the root pane of the game.
 * It includes methods for getting the instance of the root pane and initializing the root pane.
 */
public class RootPane extends StackPane {
    private static RootPane instance;

    /**
     * Constructor for the RootPane class.
     * Initializes the RootPane with the game panel dimensions and sets the initial pane to the title screen.
     */
    public RootPane() {
        this.setWidth(GamePanel.getInstance().getScreenWidth());
        this.setHeight(GamePanel.getInstance().getScreenHeight());
        Goto.setPane(this);
        Goto.titleScreen();
    }

    /**
     * Returns the instance of the RootPane.
     * If the instance is null, it creates a new RootPane.
     *
     * @return the instance of the RootPane
     */
    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}