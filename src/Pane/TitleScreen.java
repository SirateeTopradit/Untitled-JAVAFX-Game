package Pane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import main.BackgroundSound;
import main.GamePanel;
import utils.Goto;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TitleScreen extends StackPane {
    private GamePanel gp = GamePanel.getInstance();
    BackgroundSound backgroundSound = new BackgroundSound();
    private final Font Courier_New_40 = new Font("Courier New", 40);
    private final Font Courier_New_96_Bold = Font.font("Courier New", FontWeight.BOLD, 96);

    public TitleScreen() {
        this.setWidth(GamePanel.getInstance().getScreenWidth());
        this.setHeight(GamePanel.getInstance().getScreenHeight());
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        playMusic(1);

        Image backgroundImage = new Image("/Image/Artboard_1title.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(GamePanel.getInstance().getScreenWidth());
        backgroundImageView.setFitHeight(GamePanel.getInstance().getScreenHeight());
        this.getChildren().add(backgroundImageView);

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            stopMusic();
            System.out.println("Game Started");
            Goto.gamePanel();
        });
        StackPane.setAlignment(startButton, Pos.CENTER);
        startButton.setTranslateY(100);
        this.getChildren().add(startButton);
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
