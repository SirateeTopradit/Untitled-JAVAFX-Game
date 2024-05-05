package entity;

import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

import static java.lang.Math.round;

/**
 * DarkKnight class extends the Entity class.
 * This class represents a DarkKnight entity in the game.
 */
public class DarkKnight extends Entity {

    /**
     * Constructor for the DarkKnight class.
     * Initializes the DarkKnight entity with the given parameters.
     *
     * @param gp  the game panel instance
     * @param hp  the health points of the DarkKnight entity
     * @param spd the speed of the DarkKnight entity
     * @param atk the attack power of the DarkKnight entity
     */
    public DarkKnight(GamePanel gp, float hp, float spd, float atk) {
        super(gp);
        setSpeed(round(spd * 4));
        setAtk(round(atk * 25));
        setHp(round(hp * 300));
        setPoints(round(hp + spd + atk));

        // Load the images for the DarkKnight entity
        loadFrames();
    }
    public void loadFrames() {
        try {
            for (int i = 0; i < getNUM_FRAMES(); i++) {
                InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("DarkKnight/DarkKnight%02d.png",i));
                if (resource != null) {
                    getFrames()[0][i] = new Image(resource);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}