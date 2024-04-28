package entity;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

import static java.lang.Math.round;

public class Stone extends Entity {
    public Stone(GamePanel gp, float hp, float spd, float atk) {
        super(gp);
        setSpeed(round(spd * 4));
        setAtk(round(atk * 30));
        setHp(round(hp * 300));
        setPoints(round(hp + spd + atk));
        try {
            for (int j = 0; j < 1; j++) {
                int direction = j;
                for (int i = 0; i < getNUM_FRAMES(); i++) {
                    InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Stone/Stone%02d.png", i));
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