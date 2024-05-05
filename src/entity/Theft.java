package entity;

import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

import static java.lang.Math.round;

/**
 * Theft class extends the Entity class.
 * This class represents a Theft entity in the game.
 */
public class Theft extends Entity {

    /**
     * Constructor for the Theft class.
     * Initializes the Theft entity with the given parameters.
     *
     * @param gp  the game panel instance
     * @param hp  the health points of the Theft entity
     * @param spd the speed of the Theft entity
     * @param atk the attack power of the Theft entity
     */
    public Theft(GamePanel gp, float hp, float spd, float atk) {
        super(gp);
        setSpeed(round(spd * 6));
        setAtk(round(atk * 15));
        setHp(round(hp * 400));
        setPoints(round(hp + spd + atk));

        // Load the images for the Theft entity
        loadFrames();
    }
    public void loadFrames() {
        try {
            for (int i = 0; i < getNUM_FRAMES(); i++) {
                InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Theft/Theft%02d.png", i));
                if (resource != null) {
                    getFrames()[0][i] = new Image(resource);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}