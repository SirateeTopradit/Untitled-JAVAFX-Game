package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

import java.io.InputStream;

public class Zomby extends Entity{
    public Zomby(GamePanel gp) {
        super(gp);
        setSpeed(5);

        setHitBox(new Rectangle(4*20, 5*20, 2*20, 4*20));
        setHitBoxWalk(new Rectangle(3*20, 5*20, 4*20, 4*20));
        try {
            for (int j = 0; j < 1; j++) {
                int direction = j;
                for (int i = 0; i < getNUM_FRAMES(); i++) {
                    InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Zomby/Zomby_walking%d_/Zomby_walking%d_%02d.png", direction, direction, i));
                    if (resource != null) {
                        getFrames()[j][i] = new Image(resource);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
