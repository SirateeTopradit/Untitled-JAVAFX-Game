package entity;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

import static java.lang.Math.round;

/**
 * NoobGhost class extends the Entity class.
 * This class represents a NoobGhost entity in the game.
 */
public class NoobGhost extends Entity {

    /**
     * Constructor for the NoobGhost class.
     * Initializes the NoobGhost entity with the given parameters.
     *
     * @param gp  the game panel instance
     * @param hp  the health points of the NoobGhost entity
     * @param spd the speed of the NoobGhost entity
     * @param atk the attack power of the NoobGhost entity
     */
    public NoobGhost(GamePanel gp, float hp, float spd, float atk) {
        super(gp);
        setSpeed(round(spd * 6));
        setAtk(round(atk * 12));
        setHp(round(hp * 160));
        setPoints(round(hp + spd + atk));

        // Load the images for the NoobGhost entity
        loadFrames();
    }
    public void loadFrames() {
        try {
            for (int i = 0; i < getNUM_FRAMES(); i++) {
                InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("NoobGhost/NoobGhost%02d.png",i));
                if (resource != null) {
                    getFrames()[0][i] = new Image(resource);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}