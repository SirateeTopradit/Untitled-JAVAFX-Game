package main;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UI {
    GamePanel gp;
    Font Courier_New_40 = Font.font("Courier New", FontWeight.BOLD, 40);
    Font Courier_New_80 = Font.font("Courier New", FontWeight.BOLD, 80);
    private int frameCounter = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
    }
    public void draw(GraphicsContext gc) {
        gc.setFont(Courier_New_40);
        gc.setFill(Color.DARKRED);
        gc.fillText("Score:"+gp.getScore() , 10 , 40);
        gc.fillText("HP:"+gp.getPlayer().getHp() , 10 , 80);
        if (frameCounter <= 60 && gp.getNewStage()) {
            showStage(gc, gp.getStage());
            frameCounter++;
        } else if (frameCounter > 60) {
            frameCounter = 0;
            gp.setNewStage(false);
        }
    }
    public void showStage(GraphicsContext gc, int stage) {
        gc.setFont(Courier_New_80);
        Text stageText = new Text("Stage " + stage);
        stageText.setFont(Courier_New_80);
        double textWidth = stageText.getBoundsInLocal().getWidth();
        double x = (gp.getScreenWidth() - textWidth) / 2;
        double y = 60;
        gc.fillText("Stage " + stage, x, y);
        if (stage == 10 || stage == 20) {
            stageText = new Text("Skill Level UP!");
            stageText.setFont(Courier_New_80);
            textWidth = stageText.getBoundsInLocal().getWidth();
            x = (gp.getScreenWidth() - textWidth) / 2;
            gc.fillText("Skill Level UP!", x, y + 80);
        }
    }
    public void drawDebugMode(GraphicsContext gc, int fps) {
        gc.setFill(Color.WHITE);
        gc.fillText("FPS: " + fps, 10, 80);
        gc.fillText("WorldX: " + gp.player.getWorldX(), 10, 120);
        gc.fillText("WorldY: " + gp.player.getWorldY(), 10, 160);
    }


}
