package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * UI class is responsible for handling the user interface in the game.
 * This includes drawing the score, player health, and stage information on the game panel.
 */
public class UI {
    private GamePanel gp;
    private Font Courier_New_40 = Font.font("Courier New", FontWeight.BOLD, 40);
    private Font Courier_New_80 = Font.font("Courier New", FontWeight.BOLD, 80);
    private int frameCounter = 0;

    /**
     * Constructor for the UI class.
     * Initializes the UI with the given game panel.
     *
     * @param gp  the game panel instance
     */
    public UI(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Draws the score, player health, and stage information on the game panel.
     *
     * @param gc  the graphics context to draw on
     */
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

    /**
     * Shows the current stage on the game panel.
     *
     * @param gc  the graphics context to draw on
     * @param stage  the current stage
     */
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

    /**
     * Draws the debug mode information on the game panel.
     *
     * @param gc  the graphics context to draw on
     * @param fps  the current frames per second
     */
    public void drawDebugMode(GraphicsContext gc, int fps) {
        gc.setFill(Color.WHITE);
        gc.fillText("FPS: " + fps, 10, 80);
        gc.fillText("WorldX: " + gp.getPlayer().getWorldX(), 10, 120);
        gc.fillText("WorldY: " + gp.getPlayer().getWorldY(), 10, 160);
    }
}