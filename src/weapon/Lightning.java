package weapon;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.GamePanel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Lightning class extends the Weapon class and represents a lightning weapon in the game.
 * It includes methods for updating the lightning state, drawing the lightning, and starting a timer for the lightning.
 */
public class Lightning extends Weapon{
    private int countFrame;
    private int num;
    private double dx;
    private double dy;

    /**
     * Constructor for the Lightning class.
     * Initializes the Lightning with the given game panel, sets up the position, availability, timer, and sets the start time.
     *
     * @param gp  the game panel instance
     */
    public Lightning(GamePanel gp) {
        super(gp);
        setAvailable(false);
        setScreenX((gp.getScreenWidth() / 2) - 60 - 40);
        setScreenY((gp.getScreenHeight() / 2) - 110);
        startTimer();
        setStartTime(System.currentTimeMillis());
    }

    /**
     * Updates the state of the lightning.
     * This includes setting the attack power and position.
     */
    @Override
    public void update() {
        setAtk((100*getLevel()));
        setWorldX(getGp().getPlayer().getWorldX());
        setWorldY(getGp().getPlayer().getWorldY());
    }

    /**
     * Draws the lightning on the game panel.
     *
     * @param gc  the graphics context to draw on
     */
    @Override
    public void draw(GraphicsContext gc) {
        if (isAvailable()&&countFrame<4) {
            gc.setFill(Color.LIGHTBLUE);
            gc.fillRect(getGp().getPlayer().getScreenX()+80+dx ,0, 20, getGp().getPlayer().getScreenY()+100+dy);
        }
        countFrame++;
    }

    /**
     * Checks the monster's health.
     * If the monster's health is less than or equal to 0, it removes the monster and adds the monster's points to the score.
     *
     * @param num  the index of the monster
     */
    public void checkMonster(int num) {
        if (getGp().getMonster()[num] != null) {
            if (getGp().getMonster()[num].getHp() <= 0) {
                if (getGp().getMonster()[num] != null)
                    getGp().setScore(getGp().getScore() + getGp().getMonster()[num].getPoints());
                Entity[] monsters = getGp().getMonster();
                for (int i = 0; i < monsters.length; i++) {
                    if (monsters[i] == getGp().getMonster()[num]) {
                        monsters[i] = null;
                        break;
                    }
                }
                getGp().setMonster(monsters);
            }
        }
    }

    /**
     * Starts a timer for the lightning.
     * The timer checks the monster's health, plays a sound effect, targets a monster, toggles the availability of the lightning, and resets the frame count.
     */
    public void startTimer() {
        setTimer(new Timer());
        int interval = 1800 / getLevel();
        getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (getGp().getMonster()[num] != null) {
                    playSFX(2);
                    if (getGp().getMonster()[num] != null) getGp().getMonster()[num].setHp(getGp().getMonster()[num].getHp() - getAtk());
                    if (getGp().getMonster()[num] != null) getGp().getMonster()[num].setAttacked(true);
                    checkMonster(num);
                    targetMonster();
                    setAvailable(!isAvailable());
                    countFrame = 0;
                }
            }
        }, 0, interval);
    }

    /**
     * Targets a monster.
     */
    public void targetMonster() {
        getNearestMonster();
        Entity monster = getGp().getMonster()[num];
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
        for (int i = 0; i < getGp().getMonster().length; i++) {
            if (getGp().getMonster()[i] == null) {
                continue;
            }
            double distance = getDistanceToMonster(getGp().getMonster()[i]);
            if (distance < minDistance) {
                minDistance = distance;
                num = i;
            }
        }
    }

    /**
     * Updates the interval for the lightning.
     */
    @Override
    public void updateInterval() {
        if (getTimer() != null) {
            getTimer().cancel();
            setTimer(null);
        }
        startTimer();
    }
}