package main;

import entity.Entity;
import entity.Zomby;
import weapon.Sword;
import weapon.Weapon;

import static java.lang.Math.pow;
import static java.lang.Math.round;

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
    public void addMonster(int i, int status) {
        Entity[] monster = gp.getMonster();
        monster[i] = new Zomby(gp, getHpStatus(status), getSpdStatus(status));
        int randomX = (int) (Math.random() * gp.worldScreenWidth);
        int randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[i].setWorldX(randomX);
        monster[i].setWorldY(randomY);
        gp.setMonster(monster);
    }
    public void setWeapon() {
        Weapon[] weapons = gp.getWeapons();
        weapons[0] = new Sword(gp);
        gp.setWeapons(weapons);
    }

    public float getHpStatus(int i) {
        return (float) round((pow(i + 1, 1.5) - pow(i, 1.5)) * 100) / 100;
    }

    public float getAtkStatus(int i) {
        return (float) round((pow(i + 1, 1.3) - pow(i, 1.3)) * 100) / 100;
    }

    public float getSpdStatus(int i) {
        return (float) round((pow(i + 1, 1.1) - pow(i, 1.1)) * 100) / 100;
    }
}
