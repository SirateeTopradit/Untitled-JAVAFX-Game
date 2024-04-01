package main;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UI {
    GamePanel gp;
    Font Courier_New_40 = new Font("Courier New", 40);

    public UI(GamePanel gp) {
        this.gp = gp;
    }
    public void draw(GraphicsContext gc) {
        gc.setFont(Courier_New_40);
        gc.setFill(Color.WHITE);
        gc.fillText("Score:"+gp.player.getWorldY(), 10 , 40);
    }

}
