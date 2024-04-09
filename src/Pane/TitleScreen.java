package main;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;

public class TitleScreen extends Pane {
    private GamePanel gp;
    private Font Courier_New_40 = new Font("Courier New", 40);
    private Font Courier_New_96_Bold = Font.font("Courier New", FontWeight.BOLD, 96);

    public TitleScreen(GamePanel gp) {
        this.gp = gp;
    }

    public void draw() {
        this.getChildren().clear();
        Text title = new Text("Untitled-JAVAFX-Game");
        title.setFont(Courier_New_96_Bold);
        title.setFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
        title.setEffect(ds);
        this.getChildren().add(title);
        title.setLayoutX((double) gp.getScreenWidth() / 2 - title.getLayoutBounds().getWidth() / 2);
        title.setLayoutY(500);

        Button startButton = new Button("Start Game");
        startButton.setLayoutX((double) gp.getScreenWidth() / 2 - 50);
        startButton.setLayoutY(600);
        startButton.setOnAction(e -> {
            gp.stopMusic();
            gp.playMusic(0);
            gp.setGameState(gp.getPlayState());
            System.out.println("Game Started");
        });
        this.getChildren().add(startButton);
    }
}