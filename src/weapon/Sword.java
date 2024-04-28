package weapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;


public class Sword extends Weapon{
    private Image[] frames = new Image[getNUM_FRAMES()];
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
    public Image getCurrentFrame() {
        int index = (int) ((System.currentTimeMillis() - getStartTime()) / 50) % getNUM_FRAMES();
        return frames[index];
    }
    @Override
    public void update() {
        setWorldX(gp.getPlayer().getWorldX());
        setWorldY(gp.getPlayer().getWorldY());
        if (isAvailable()) {
            setHitBox(new Rectangle(0, 0, 400, 400));
        }else {
            setHitBox(new Rectangle(0, 0, 0, 0));
        }
    }
    @Override
    public void draw(GraphicsContext gc) {
        if (isAvailable()) {
            gc.drawImage(getCurrentFrame(), getScreenX()-100, getScreenY()-100,400,400);
//            gc.setFill(javafx.scene.paint.Color.BLUE);
//            gc.fillRect(screenX + getHitBox().getX(), screenY + getHitBox().getY(), getHitBox().getWidth(), getHitBox().getHeight());
        }
    }
    public void startTimer() {
        setTimer(new Timer());
        getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer task started");
                setAvailable(!isAvailable());
            }
        }, 0, 2000);
    }


}
