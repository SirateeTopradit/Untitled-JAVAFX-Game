package entity;

import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

import static java.lang.Math.round;

/**
 * Zomby class extends the Entity class.
 * This class represents a Zomby entity in the game.
 */
public class Zomby extends Entity{

    /**
     * Constructor for the Zomby class.
     * Initializes the Zomby entity with the given parameters.
     *
     * @param gp  the game panel instance
     * @param hp  the health points of the Zomby entity
     * @param spd the speed of the Zomby entity
     * @param atk the attack power of the Zomby entity
     */
    public Zomby(GamePanel gp, float hp, float spd, float atk) {
        super(gp);
        setSpeed(round(spd * 4));
        setAtk(round(atk * 10));
        setHp(round(hp * 600));
        setPoints(round(hp + spd + atk));

        // Load the images for the Zomby entity
        loadFrames();
    }
    public void loadFrames() {
        try {
            for (int i = 0; i < getNUM_FRAMES(); i++) {
                InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Zomby/Zomby_walking0_/Zomby_walking0_%02d.png", i));
                if (resource != null) {
                    getFrames()[0][i] = new Image(resource);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}