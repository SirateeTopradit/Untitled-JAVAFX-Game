package pane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Text;
import main.BackgroundSound;
import main.GamePanel;
import utils.Goto;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * TitleScreen class extends the StackPane class and represents the title screen of the game.
 * It includes methods for setting up the title screen, playing music, and transitioning to the game panel.
 */
public class TitleScreen extends StackPane {
    BackgroundSound backgroundSound = new BackgroundSound();
    private Slider volumeSlider;
    private ImageView backgroundImageView;

    /**
     * Constructor for the TitleScreen class.
     * Initializes the TitleScreen with the game panel dimensions, background color, music, background image, and start button.
     */
    public TitleScreen() {
        this.setWidth(GamePanel.getInstance().getScreenWidth());
        this.setHeight(GamePanel.getInstance().getScreenHeight());
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        playMusic(1);
        setBackground();
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            this.getChildren().remove(backgroundImageView);
            this.getChildren().remove(startButton);
            howToPlay();
        });
        StackPane.setAlignment(startButton, Pos.CENTER);
        startButton.setTranslateY(99);
        volumeBar();
        volumeSFXBar();
        this.getChildren().add(startButton);
    }

    /**
     * Transitions to the game panel.
     */
    public void howToPlay(){
        Image newImage = new Image("/Image/Artboard_2howToPlay.png");
        ImageView newImageView = new ImageView(newImage);
        newImageView.setFitWidth(GamePanel.getInstance().getScreenWidth());
        newImageView.setFitHeight(GamePanel.getInstance().getScreenHeight());
        this.getChildren().add(newImageView);
        Button readyButton = new Button("Ready To Play");
        readyButton.setOnAction(event -> {
            stopMusic();
            System.out.println("Game Started");
            Goto.gamePanel();
        });
        StackPane.setAlignment(readyButton, Pos.CENTER);
        readyButton.setTranslateY(333);
        this.getChildren().add(readyButton);
    }

    /**
     * Sets the background image for the title screen.
     */
    public void setBackground(){
        Image backgroundImage = new Image("/Image/Artboard_1title.png");
        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(GamePanel.getInstance().getScreenWidth());
        backgroundImageView.setFitHeight(GamePanel.getInstance().getScreenHeight());
        this.getChildren().add(backgroundImageView);
    }

    /**
     * Plays the background music for the title screen.
     *
     * @param MusicIndex  the index of the music to play
     */
    public void playMusic(int MusicIndex) {
        backgroundSound.setFile(MusicIndex);
        backgroundSound.play();
        backgroundSound.loop();
    }

    /**
     * Stops the background music for the title screen.
     */
    public void stopMusic() {
        backgroundSound.stop();
    }

    /**
     * Sets up the volume control bar for the title screen.
     */
    public void volumeBar() {
        HBox hbox = new HBox();
        volumeSlider = new Slider(0, 1, 0.25);
        volumeSlider.setPrefWidth(200);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            backgroundSound.setToVolume(newValue.doubleValue()/2.5);
        });
        Text whiteText = new Text("volume");
        whiteText.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        whiteText.setFill(Color.WHITE);
        hbox.getChildren().add(whiteText);
        hbox.getChildren().add(volumeSlider);
        hbox.setTranslateY(20);
        this.getChildren().add(hbox);
    }

    /**
     * Sets up the SFX volume control bar for the title screen.
     */
    public void volumeSFXBar() {
        HBox hbox = new HBox();
        volumeSlider = new Slider(0, 1, 0.25);
        volumeSlider.setPrefWidth(200);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            backgroundSound.setToSFXVolume(newValue.doubleValue()/2.5);
        });
        Text whiteText = new Text("SFX volume");
        whiteText.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        whiteText.setFill(Color.WHITE);
        hbox.getChildren().add(whiteText);
        hbox.getChildren().add(volumeSlider);
        hbox.setTranslateY(50);
        this.getChildren().add(hbox);
    }
}