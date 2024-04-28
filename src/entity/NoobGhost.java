package entity;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

import static java.lang.Math.round;

public class NoobGhost extends Entity {
    public NoobGhost(GamePanel gp, float hp, float spd, float atk) {
        super(gp);
        setSpeed(round(spd * 6));
        setAtk(round(atk * 25));
        setHp(round(hp * 150));
        setPoints(round(hp + spd + atk));
        try {
            for (int j = 0; j < 1; j++) {
                int direction = j;
                for (int i = 0; i < getNUM_FRAMES(); i++) {
                    InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("NoobGhost/NoobGhost%02d.png",i));
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