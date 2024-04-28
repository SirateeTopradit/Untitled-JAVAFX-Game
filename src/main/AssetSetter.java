package main;

import entity.*;
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
        monster[0] = getNewMonster(gp.randomMonsterType(), 0);
        int randomX = (int) (Math.random() * gp.worldScreenWidth);
        int randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[0].setWorldX(randomX);
        monster[0].setWorldY(randomY);
        monster[1] = getNewMonster(gp.randomMonsterType(), 0);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[1].setWorldX(randomX);
        monster[1].setWorldY(randomY);
        monster[2] = getNewMonster(gp.randomMonsterType(), 0);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[2].setWorldX(randomX);
        monster[2].setWorldY(randomY);
        monster[3] = getNewMonster(gp.randomMonsterType(), 0);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[3].setWorldX(randomX);
        monster[3].setWorldY(randomY);
        monster[4] = getNewMonster(gp.randomMonsterType(), 0);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[4].setWorldX(randomX);
        monster[4].setWorldY(randomY);
        monster[5] = getNewMonster(gp.randomMonsterType(), 0);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[5].setWorldX(randomX);
        monster[5].setWorldY(randomY);
        monster[6] = getNewMonster(gp.randomMonsterType(), 0);
        randomX = (int) (Math.random() * gp.worldScreenWidth);
        randomY = (int) (Math.random() * gp.worldScreenHeight);
        monster[6].setWorldX(randomX);
        monster[6].setWorldY(randomY);
        monster[7] = getNewMonster(gp.randomMonsterType(), 0);
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
    public void addMonster(int i, int j, int status) {
        Entity[] monster = gp.getMonster();
        monster[i] = getNewMonster(j, status);
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
        return i < 1 ? 1 : (float) round((pow(i + 1, 1.5) - pow(i, 1.5)) * 100) / 100;
    }

    public float getAtkStatus(int i) {
        return i < 1 ? 1 : (float) round((pow(i + 1, 1.3) - pow(i, 1.3)) * 100) / 100;
    }

    public float getSpdStatus(int i) {
        return i < 1 ? 1 : (float) (round((pow(i + 1, 1.1) - pow(i, 1.1)) * 100) / 100);
    }

    public Entity getNewMonster(int i, int status) {
        Entity monster = null;
        switch (i) {
            case 0 : {
                monster = new Bone(gp, getHpStatus(status), getSpdStatus(status), getAtkStatus(status));
                break;
            }
            case 1 : {
                monster = new DarkKnight(gp, getHpStatus(status), getSpdStatus(status), getAtkStatus(status));
                break;
            }
            case 2 : {
                monster = new NoobGhost(gp, getHpStatus(status), getSpdStatus(status), getAtkStatus(status));
                break;
            }
            case 3 : {
                monster = new Stone(gp, getHpStatus(status), getSpdStatus(status), getAtkStatus(status));
                break;
            }
            case 4 : {
                monster = new Theft(gp, getHpStatus(status), getSpdStatus(status), getAtkStatus(status));
                break;
            }
            case 5 : {
                monster = new Zomby(gp, getHpStatus(status), getSpdStatus(status), getAtkStatus(status));
                break;
            }
        }
        return monster;
    }
}
