package main;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkCollision() {
        int playerX = gp.player.getWorldX();
        int playerY = gp.player.getWorldY();
        int playerWidth = gp.player.getHitBoxWalk().width;
        int playerHeight = gp.player.getHitBoxWalk().height;
        if(playerX < 0) {
            gp.player.setWorldX(0);
        }
        if(playerX + playerWidth > gp.worldScreenWidth) {
            gp.player.setWorldX(gp.worldScreenWidth - playerWidth);
        }
        if(playerY < -10) {
            gp.player.setWorldY(-10);
        }
        if(playerY + playerHeight > gp.worldScreenHeight-10) {
            gp.player.setWorldY(gp.worldScreenHeight - playerHeight - 10);
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
}
