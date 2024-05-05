package entity;

import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

import static java.lang.Math.round;

/**
 * Bone class extends the Entity class.
 * This class represents a Bone entity in the game.
 */
public class Bone extends Entity {

    /**
     * Constructor for the Bone class.
     * Initializes the Bone entity with the given parameters.
     *
     * @param gp  the game panel instance
     * @param hp  the health points of the Bone entity
     * @param spd the speed of the Bone entity
     * @param atk the attack power of the Bone entity
     */
    public Bone(GamePanel gp, float hp, float spd, float atk) {
        super(gp);
        setSpeed(round(spd * 8));
        setAtk(round(atk * 20));
        setHp(round(hp * 200));
        setPoints(round(hp + spd + atk));

        // Load the images for the Bone entity
        loadFrames();
    }
    public void loadFrames() {
        try {
            for (int i = 0; i < getNUM_FRAMES(); i++) {
                InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Bone/Bone_walk%02d.png", i));
                if (resource != null) {
                    getFrames()[0][i] = new Image(resource);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}