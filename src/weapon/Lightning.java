package weapon;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

import java.util.Timer;
import java.util.TimerTask;

public class Lightning extends Weapon{
    public Lightning(GamePanel gp) {
        super(gp);
        setAvailable(false);
        setScreenX((gp.getScreenWidth() / 2) - 60 - 40);
        setScreenY((gp.getScreenHeight() / 2) - 110);
        startTimer();
        setStartTime(System.currentTimeMillis());
    }
    int countFrame;
    @Override
    public void update() {
        setAtk((100*getLevel()));
        setWorldX(gp.getPlayer().getWorldX());
        setWorldY(gp.getPlayer().getWorldY());
    }
    @Override
    public void draw(GraphicsContext gc) {
        if (isAvailable()&&countFrame<4) {
            gc.setFill(Color.LIGHTBLUE);
            gc.fillRect(gp.getPlayer().getScreenX()+80+dx ,0, 20, gp.getPlayer().getScreenY()+100+dy);
        }
        countFrame++;
    }
    public void startTimer() {
        setTimer(new Timer());
        int interval = 1800 / getLevel();
        getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (gp.getMonster()[num] != null) {
                    playSFX(2);
                    gp.getMonster()[num].setHp(gp.getMonster()[num].getHp() - getAtk());
                    gp.getMonster()[num].setAttacked(true);
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
                    targetMonster();
                    setAvailable(!isAvailable());
                    countFrame = 0;
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
}
