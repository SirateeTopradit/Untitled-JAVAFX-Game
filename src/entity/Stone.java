package entity;

import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

import static java.lang.Math.round;

/**
 * Stone class extends the Entity class.
 * This class represents a Stone entity in the game.
 */
public class Stone extends Entity {

    /**
     * Constructor for the Stone class.
     * Initializes the Stone entity with the given parameters.
     *
     * @param gp  the game panel instance
     * @param hp  the health points of the Stone entity
     * @param spd the speed of the Stone entity
     * @param atk the attack power of the Stone entity
     */
    public Stone(GamePanel gp, float hp, float spd, float atk) {
        super(gp);
        setSpeed(round(spd * 8));
        setAtk(round(atk * 15));
        setHp(round(hp * 300));
        setPoints(round(hp + spd + atk));

        // Load the images for the Stone entity
        loadFrames();
    }
    public void loadFrames() {
        try {
            for (int i = 0; i < getNUM_FRAMES(); i++) {
                InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Stone/Stone%02d.png", i));
                if (resource != null) {
                    getFrames()[0][i] = new Image(resource);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}