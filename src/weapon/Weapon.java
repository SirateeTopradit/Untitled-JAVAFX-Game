package weapon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class Weapon {
    GamePanel gp;
    private Rectangle hitBox;
    private int screenX;
    private int screenY;
    private int worldX;
    private int worldY;
    private boolean isAvailable;
    private Timer timer;
    private int NUM_FRAMES = 10;


    public Weapon(GamePanel gp) {
        this.gp = gp;
    }
    public void draw(GraphicsContext gc) {
        if (isAvailable()) {
            gc.setFill(javafx.scene.paint.Color.BLUE);
            gc.fillRect(screenX + getHitBox().getX(), screenY + getHitBox().getY(), getHitBox().getWidth(), getHitBox().getHeight());
        }
    }
    public void update() {
    }
    public void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("Timer task started");
                isAvailable = !isAvailable;
            }
        }, 0, 2000);
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


}
