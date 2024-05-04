package weapon;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

import java.util.Timer;
import java.util.TimerTask;

public class Shuriken extends Weapon {
    double angle;
    double length;

    public Shuriken(GamePanel gp) {
        super(gp);
//        setHitBox(new Rectangle(0, 0, 200, 200));
        setScreenX((gp.getScreenWidth() / 2) - 60 - 40);
        setScreenY((gp.getScreenHeight() / 2) - 110);
        setAvailable(false);
        startTimer();
        setStartTime(System.currentTimeMillis());
    }

    @Override
    public void update() {
        setWorldX(gp.getPlayer().getWorldX());
        setWorldY(gp.getPlayer().getWorldY());
        if (isAvailable()) {
            setHitBox(new Rectangle(0, 0, length, 10));
        } else {
            setHitBox(new Rectangle(0, 0, 0, 0));
        }
    }
    int countBulletFrame;
    @Override
    public void draw(GraphicsContext gc) {
        if (isAvailable()) {
            gc.setFill(Color.RED);
            gc.fillOval(gp.getPlayer().getScreenX()+80 + (dx/12)*countBulletFrame ,gp.getPlayer().getScreenY()+100 + (dy/12)*countBulletFrame, 40, 40);
            countBulletFrame++;
        }

////        if (isAvailable()) {
//        // Save the current state of the GraphicsContext
//        gc.save();
//
//        // Translate to the center of the laser
//        double centerX = getScreenX();
//        double centerY = getScreenY();
//        gc.translate(centerX, centerY);
//
//        // Rotate the GraphicsContext by the desired angle
//        gc.rotate(angle);
//
//        // Translate back to the original position
//        gc.translate(-centerX, -centerY);
//
//        // Draw the laser
//        gc.setFill(Color.WHITE);
//        gc.fillRect(getScreenX(), getScreenY(), getHitBox().getWidth(), getHitBox().getHeight());
//
//        // Restore the previous state of the GraphicsContext
//        gc.restore();
//
//        }
    }


    public void startTimer() {
        setTimer(new Timer());
        getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(gp.getMonster()[num] != null) {
                    gp.getMonster()[num].setHp(gp.getMonster()[num].getHp() - 20);
                    gp.getMonster()[num].setAttacked(true);
                    double magnitude = Math.sqrt(dx * dx + dy * dy);
                    dx /= magnitude;
                    dy /= magnitude;
                    int knockBackDistance = 40;
                    gp.getMonster()[num].setWorldX(gp.getMonster()[num].getWorldX() + (int) (dx * knockBackDistance));
                    gp.getMonster()[num].setWorldY(gp.getMonster()[num].getWorldY() + (int) (dy * knockBackDistance));
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
                    countBulletFrame = 0;
                }
            }
        }, 0, 200);
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
}
