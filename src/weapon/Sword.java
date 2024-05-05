package weapon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Sword class extends the Weapon class and represents a sword weapon in the game.
 * It includes methods for updating the sword state, drawing the sword, and starting a timer for the sword.
 */
public class Sword extends Weapon{
    private Image[] frames = new Image[getNUM_FRAMES()];

    /**
     * Constructor for the Sword class.
     * Initializes the Sword with the given game panel, sets up the hitbox, position, availability, timer, and loads the sword images.
     *
     * @param gp  the game panel instance
     */
    public Sword(GamePanel gp) {
        super(gp);
        setHitBox(new Rectangle(0, 0, 200, 200));
        setScreenX((gp.getScreenWidth()/2)-60-40);
        setScreenY((gp.getScreenHeight()/2)-110);
        setAvailable(false);
        startTimer();
        setStartTime(System.currentTimeMillis());
        try {
            for (int i = 0; i < getNUM_FRAMES(); i++) {
                InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("hit/hit%02d.png", i));
                if (resource != null) {
                    frames[i] = new Image(resource);
                }
                else System.out.println("nooooo " + String.format("hit/hit%2d.png", i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current frame of the sword based on the time since the sword was created.
     *
     * @return the current frame of the sword
     */
    public Image getCurrentFrame() {
        int index = (int) ((System.currentTimeMillis() - getStartTime()) / 50) % getNUM_FRAMES();
        return frames[index];
    }

    /**
     * Updates the state of the sword.
     * This includes setting the attack power, position, and hitbox size.
     */
    @Override
    public void update() {
        setAtk(100*getLevel());
        setWorldX(gp.getPlayer().getWorldX());
        setWorldY(gp.getPlayer().getWorldY());
        if (isAvailable()) {
            setHitBox(new Rectangle(0, 0, 200 + (getLevel()-1)*100, 200 + (getLevel()-1)*100));
        } else {
            setHitBox(new Rectangle(0, 0, 0, 0));
        }
    }

    /**
     * Draws the sword on the game panel.
     *
     * @param gc  the graphics context to draw on
     */
    @Override
    public void draw(GraphicsContext gc) {
        if (isAvailable()) {
            int size = 200 + (getLevel()-1)*100;
            gc.drawImage(getCurrentFrame(), getScreenX()-(getLevel()-1)*50, getScreenY()-(getLevel()-1)*50,size, size);
        }
    }

    /**
     * Starts a timer for the sword.
     * The timer toggles the availability of the sword every 3 seconds and plays a sound effect.
     */
    public void startTimer() {
        setTimer(new Timer());
        getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                playSFX(7);
                setAvailable(!isAvailable());
            }
        }, 0, 3000);
    }
}