package main;

import entity.Entity;
import entity.Player;
import entity.Zomby;
import javafx.scene.shape.Rectangle;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import weapon.Laser;
import weapon.Lightning;
import weapon.Weapon;

import java.util.Arrays;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkCollision() {
        int playerX = gp.player.getWorldX();
        int playerY = gp.player.getWorldY();
        double playerWidth = gp.player.getHitBoxWalk().getWidth();
        double playerHeight = gp.player.getHitBoxWalk().getHeight();
        if(playerX < 0) {
            gp.player.setWorldX(0);
        }
        if(playerX + playerWidth > gp.worldScreenWidth) {
            gp.player.setWorldX((int) (gp.worldScreenWidth - playerWidth));
        }
        if(playerY < 0) {
            gp.player.setWorldY(0);
        }
        if(playerY + playerHeight > gp.worldScreenHeight) {
            gp.player.setWorldY((int) (gp.worldScreenHeight - playerHeight));
        }
//        for (int i = 0; i < gp.map.length; i++) {
//            for (int j = 0; j < gp.map[i].length; j++) {
//                int tileX = j * 20;
//                int tileY = i * 20;
//                int tileWidth = 20;
//                int tileHeight = 20;
//                if (gp.map[i][j].collision) {
//                    if (playerX + playerWidth > tileX && playerX < tileX + tileWidth && playerY + playerHeight > tileY && playerY < tileY + tileHeight) {
//                        gp.player.setWorldX(gp.player.getWorldX() - gp.player.getSpeed());
//                    }
//                }
//            }
//        }
    }
    public void checkEntity(Entity entity, Entity[] target) {
        for (Entity me : target) {
            Rectangle entityHitBox = new Rectangle(entity.getWorldX() + entity.getHitBoxWalk().getX(), entity.getWorldY() + entity.getHitBoxWalk().getY(), entity.getHitBoxWalk().getWidth(), entity.getHitBoxWalk().getHeight());
            for (Weapon W: gp.getWeapons()){
                if (!(me instanceof Player)) {
                    if (W == null) continue;
                    if (W instanceof Laser ) continue;
                    if (W instanceof Lightning) continue;
                    Rectangle weaponHitBox = new Rectangle(W.getWorldX() + W.getHitBox().getX(), W.getWorldY() + W.getHitBox().getY(), W.getHitBox().getWidth(), W.getHitBox().getHeight());
                    if (entityHitBox.getBoundsInParent().intersects(weaponHitBox.getBoundsInParent())) {

                        double dx = entity.getWorldX() - me.getWorldX();
                        double dy = entity.getWorldY() - me.getWorldY();
                        double magnitude = Math.sqrt(dx * dx + dy * dy);
                        dx /= magnitude;
                        dy /= magnitude;
//                        int knockBackDistance = 20;
//                        entity.setWorldX(entity.getWorldX() + (int) (dx * knockBackDistance));
//                        entity.setWorldY(entity.getWorldY() + (int) (dy * knockBackDistance));
                        entity.setColliding(true);
                    }
                }
            }
            if (me != entity && me instanceof Player) {
                Rectangle otherHitBox = new Rectangle(me.getWorldX() + me.getHitBoxWalk().getX(), me.getWorldY() + me.getHitBoxWalk().getY(), me.getHitBoxWalk().getWidth(), me.getHitBoxWalk().getHeight());
                if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())){
                    gp.getPlayer().setHp(gp.getPlayer().getHp() - entity.getAtk());
                    gp.getPlayer().setAttacked(true);
                    double dx = entity.getWorldX() - me.getWorldX();
                    double dy = entity.getWorldY() - me.getWorldY();
                    double magnitude = Math.sqrt(dx * dx + dy * dy);
                    dx /= magnitude;
                    dy /= magnitude;
//                    int knockBackDistance = 40;
//                    entity.setWorldX(entity.getWorldX() + (int)(dx * knockBackDistance));
//                    entity.setWorldY(entity.getWorldY() + (int)(dy * knockBackDistance));
                    entity.setColliding(true);
                }
//                switch (entity.getDirection()) {
//                    case 0:
//                        entityHitBox.setX(entityHitBox.getX() + entity.getSpeed());
//                        if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())) {
//                            entity.setColliding(true);
//                        }
//                        break;
//                    case 2:
//                        entityHitBox.setX(entityHitBox.getX() - entity.getSpeed());
//                        if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())) {
//                            entity.setColliding(true);
//                        }
//                        break;
//                }
//                switch (entity.getDirectionY()) {
//                    case 1:
//                        entityHitBox.setY(entityHitBox.getY() + entity.getSpeed());
//                        if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())) {
//                            entity.setColliding(true);
//                        }
//                        break;
//                    case 3:
//                        entityHitBox.setY(entityHitBox.getY() - entity.getSpeed());
//                        if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())) {
//                            entity.setColliding(true);
//                        }
//                        break;
//                }
            }
        }
    }
}