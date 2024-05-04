package weapon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import main.BackgroundSound;
import main.GamePanel;

import java.util.Timer;
import java.util.TimerTask;

public class Weapon {
    GamePanel gp;
    private Rectangle hitBox;
    private int screenX;
    private int screenY;
    private int worldX;
    private int worldY;
    private boolean isAvailable;
    private Timer timer;
    private int NUM_FRAMES = 5;
    private long startTime;
    private BackgroundSound soundEffect = new BackgroundSound();
    private int atk;

    public Weapon(GamePanel gp) {
        this.gp = gp;
    }
    public int getLevel(){
        if(gp.getStage() < 10){
            return 1;
        } else if (gp.getStage() < 20) {
            return 2;
        } else {
            return 3;
        }
    }
    public void playSFX(int SFXIndex) {
        soundEffect.setFile(SFXIndex);
        soundEffect.play();
    }
    public void draw(GraphicsContext gc) {
    }
    public void update() {
    }
    public void startTimer() {}

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    public int getNUM_FRAMES() {
        return NUM_FRAMES;
    }

    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long l) {
        startTime = l;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    //    public void startTimer() {
    //        setTimer(new Timer());
    //        getTimer().schedule(new TimerTask() {
    //            @Override
    //            public void run() {
    //                if(gp.getMonster()[num] != null) {
    //                    gp.getMonster()[num].setHp(gp.getMonster()[num].getHp() - 20);
    //                    gp.getMonster()[num].setAttacked(true);
    //                    double magnitude = Math.sqrt(dx * dx + dy * dy);
    //                    dx /= magnitude;
    //                    dy /= magnitude;
    //                    int knockBackDistance = 40;
    //                    gp.getMonster()[num].setWorldX(gp.getMonster()[num].getWorldX() + (int) (dx * knockBackDistance));
    //                    gp.getMonster()[num].setWorldY(gp.getMonster()[num].getWorldY() + (int) (dy * knockBackDistance));
    //                    if (gp.getMonster()[num].getHp() <= 0) {
    //                        gp.setScore(gp.getScore() + gp.getMonster()[num].getPoints());
    //                        Entity[] monsters = gp.getMonster();
    //                        for (int i = 0; i < monsters.length; i++) {
    //                            if (monsters[i] == gp.getMonster()[num]) {
    //                                monsters[i] = null;
    //                                break;
    //                            }
    //                        }
    //                        gp.setMonster(monsters);
    //                    }
    //                    targetMonster();
    //                    setAvailable(!isAvailable());
    //                    countBulletFrame = 0;
    //                }
    //            }
    //        }, 0, 200);
    //    }
    public void startTimer(int interval){};

    public void updateInterval() {
    }
}

