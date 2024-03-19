package entity;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    int direction;

    private int x, y;
    private int speed;
    private static final int NUM_FRAMES = 15;
    private static final int NUM_DIRECTIONS = 4; // number of directions
    private BufferedImage[][] frames;
    private long startTime;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();

        frames = new BufferedImage[NUM_DIRECTIONS][NUM_FRAMES];
        startTime = System.currentTimeMillis();

        try {
            for (int j = 0; j < NUM_DIRECTIONS; j++) {
                char direction = getDirectionFromIndex(j);
                for (int i = 0; i < NUM_FRAMES; i++) {
                    InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Dekhere/Dekhere_%c/Dekhere_%c%02d.png", direction, direction, i));
                    if (resource != null) {
                        frames[j][i] = ImageIO.read(resource);
                    } else {
                        System.out.println("Resource not found: " + String.format("Dekhere/Dekhere_%c/Dekhere_%c%02d.png", direction, direction, i));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        setX(100);
        setY(100);
        setSpeed(4);
        direction = 0; // start facing 'a' direction
    }

    public void update() {
        if (keyH.isUpPressed()) {
            setY(getY() - getSpeed());
            direction = 3; // 'w' direction
        }
        if (keyH.isDownPressed()) {
            setY(getY() + getSpeed());
            direction = 2; // 's' direction
        }
        if (keyH.isLeftPressed()) {
            setX(getX() - getSpeed());
            direction = 0; // 'a' direction
        }
        if (keyH.isRightPressed()) {
            setX(getX() + getSpeed());
            direction = 1; // 'd' direction
        }
    }

    public void draw(GraphicsContext gc) {
        Image fxImage = SwingFXUtils.toFXImage(getCurrentFrame(direction), null);
        gc.drawImage(fxImage, getX(), getY(), gp.getTileSize()*5, gp.getTileSize()*5);
    }

    private char getDirectionFromIndex(int index) {
        switch (index) {
            case 0: return 'a';
            case 1: return 'd';
            case 2: return 's';
            default: return 'w';
        }
    }

    public BufferedImage getCurrentFrame(int direction) {
        int index = (int) ((System.currentTimeMillis() - startTime) / 50) % NUM_FRAMES;
//        if(!keyH.isKeyPressed()){
//            index = 0;
//        }
        return frames[direction][index];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}