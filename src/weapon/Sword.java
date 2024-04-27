package weapon;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

public class Sword extends Weapon{
    public Sword(GamePanel gp) {
        super(gp);
        System.out.println("Sword created");
        setHitBox(new Rectangle(0, 0, 200, 200));
        setScreenX((gp.getScreenWidth()/2)-60-40);
        setScreenY((gp.getScreenHeight()/2)-110);
    }
    @Override
    public void update() {
        setWorldX(gp.getPlayer().getWorldX());
        setWorldY(gp.getPlayer().getWorldY());
    }


}
