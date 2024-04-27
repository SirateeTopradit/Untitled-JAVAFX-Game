package main;

import entity.Entity;
import entity.Zomby;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setMonster() {
        Entity[] monster = gp.getMonster();
        for (int i = 0; i < 8; i++) {
            monster[i] = new Zomby(gp);
            int randomX = (int) (Math.random() * gp.worldScreenWidth-1080);
            int randomY = (int) (Math.random() * gp.worldScreenHeight-1920);
            monster[i].setWorldX(randomX+1080);
            monster[i].setWorldY(randomY+1920);
        }
        gp.setMonster(monster);
    }
}
