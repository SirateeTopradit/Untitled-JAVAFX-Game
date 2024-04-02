package entity;

import javafx.scene.shape.Rectangle;

public class Entity {
    private int worldX, worldY;
    private int speed;
    private Rectangle hitBox;
    private Rectangle hitBoxWalk;

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
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
}