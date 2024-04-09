package main;

import entity.Entity;
import javafx.scene.shape.Rectangle;

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
    public void checkEntity(Entity entity,Entity[] target) {
        for (Entity zombie : target) {
            if (zombie != null && zombie != entity) {
                Rectangle entityHitBox = new Rectangle(entity.getWorldX() + entity.getHitBoxWalk().getX(), entity.getWorldY() + entity.getHitBoxWalk().getY(), entity.getHitBoxWalk().getWidth(), entity.getHitBoxWalk().getHeight());
                Rectangle otherHitBox = new Rectangle(zombie.getWorldX() + zombie.getHitBoxWalk().getX(), zombie.getWorldY() + zombie.getHitBoxWalk().getY(), zombie.getHitBoxWalk().getWidth(), zombie.getHitBoxWalk().getHeight());
//                if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())) {
//                    int randomX = (int) (Math.random() * gp.worldScreenWidth);
//                    int randomY = (int) (Math.random() * gp.worldScreenHeight);
//                    entity.setWorldX(randomX);
//                    entity.setWorldY(randomY);
//                }
                switch (entity.getDirection()){
                    case 0:
                        entityHitBox.setX(entityHitBox.getX() + entity.getSpeed());
                        if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())) {
                            entity.setColliding(true);
                        }
                        break;
                    case 2:
                        entityHitBox.setX(entityHitBox.getX() - entity.getSpeed());
                        if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())) {
                            entity.setColliding(true);
                        }
                        break;
                }
                switch (entity.getDirectionY()){
                    case 1:
                        entityHitBox.setY(entityHitBox.getY() + entity.getSpeed());
                        if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())) {
                            entity.setColliding(true);
                        }
                        break;
                    case 3:
                        entityHitBox.setY(entityHitBox.getY() - entity.getSpeed());
                        if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())) {
                            entity.setColliding(true);
                        }
                        break;
                }
            }
        }
    }
}