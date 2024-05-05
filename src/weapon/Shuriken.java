package weapon;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Shuriken class extends the Weapon class and represents a shuriken weapon in the game.
 * It includes methods for updating the shuriken state, drawing the shuriken, and starting a timer for the shuriken.
 */
public class Shuriken extends Weapon {
    private Image[] frames;
    private int num;
    private double dx;
    private double dy;

    /**
     * Constructor for the Shuriken class.
     * Initializes the Shuriken with the given game panel, sets up the position, availability, timer, and loads the shuriken images.
     *
     * @param gp  the game panel instance
     */
    public Shuriken(GamePanel gp) {
        super(gp);
        setScreenX((gp.getScreenWidth() / 2) - 60 - 40);
        setScreenY((gp.getScreenHeight() / 2) - 110);
        setAvailable(false);
        startTimer();
        setStartTime(System.currentTimeMillis());
        frames = new Image[getNUM_FRAMES()];
        try {
            for (int i = 0; i < getNUM_FRAMES(); i++) {
                InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Kj/KongJuk%d.png",i));
                if (resource != null) {
                    getFrames()[i] = new Image(resource);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the state of the shuriken.
     * This includes setting the attack power and position.
     */
    @Override
    public void update() {
        setAtk(40*gp.getStage());
        setWorldX(gp.getPlayer().getWorldX());
        setWorldY(gp.getPlayer().getWorldY());
    }
    int countBulletFrame;
    /**
     * Draws the shuriken on the game panel.
     *
     * @param gc  the graphics context to draw on
     */
    @Override
    public void draw(GraphicsContext gc) {
        if (isAvailable()) {
            Image fxImage = getCurrentFrame();
            gc.drawImage(fxImage, gp.getPlayer().getScreenX()+80 + (dx/12) * countBulletFrame, gp.getPlayer().getScreenY()+100 + (dy/12) * countBulletFrame, 80, 80);
            countBulletFrame++;
        }
    }
    /**
     * Knocks back the monster.
     *
     * @param num  the index of the monster
     * @param knockBackDistance  the distance to knock back the monster
     */
    public void knockBack(int num,int knockBackDistance) {
        double magnitude = Math.sqrt(dx * dx + dy * dy);
        dx /= magnitude;
        dy /= magnitude;
        if (gp.getMonster()[num] != null) gp.getMonster()[num].setWorldX(gp.getMonster()[num].getWorldX() + (int) (dx * knockBackDistance));
        if (gp.getMonster()[num] != null) gp.getMonster()[num].setWorldY(gp.getMonster()[num].getWorldY() + (int) (dy * knockBackDistance));
    }
    /**
     * Checks the monster's health.
     *
     * @param num  the index of the monster
     */
    public void checkMonster(int num){
        if (gp.getMonster()[num] != null) {
            if (gp.getMonster()[num].getHp() <= 0) {
                gp.setScore(gp.getScore() + gp.getMonster()[num].getPoints());
                Entity[] monsters = gp.getMonster();
                for (int i = 0; i < monsters.length; i++) {
                    if (monsters[i] == gp.getMonster()[num]) {
                        monsters[i] = null;
                        break;
                    }
                }
                gp.setMonster(monsters);
            }
        }
    }
    /**
     * Starts a timer for the shuriken.
     * The timer checks the monster's health, knocks back the monster, plays a sound effect, targets a monster, toggles the availability of the shuriken, and resets the bullet frame count.
     */
    @Override
    public void startTimer() {
        setTimer(new Timer());
        int interval = 200;
        getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (gp.getMonster()[num] != null) {
                    if (gp.getMonster()[num] != null) gp.getMonster()[num].setAttacked(true);
                    if (gp.getMonster()[num] != null) gp.getMonster()[num].setHp(gp.getMonster()[num].getHp() - getAtk());
                    knockBack(num,40);
                    playSFX(6);
                    checkMonster(num);
                    targetMonster();
                    setAvailable(!isAvailable());
                    countBulletFrame = 0;
                }
            }
        }, 0, interval);
    }

    /**
     * Targets a monster.
     */
    public void targetMonster() {
        getNearestMonster();
        Entity monster = gp.getMonster()[num];
        if (monster == null) {
            return ;
        }
        getDistanceToMonsters(monster);
    }

    /**
     * Gets the distance to a monster.
     *
     * @param monster  the monster to get the distance to
     */
    public void getDistanceToMonsters(Entity monster) {
        dx = monster.getWorldX() - this.getWorldX();
        dy = monster.getWorldY() - this.getWorldY();
    }
    /**
     * Gets the distance to a monster.
     *
     * @param monster  the monster to get the distance to
     * @return the distance to the monster
     */
    public double getDistanceToMonster(Entity monster) {
        dx = monster.getWorldX() - this.getWorldX();
        dy = monster.getWorldY() - this.getWorldY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * Gets the nearest monster.
     */
    public void getNearestMonster() {
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < gp.getMonster().length; i++) {
            if (gp.getMonster()[i] == null) {
                continue;
            }
            double distance = getDistanceToMonster(gp.getMonster()[i]);
            if (distance < minDistance) {
                minDistance = distance;
                num = i;
            }
        }
    }
    /**
     * Updates the interval for the shuriken.
     */
    @Override
    public void updateInterval() {
        if (getTimer() != null) {
            getTimer().cancel();
            setTimer(null);
        }
        startTimer();
    }
    /**
     * Returns the current frame of the shuriken based on the time since the shuriken was created.
     *
     * @return the current frame of the shuriken
     */
    public Image getCurrentFrame() {
        int index = (int) ((System.currentTimeMillis() - getStartTime()) / 50) % getNUM_FRAMES();
        return frames[index];
    }
    /**
     * Returns the frames of the shuriken.
     *
     * @return the frames of the shuriken
     */
    public Image[] getFrames() {
        return frames;
    }
}