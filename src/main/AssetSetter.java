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
            int randomX = (int) (Math.random() * gp.worldScreenWidth);
            int randomY = (int) (Math.random() * gp.worldScreenHeight);
            monster[i].setWorldX(randomX);
            monster[i].setWorldY(randomY);
        }
        gp.setMonster(monster);
    }
    public void addMonster(int i) {
        Entity[] monster = gp.getMonster();
        monster[i] = new Zomby(gp);
        int randomX = (int) (Math.random() * gp.worldScreenWidth);
        int randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[i].setWorldX(randomX);
        monster[i].setWorldY(randomY);
        gp.setMonster(monster);
    }

}
