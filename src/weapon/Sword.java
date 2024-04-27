package weapon;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

public class Sword extends Weapon{
    public Sword(GamePanel gp) {
        super(gp);
        System.out.println("Sword created");
        setHitBox(new Rectangle(0, 5*20, 2*20, 4*20));
        setScreenX((gp.getScreenWidth()/2)-60-40);
        setScreenY((gp.getScreenHeight()/2)-110-40);
        setWorldX((gp.getWorldScreenWidth() / 2)-60-40);
        setWorldY((gp.getWorldScreenHeight() / 2)-100-40);
    }
}
