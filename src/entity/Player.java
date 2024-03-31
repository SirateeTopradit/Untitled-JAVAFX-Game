package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.GamePanel;
import main.KeyHandler;

import java.io.InputStream;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    int direction;
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
        this.screenX = ((gp.getScreenWidth()/2)-60-40);
        this.screenY = ((gp.getScreenHeight()/2)-110-30);

        frames = new Image[NUM_DIRECTIONS][NUM_FRAMES];
        startTime = System.currentTimeMillis();


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

    public void setDefaultValues() {
        setWorldX((gp.getWorldScreenWidth() / 2)-60-40);
        setWorldY((gp.getWorldScreenHeight() / 2)-110-30);
        setSpeed(4);
        direction = 0; // start facing 'a' direction
    }

    public void update() {



        if (keyH.isUpPressed()) {
            setWorldY(getWorldY() - getSpeed());
            direction = 3; // 'w' direction
        }
        if (keyH.isDownPressed()) {
            setWorldY(getWorldY() + getSpeed());
            direction = 2; // 's' direction
        }
        if (keyH.isLeftPressed()) {
            setWorldX(getWorldX() - getSpeed());
            direction = 0; // 'a' direction
        }
        if (keyH.isRightPressed()) {
            setWorldX(getWorldX() + getSpeed());
            direction = 1; // 'd' direction
        }
    }

    public void draw(GraphicsContext gc) {
        int playerSize = (gp.getTileSize() * gp.getTileSize())/2;

//        DropShadow ds = new DropShadow();
//        ds.setOffgetWorldY(3.0);
//        ds.setOffsetWorldX(3.0);
//        ds.setColor(Color.GRAY);
//        gc.setEffect(ds);
//        gc.setFill(Color.YELLOW);
//        gc.fillRect(getX(), getY(), playerSize, playerSize);

//        gc.setFill(Color.RED);
//        gc.fillRect(getX(), getY(), playerSize-100, playerSize-100);

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
        return frames[direction][index];
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