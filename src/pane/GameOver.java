package pane;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import main.BackgroundSound;
import main.GamePanel;

public class GameOver extends VBox {
    Font Courier_New_80 = Font.font("Courier New", FontWeight.BOLD, 80);
    BackgroundSound soundEffect = new BackgroundSound();
    public GameOver(long score, int killCount) {
        playSFX(3);
        this.setPrefWidth(GamePanel.getInstance().getScreenWidth());
        this.setPrefHeight(GamePanel.getInstance().getScreenHeight());
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setFont(Courier_New_80);
        gameOverLabel.setTextFill(Color.WHITE);

        Label scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(Courier_New_80);
        scoreLabel.setTextFill(Color.WHITE);

        Label killCountLabel = new Label("Kill Count: " + killCount);
        killCountLabel.setFont(Courier_New_80);
        killCountLabel.setTextFill(Color.WHITE);

        Button exitGameButton = new Button("Exit Game");
        exitGameButton.setOnAction(event -> Platform.exit());

        this.getChildren().addAll(gameOverLabel, scoreLabel, killCountLabel, exitGameButton);
        this.setAlignment(Pos.CENTER);

    }
    public void playSFX(int SFXIndex) {
        soundEffect.setFile(SFXIndex);
        soundEffect.play();
    }
}