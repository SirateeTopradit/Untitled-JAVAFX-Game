package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.GamePanel;
import utils.Goto;

public class RootPane extends StackPane {
    private static RootPane instance;

    public RootPane() {
        this.setWidth(GamePanel.getInstance().getScreenWidth());
        this.setHeight(GamePanel.getInstance().getScreenHeight());
        Goto.setPane(this);
        Goto.titleScreen();

    }

    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}