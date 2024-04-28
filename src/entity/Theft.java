package entity;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

import static java.lang.Math.round;

public class Theft extends Entity {
    public Theft(GamePanel gp, float hp, float spd, float atk) {
        super(gp);
        setSpeed(round(spd * 4));
        setAtk(round(atk * 20));
        setHp(round(hp * 600));
        setPoints(round(hp + spd + atk));
        setHitBox(new Rectangle(4*20, 5*20, 2*20, 4*20));
        setHitBoxWalk(new Rectangle(3*20, 5*20, 4*20, 4*20));
        try {
            for (int j = 0; j < 1; j++) {
                int direction = j;
                for (int i = 0; i < getNUM_FRAMES(); i++) {
                    InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Theft/Theft_walk%02d.png", i));
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