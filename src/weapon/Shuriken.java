package weapon;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class Shuriken extends Weapon {
    double angle;
    double length;
    private Image[] frames;

    public Shuriken(GamePanel gp) {
        super(gp);
        setScreenX((gp.getScreenWidth() / 2) - 60 - 40);
        setScreenY((gp.getScreenHeight() / 2) - 110);
        setAvailable(false);
        startTimer();
        setStartTime(System.currentTimeMillis());
        frames = new Image[getNUM_FRAMES()];
        try {
                for (int i = 0; i < getNUM_FRAMES(); i++) {
                    InputStream resource = getClass().getClassLoader().getResourceAsStream(String.format("Kj/KongJuk%d.png",i));
                    if (resource != null) {
                        getFrames()[i] = new Image(resource);
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        setAtk(40*gp.getStage());
        setWorldX(gp.getPlayer().getWorldX());
        setWorldY(gp.getPlayer().getWorldY());
    }
    int countBulletFrame;
    @Override
    public void draw(GraphicsContext gc) {
        if (isAvailable()) {
            Image fxImage = getCurrentFrame();
            gc.drawImage(fxImage, gp.getPlayer().getScreenX()+80 + (dx/12) * countBulletFrame, gp.getPlayer().getScreenY()+100 + (dy/12) * countBulletFrame, 80, 80);
            countBulletFrame++;
        }
    }
@Override
public void startTimer() {
    setTimer(new Timer());
    int interval = 200;
    getTimer().schedule(new TimerTask() {
        @Override
        public void run() {
            if (gp.getMonster()[num] != null) {
                if (gp.getMonster()[num] != null) gp.getMonster()[num].setAttacked(true);
                if (gp.getMonster()[num] != null) gp.getMonster()[num].setHp(gp.getMonster()[num].getHp() - getAtk());
                double magnitude = Math.sqrt(dx * dx + dy * dy);
                dx /= magnitude;
                dy /= magnitude;
                int knockBackDistance = 40;
                if (gp.getMonster()[num] != null) gp.getMonster()[num].setWorldX(gp.getMonster()[num].getWorldX() + (int) (dx * knockBackDistance));
                if (gp.getMonster()[num] != null) gp.getMonster()[num].setWorldY(gp.getMonster()[num].getWorldY() + (int) (dy * knockBackDistance));
                playSFX(6);
                if (gp.getMonster()[num] != null) {
                    if (gp.getMonster()[num].getHp() <= 0) {
                        gp.setScore(gp.getScore() + gp.getMonster()[num].getPoints());
                        Entity[] monsters = gp.getMonster();
                        for (int i = 0; i < monsters.length; i++) {
                            if (monsters[i] == gp.getMonster()[num]) {
                                monsters[i] = null;
                                break;
                            }
                        }
                        gp.setMonster(monsters);
                    }
                }
                targetMonster();
                setAvailable(!isAvailable());
                countBulletFrame = 0;
            }
        }
    }, 0, interval);
}
    int num;
    public void targetMonster() {
        getNearestMonster();
        Entity monster = gp.getMonster()[num];
        if (monster == null) {
            return ;
        }
        getDistanceToMonsters(monster);
    }
    double dx;
    double dy;

    public void getDistanceToMonsters(Entity monster) {
        dx = monster.getWorldX() - this.getWorldX();
        dy = monster.getWorldY() - this.getWorldY();
    }
    public double getDistanceToMonster(Entity monster) {
        dx = monster.getWorldX() - this.getWorldX();
        dy = monster.getWorldY() - this.getWorldY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    public void getNearestMonster() {
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < gp.getMonster().length; i++) {
            if (gp.getMonster()[i] == null) {
                continue;
            }
            double distance = getDistanceToMonster(gp.getMonster()[i]);
            if (distance < minDistance) {
                minDistance = distance;
                num = i;
            }
        }
    }
    @Override
    public void updateInterval() {
        if (getTimer() != null) {
            getTimer().cancel();
            setTimer(null);
        }
        startTimer();
    }
    public Image getCurrentFrame() {
        int index = (int) ((System.currentTimeMillis() - getStartTime()) / 50) % getNUM_FRAMES();
        return frames[index];
    }
    public Image[] getFrames() {
        return frames;
    }
}
