package pane;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import main.BackgroundSound;
import main.GamePanel;

/**
 * GameOver class extends the VBox class and represents the game over screen of the game.
 * It includes methods for playing sound effects and initializing the game over screen.
 */
public class GameOver extends StackPane {
    Font Courier_New_48 = Font.font("Courier New", FontWeight.BOLD, 48);
    BackgroundSound soundEffect = new BackgroundSound();
    private ImageView backgroundImageView;

    /**
     * Constructor for the GameOver class.
     * Initializes the GameOver with the game panel dimensions, background color, game over label, score label, kill count label, and exit game button.
     *
     * @param score  the final score of the game
     * @param killCount  the final kill count of the game
     */
    public GameOver(long score, int killCount) {
        this.setAlignment(Pos.CENTER);
        playSFX(3);
        this.setPrefWidth(GamePanel.getInstance().getScreenWidth());
        this.setPrefHeight(GamePanel.getInstance().getScreenHeight());
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        setBackground();
        Label scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(Courier_New_48);
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setTranslateY(90);
        Label killCountLabel = new Label("Kill Count: " + killCount);
        killCountLabel.setFont(Courier_New_48);
        killCountLabel.setTextFill(Color.WHITE);
        Button exitGameButton = new Button("Exit Game");
        exitGameButton.setOnAction(event -> Platform.exit());
        exitGameButton.setTranslateY(150);
        this.getChildren().addAll(scoreLabel, killCountLabel, exitGameButton);
    }

    public void setBackground(){
        Image backgroundImage = new Image("/Image/Asset_2wda.png");
        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(GamePanel.getInstance().getScreenWidth());
        backgroundImageView.setFitHeight(GamePanel.getInstance().getScreenHeight());
        this.getChildren().add(backgroundImageView);
    }

    /**
     * Plays a sound effect.
     *
     * @param SFXIndex  the index of the sound effect to play
     */
    public void playSFX(int SFXIndex) {
        soundEffect.setFile(SFXIndex);
        soundEffect.play();
    }
}