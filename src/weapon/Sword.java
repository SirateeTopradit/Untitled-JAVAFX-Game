package weapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

import java.io.InputStream;



public class Sword extends Weapon{
    private final long startTime;
    private Image[] frames = new Image[getNUM_FRAMES()];
    public Sword(GamePanel gp) {
        super(gp);
        System.out.println("Sword created");
        setHitBox(new Rectangle(0, 0, 200, 200));
        setScreenX((gp.getScreenWidth()/2)-60-40);
        setScreenY((gp.getScreenHeight()/2)-110);
        setAvailable(false);
        startTimer();
        startTime = System.currentTimeMillis();
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
        int index = (int) ((System.currentTimeMillis() - startTime) / 50) % getNUM_FRAMES();
        return frames[index];
    }
    @Override
    public void update() {
        setWorldX(gp.getPlayer().getWorldX());
        setWorldY(gp.getPlayer().getWorldY());
        if (isAvailable()) {
            setHitBox(new Rectangle(0, 0, 200, 200));
        }else {
            setHitBox(new Rectangle(0, 0, 0, 0));
        }
    }
    @Override
    public void draw(GraphicsContext gc) {
        if (isAvailable()) {
            gc.drawImage(getCurrentFrame(), getScreenX(), getScreenY(),200,200);
//            gc.setFill(javafx.scene.paint.Color.BLUE);
//            gc.fillRect(screenX + getHitBox().getX(), screenY + getHitBox().getY(), getHitBox().getWidth(), getHitBox().getHeight());
        }
    }


}
