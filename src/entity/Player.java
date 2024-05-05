package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.GamePanel;
import main.KeyHandler;
import javafx.scene.shape.Rectangle;
import java.io.InputStream;
/**
 * Player class represents a player entity in the game.
 * This class extends the Entity class and adds player-specific functionality.
 */
public class Player extends Entity{
    private KeyHandler keyH;
    private boolean isStopped = true;
    private int speed;
    private final int NUM_FRAMES = 15;
    private final int NUM_DIRECTIONS = 4;
    private final Image[][] frames;
    private final long startTime;
    private final int screenX;
    private final int screenY;
    /**
     * Constructor for the Player class.
     * Initializes the Player with the given game panel and key handler.
     *
     * @param gp  the game panel instance
     * @param keyH  the key handler instance
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        setDefaultValues();
        this.screenX = ((gp.getScreenWidth()/2)-60-40);
        this.screenY = ((gp.getScreenHeight()/2)-110-40);
        frames = new Image[NUM_DIRECTIONS][NUM_FRAMES*2];
        startTime = System.currentTimeMillis();
        setHitBoxWalk(new Rectangle(3*20, 5*20, 4*20, 4*20));
        loadFrames();
        loadStandFrame();
    }
    /**
     * Loads the frames for the player's movement.
     */
    public void loadFrames() {
        try {
            for (int j = 0; j < NUM_DIRECTIONS; j++) {
                char direction = getDirectionFromIndex(j);
                for (int i = 0; i < NUM_FRAMES; i++) {
                    InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Dekhere/Dekhere_%c/Dekhere_%c%02d.png", direction, direction, i));
                    if (resource != null) {
                        frames[j][i] = new Image(resource);
                    } else {
                        System.out.println("Resource not found: " + String.format("Dekhere/Dekhere_%c/Dekhere_%c%02d.png", direction, direction, i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Loads the frames for the player's standing position.
     */
    public void loadStandFrame(){
        try {
            for (int j = 0; j < NUM_DIRECTIONS; j++) {
                char direction = getDirectionFromIndex(j);
                for (int i = 0; i < NUM_FRAMES; i++) {
                    InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Dekhere/Dekhere_%c_stand/Dekhere_%c_stand%02d.png", direction, direction, i));
                    if (resource != null) {
                        frames[j][i+NUM_FRAMES-1] = new Image(resource);
                    } else {
                        System.out.println("Resource not found: " + String.format("Dekhere/Dekhere_%c_stand/Dekhere_%c_stand%02d.png", direction, direction, i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Sets the default values for the player.
     */
    public void setDefaultValues() {
        setWorldX((getGp().getWorldScreenWidth() / 2)-60-40);
        setWorldY((getGp().getWorldScreenHeight() / 2)-100-40);
        setSpeed(10);
        setHp(2500);
        setDirection(0); // start facing 'a' direction
    }
    /**
     * Handles the key inputs for the player's movement.
     *
     * @param dx  the delta x
     * @param dy  the delta y
     */
    public void keyHan(double dx, double dy) {
        if (keyH.isUpPressed()) {
            dy -= getSpeed();
            setDirection(3);// 'w' direction
        }
        if (keyH.isDownPressed()) {
            dy += getSpeed();
            setDirection(2);// 's' direction
        }
        if (keyH.isLeftPressed()) {
            dx -= getSpeed();
            setDirection(0);// 'a' direction
        }
        if (keyH.isRightPressed()) {
            dx += getSpeed();
            setDirection(1);// 'd' direction
        }
        if (dx != 0 && dy != 0) {
            double factor = Math.sqrt(2) / 2;
            dx *= factor;
            dy *= factor;
        }
        setWorldX((int) (getWorldX() + dx));
        setWorldY((int) (getWorldY() + dy));
        isStopped = false;
    }
    /**
     * Updates the player's state.
     */
    @Override
    public void update() {
        double dx = 0;
        double dy = 0;
        keyHan(dx, dy);
        if (!keyH.isDownPressed() && !keyH.isUpPressed() && !keyH.isLeftPressed() && !keyH.isRightPressed()) {
            isStopped = true;
        }
        getGp().getCollisionChecker().checkCollision();
    }
    /**
     * Draws the player on the game panel.
     *
     * @param gc  the graphics context to draw on
     */
    @Override
    public void draw(GraphicsContext gc) {
        if(isAttacked()) {
            setCounterIsAttacked(getCounterIsAttacked() + 1);
            if(getCounterIsAttacked() >= 10) {
                setAttacked(false);
                setCounterIsAttacked(0);
            }
        }
        healthBar(gc);
    }
    /**
     * Draws the health bar for the player.
     *
     * @param gc  the graphics context to draw on
     */
    public void healthBar(GraphicsContext gc){
        int playerSize = (getGp().getTileSize() * getGp().getTileSize())/2;
        double healthBarWidth = 100.0;
        double healthBarHeight = 10.0;
        double healthBarX = getScreenX() + 50;
        double healthBarY = getScreenY() + 60;
        double currentHealthBarWidth = (getHp() / 2500.0) * healthBarWidth;
        gc.setFill(Color.GRAY);
        gc.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);
        gc.setFill(Color.DARKGREEN);
        gc.fillRect(healthBarX, healthBarY, currentHealthBarWidth, healthBarHeight);
        Image fxImage = getCurrentFrame(getDirection());
        gc.drawImage(fxImage, getScreenX(), getScreenY(), playerSize,playerSize);
    }
    /**
     * Returns the current frame of the player.
     *
     * @param direction  the direction of the player
     * @return the current frame
     */
    public Image getCurrentFrame(int direction) {
        int index = (int) ((System.currentTimeMillis() - startTime) / 50) % NUM_FRAMES;
        return isStopped ? frames[direction][index+NUM_FRAMES-1]:frames[direction][index];
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public Image[][] getFrames() {
        return frames;
    }

}