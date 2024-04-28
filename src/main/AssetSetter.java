package main;

import entity.*;
import weapon.Laser;
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
//        for (int i = 0; i < 8; i++) {
            monster[0] = new Zomby(gp);
            int randomX = (int) (Math.random() * gp.worldScreenWidth);
            int randomY = (int) (Math.random() * gp.worldScreenHeight);
            monster[0].setWorldX(randomX);
            monster[0].setWorldY(randomY);
        monster[1] = new Theft(gp, 1, 1, 1);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[1].setWorldX(randomX);
        monster[1].setWorldY(randomY);
        monster[2] = new Stone(gp, 1, 1, 1);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[2].setWorldX(randomX);
        monster[2].setWorldY(randomY);
        monster[3] = new NoobGhost(gp, 1, 1, 1);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[3].setWorldX(randomX);
        monster[3].setWorldY(randomY);
        monster[4] = new DarkKnight(gp, 1, 1, 1);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[4].setWorldX(randomX);
        monster[4].setWorldY(randomY);
        monster[5] = new Bone(gp, 1, 1, 1);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[5].setWorldX(randomX);
        monster[5].setWorldY(randomY);
        monster[6] = new Zomby(gp, 1, 1, 1);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[6].setWorldX(randomX);
        monster[6].setWorldY(randomY);
        monster[7] = new Zomby(gp, 1, 1, 1);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[7].setWorldX(randomX);
        monster[7].setWorldY(randomY);
//        }
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
        monster[i] = new Zomby(gp, getHpStatus(status), getSpdStatus(status), getAtkStatus(status));
        int randomX = (int) (Math.random() * gp.worldScreenWidth);
        int randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[i].setWorldX(randomX);
        monster[i].setWorldY(randomY);
        gp.setMonster(monster);
    }
    public void setWeapon() {
        Weapon[] weapons = gp.getWeapons();
        weapons[0] = new Sword(gp);
        weapons[1] = new Laser(gp);
        gp.setWeapons(weapons);
    }

    public float getHpStatus(int i) {
        return (float) round((pow(i + 1, 1.5) - pow(i, 1.5)) * 100) / 100;
    }

    public float getAtkStatus(int i) {
        return (float) round((pow(i + 1, 1.3) - pow(i, 1.3)) * 100) / 100;
    }

    public float getSpdStatus(int i) {
        return (float) (round((pow(i + 1, 1.1) - pow(i, 1.1)) * 100) / 100);
    }
}
