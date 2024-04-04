package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.GamePanel;
import main.KeyHandler;
import javafx.scene.shape.Rectangle;
import java.io.InputStream;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    int direction;
    boolean isStopped = true;
    private int speed;
    private static final int NUM_FRAMES = 15;
    private static final int NUM_DIRECTIONS = 4; // number of directions
    private final Image[][] frames;
    private final long startTime;
    private final int screenX;
    private final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        this.screenX = ((gp.getScreenWidth()/2)-60);
        this.screenY = ((gp.getScreenHeight()/2)-110);
        frames = new Image[NUM_DIRECTIONS][NUM_FRAMES*2];
        startTime = System.currentTimeMillis();

        setHitBox(new Rectangle(4*20, 5*20, 2*20, 4*20));
        setHitBoxWalk(new Rectangle(3*20, 5*20, 4*20, 4*20));

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

    public void setDefaultValues() {
        setWorldX((gp.getWorldScreenWidth() / 2)-60-40);
        setWorldY((gp.getWorldScreenHeight() / 2)-100-40);
        setSpeed(10);
        direction = 0; // start facing 'a' direction
    }

    public void update() {
        if (keyH.isUpPressed()) {
            setWorldY(getWorldY() - getSpeed());
            direction = 3; // 'w' direction
            isStopped = false;
        }
        if (keyH.isDownPressed()) {
            setWorldY(getWorldY() + getSpeed());
            direction = 2; // 's' direction
            isStopped = false;
        }
        if (keyH.isLeftPressed()) {
            setWorldX(getWorldX() - getSpeed());
            direction = 0; // 'a' direction
            isStopped = false;
        }
        if (keyH.isRightPressed()) {
            setWorldX(getWorldX() + getSpeed());
            direction = 1; // 'd' direction
            isStopped = false;
        }
        if (!keyH.isDownPressed()&&!keyH.isUpPressed()&&!keyH.isLeftPressed()&&!keyH.isRightPressed()) {
            isStopped = true;
        }
        gp.getCollisionChecker().checkCollision();
    }

    public void draw(GraphicsContext gc) {
        int playerSize = (gp.getTileSize() * gp.getTileSize())/2;
        if(gp.getDebugMode()) {
            gc.setFill(Color.BLUE);
            gc.fillRect(getScreenX() + getHitBoxWalk().getX(), getScreenY() + getHitBoxWalk().getY(), getHitBoxWalk().getWidth(), getHitBoxWalk().getHeight());

            gc.setFill(Color.RED);
            gc.fillRect(getScreenX() + getHitBox().getX(), getScreenY() + getHitBox().getY(), getHitBox().getWidth(), getHitBox().getHeight());
        }

        Image fxImage = getCurrentFrame(direction);
        gc.drawImage(fxImage, getScreenX(), getScreenY(), playerSize,playerSize);
    }
    private char getDirectionFromIndex(int index) {
        return switch (index) {
            case 0 -> 'a';
            case 1 -> 'd';
            case 2 -> 's';
            default -> 'w';
        };
    }

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
}