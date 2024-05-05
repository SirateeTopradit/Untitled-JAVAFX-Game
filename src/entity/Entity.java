package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GamePanel;
/**
 * Entity class represents a game entity.
 * This class is a base class for all game entities.
 */
public abstract class Entity {
    private int worldX, worldY;
    private GamePanel gp;
    private int direction;
    private int speed;
    private Rectangle hitBoxWalk;
    private final int NUM_FRAMES = 5;
    private final int NUM_DIRECTIONS = 4; // number of directions
    private Image[][] frames;
    private final long startTime;
    private boolean isAttacked;
    private int counterIsAttacked = 0;
    private int atk;
    private Boolean isColliding = false;
    private int hp;
    private int points;
    /**
     * Constructor for the Entity class.
     * Initializes the Entity with the given game panel.
     *
     * @param gp  the game panel instance
     */
    public Entity(GamePanel gp) {
        this.gp = gp;
        frames = new Image[NUM_DIRECTIONS][NUM_FRAMES*2];
        startTime = System.currentTimeMillis();
        setHitBoxWalk(new Rectangle(2*20, 4*20, 4*40, 4*30));
    }
    public abstract void loadFrames();
    /**
     * Draws the entity on the game panel.
     *
     * @param gc  the graphics context to draw on
     */
    public void draw(GraphicsContext gc) {
        int chunkSize = 400;
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
        if(     worldX + gp.getTileSize() + chunkSize > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() - chunkSize < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() + chunkSize > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() - chunkSize < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY() ){
            int EntitySize = (gp.getTileSize() * gp.getTileSize())/2;
            Image fxImage = getCurrentFrame(0);
            if (getDirection() == 0) {
                gc.save();
                gc.scale(-1, 1);
                gc.drawImage(fxImage, -screenX - EntitySize, screenY, EntitySize, EntitySize);
                gc.restore();
            } else {
                gc.drawImage(fxImage, screenX, screenY, EntitySize, EntitySize);
            }
        }
    }
    /**
     * Calculates the direction of the entity based on the given deltas.
     *
     * @param dx  the delta x
     * @param dy  the delta y
     */
    public void calDirection(int dx, int dy) {
        if (dx >= 5) setDirection(0);
        else setDirection(2);
    }
    /**
     * Moves the entity based on the given deltas.
     *
     * @param dx  the delta x
     * @param dy  the delta y
     */
    public void move(int dx, int dy){
        int nextX = getWorldX();
        int nextY = getWorldY();
        if (Math.abs(dx) >= Math.abs(dy)) {
            if (dx >= 0) nextX += getSpeed();
            else nextX -= getSpeed();
            if (dy >= 0) nextY += getSpeed() / 2;
            else nextY -= getSpeed() / 2;
        } else {
            if (dy >= 0) nextY += getSpeed();
            else nextY -= getSpeed();
            if (dx >= 0) nextX += getSpeed() / 2;
            else nextX -= getSpeed() / 2;
        }
        setWorldX(nextX);
        setWorldY(nextY);
    }
    /**
     * Damages the entity.
     */
    public void damage() {
        this.setHp(this.getHp() - gp.getWeapons()[0].getAtk());
        this.setAttacked(true);
        if (this.getHp() <= 0) {
            Entity[] monsters = gp.getMonster();
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] == this) {
                    monsters[i] = null;
                    break;
                }
            }
            gp.setMonster(monsters);
            gp.setScore(gp.getScore() + this.getPoints());
        }
    }
    /**
     * Updates the entity's state.
     */
    public void update() {
        int dx = gp.getPlayer().getWorldX() - getWorldX();
        int dy = gp.getPlayer().getWorldY() - getWorldY();
        calDirection(dx, dy);
        gp.getCollisionChecker().checkEntity(this, gp.getEntity());
        if (!isColliding) move(dx, dy);
        else damage();
        isColliding = false;
    }
    /**
     * Returns the direction character from the given index.
     *
     * @param index  the index
     * @return the direction character
     */
    public char getDirectionFromIndex(int index) {
        return switch (index) {
            case 0 -> 'a';
            case 1 -> 'd';
            case 2 -> 's';
            default -> 'w';
        };
    }
    /**
     * Returns the current frame of the entity.
     *
     * @param direction  the direction of the entity
     * @return the current frame
     */
    public Image getCurrentFrame(int direction) {
        int index = (int) ((System.currentTimeMillis() - startTime) / 50) % NUM_FRAMES;
        return frames[direction][index];
    }

    public int getNUM_FRAMES() {
        return NUM_FRAMES;
    }

    public Image[][] getFrames() {
        return frames;
    }

    public void setColliding(Boolean colliding) {
        isColliding = colliding;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    public void setAttacked(boolean attacked) {
        isAttacked = attacked;
    }

    public int getCounterIsAttacked() {
        return counterIsAttacked;
    }

    public void setCounterIsAttacked(int counterIsAttacked) {
        this.counterIsAttacked = counterIsAttacked;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAtk() {
        return this.atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }
    public Rectangle getHitBoxWalk() {
        return hitBoxWalk;
    }

    public void setHitBoxWalk(Rectangle hitBoxWalk) {
        this.hitBoxWalk = hitBoxWalk;
    }

    public void setWorldX(int x) {
        this.worldX = x;
    }

    public void setWorldY(int y) {
        this.worldY = y;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public GamePanel getGp() {
        return gp;
    }
}