package weapon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import main.BackgroundSound;
import main.GamePanel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Weapon class is an abstract class that implements the BaseWeapon interface.
 * It provides the basic structure and functionalities for a weapon in the game.
 * This includes the weapon's hitbox, position, availability, timer, sound effect, and attack power.
 */
public abstract class Weapon implements BaseWeapon{
    GamePanel gp;
    private Rectangle hitBox;
    private int screenX;
    private int screenY;
    private int worldX;
    private int worldY;
    private boolean isAvailable;
    private Timer timer;
    private int NUM_FRAMES = 5;
    private long startTime;
    private BackgroundSound soundEffect = new BackgroundSound();
    private int atk;

    /**
     * Constructor for the Weapon class.
     * Initializes the Weapon with the given game panel.
     *
     * @param gp  the game panel instance
     */
    public Weapon(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Returns the level of the weapon based on the current stage of the game.
     *
     * @return the level of the weapon
     */
    public int getLevel(){
        if(gp.getStage() < 10){
            return 1;
        } else if (gp.getStage() < 20) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Plays the sound effect for the weapon.
     *
     * @param SFXIndex  the index of the sound effect to play
     */
    public void playSFX(int SFXIndex) {
        soundEffect.setFile(SFXIndex);
        soundEffect.play();
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    public int getNUM_FRAMES() {
        return NUM_FRAMES;
    }

    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long l) {
        startTime = l;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void updateInterval() {
    }
}

