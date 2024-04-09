package Pane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import main.BackgroundSound;
import main.GamePanel;
import utils.Goto;

public class TitleScreen extends VBox {
    private GamePanel gp = GamePanel.getInstance();
    BackgroundSound backgroundSound = new BackgroundSound();
    private final Font Courier_New_40 = new Font("Courier New", 40);
    private final Font Courier_New_96_Bold = Font.font("Courier New", FontWeight.BOLD, 96);

    public TitleScreen() {
        this.setWidth(GamePanel.getInstance().getScreenWidth());
        this.setHeight(GamePanel.getInstance().getScreenHeight());
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        playMusic(0);

        Text title = new Text("Dekhere");
        title.setFont(Courier_New_96_Bold);
        title.setFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
        title.setEffect(ds);
        this.getChildren().add(title);

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            stopMusic();
            System.out.println("Game Started");
            Goto.gamePanel();
        });
        this.getChildren().add(startButton);
        this.setAlignment(Pos.CENTER);
    }

    public void playMusic(int MusicIndex) {
        backgroundSound.setFile(MusicIndex);
        backgroundSound.play();
        backgroundSound.loop();
    }

    public void stopMusic() {
        backgroundSound.stop();
    }
}