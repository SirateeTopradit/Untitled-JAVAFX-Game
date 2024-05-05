package main;

import entity.*;
import weapon.Shuriken;
import weapon.Lightning;
import weapon.Sword;
import weapon.Weapon;

import static java.lang.Math.pow;
import static java.lang.Math.round;
/**
 * AssetSetter class is responsible for setting up the game assets.
 * This includes setting up the monsters and weapons in the game.
 */
public class AssetSetter {
    private GamePanel gp;
    /**
     * Constructor for the AssetSetter class.
     * Initializes the AssetSetter with the given game panel.
     *
     * @param gp  the game panel instance
     */
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    /**
     * Sets up the monsters in the game.
     */
    public void setMonster() {
        Entity[] monster = gp.getMonster();
        for (int i = 0; i < monster.length; i++) {
            monster[i] = getNewMonster(gp.randomMonsterType(), 0);
            int randomX = (int) (Math.random() * gp.getWorldScreenWidth());
            int randomY = (int) (Math.random() * gp.getWorldScreenHeight());
            monster[i].setWorldX(randomX);
            monster[i].setWorldY(randomY);
        }
        gp.setMonster(monster);
    }
    /**
     * Adds a new monster to the game.
     *
     * @param i  the index of the monster to add
     * @param j  the type of the monster to add
     * @param status  the status of the monster to add
     */
    public void addMonster(int i, int j, int status) {
        Entity[] monster = gp.getMonster();
        monster[i] = getNewMonster(j, status);
        int randomX = (int) (Math.random() * gp.getWorldScreenWidth());
        int randomY = (int) (Math.random() * gp.getWorldScreenHeight());
        monster[i].setWorldX(randomX);
        monster[i].setWorldY(randomY);
        gp.setMonster(monster);
    }
    /**
     * Sets up the weapons in the game.
     */
    public void setWeapon() {
        Weapon[] weapons = gp.getWeapons();
        weapons[0] = new Sword(gp);
        weapons[1] = new Lightning(gp);
        weapons[2] = new Shuriken(gp);
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
