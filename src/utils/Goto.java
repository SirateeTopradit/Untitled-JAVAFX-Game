package utils;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Goto {
    private static RootPane rootPane;

    public static void setPane(RootPane pane){
        Goto.rootPane = pane;
    }

    public static void MainMenuPage(){
        clear();
        rootPane.getChildren().add(new MainMenu());
    }

    public static void test(){
        clear();
        rootPane.getChildren().add(new GameMap());
    }

    public static void clear(){
        rootPane.getChildren().clear();
    }
}
