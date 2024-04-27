package weapon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

public class Weapon {
    GamePanel gp;
    private Rectangle hitBox;
    private int screenX;
    private int screenY;
    private int worldX;
    private int worldY;
    public Weapon(GamePanel gp) {
        this.gp = gp;
    }
    public void draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.BLUE);
        gc.fillRect(screenX+getHitBox().getX(), screenY+getHitBox().getY(), getHitBox().getWidth(), getHitBox().getHeight());
    }
    public void update() {
        setWorldX(gp.getPlayer().getWorldX());
        setWorldY(gp.getPlayer().getWorldY());
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


}
