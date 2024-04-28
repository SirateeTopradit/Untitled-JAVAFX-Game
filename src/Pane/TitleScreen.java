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
    private ImageView backgroundImageView; // สร้างตัวแปรเพื่อเก็บ ImageView

    public TitleScreen() {
        this.setWidth(GamePanel.getInstance().getScreenWidth());
        this.setHeight(GamePanel.getInstance().getScreenHeight());
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        playMusic(1);

        Image backgroundImage = new Image("/Image/Artboard_1title.png");
        backgroundImageView = new ImageView(backgroundImage); // กำหนดค่าให้ตัวแปร backgroundImageView
        backgroundImageView.setFitWidth(GamePanel.getInstance().getScreenWidth());
        backgroundImageView.setFitHeight(GamePanel.getInstance().getScreenHeight());
        this.getChildren().add(backgroundImageView);

        Button startButton = new Button("Start Game"); // สร้างปุ่มใหม่
        startButton.setOnAction(e -> {
            // ลบ ImageView และปุ่ม changeImageButton ออกจาก StackPane
            this.getChildren().remove(backgroundImageView);
            this.getChildren().remove(startButton);

            // สร้าง ImageView ใหม่และกำหนดรูปภาพใหม่
            Image newImage = new Image("/Image/Artboard_2howToPlay.png");
            ImageView newImageView = new ImageView(newImage);
            newImageView.setFitWidth(GamePanel.getInstance().getScreenWidth());
            newImageView.setFitHeight(GamePanel.getInstance().getScreenHeight());
            this.getChildren().add(newImageView);

            // เพิ่มปุ่ม startButton และกำหนดตำแหน่งใหม่
            Button readyButton = new Button("Ready To Play");
            readyButton.setOnAction(event -> {
                stopMusic();
                System.out.println("Game Started");
                Goto.gamePanel();
            });
            StackPane.setAlignment(readyButton, Pos.CENTER);
            readyButton.setTranslateY(333);
            this.getChildren().add(readyButton);
        });
        StackPane.setAlignment(startButton, Pos.CENTER);
        startButton.setTranslateY(99);
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
