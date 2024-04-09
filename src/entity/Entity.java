package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

public class Entity {
    private int worldX, worldY;
    GamePanel gp;
    int direction;
    private int speed;
    private Rectangle hitBox;
    private Rectangle hitBoxWalk;
    private final int NUM_FRAMES = 20;
    private final int NUM_DIRECTIONS = 4; // number of directions
    private Image[][] frames;
    private final long startTime;
    private int directionY;

    public Entity(GamePanel gp) {
        this.gp = gp;
        frames = new Image[NUM_DIRECTIONS][NUM_FRAMES*2];
        startTime = System.currentTimeMillis();
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public Rectangle getHitBoxWalk() {
        return hitBoxWalk;
    }

    public void setHitBoxWalk(Rectangle hitBoxWalk) {
        this.hitBoxWalk = hitBoxWalk;
    }

    public void setWorldX(int x) {
        this.worldX = x;
    }

    public void setWorldY(int y) {
        this.worldY = y;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    private Boolean isColliding = false;

    public void draw(GraphicsContext gc) {
        int chunkSize = 200;
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if(     worldX + gp.getTileSize() + chunkSize > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() - chunkSize < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() + chunkSize > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() - chunkSize < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY() ){

            int EntitySize = (gp.getTileSize() * gp.getTileSize())/2;
            if(gp.getDebugMode()) {
                gc.setFill(javafx.scene.paint.Color.BLUE);
                gc.fillRect(screenX + getHitBoxWalk().getX(), screenY + getHitBoxWalk().getY(), getHitBoxWalk().getWidth(), getHitBoxWalk().getHeight());

                gc.setFill(javafx.scene.paint.Color.RED);
                gc.fillRect(screenX + getHitBox().getX(), screenY + getHitBox().getY(), getHitBox().getWidth(), getHitBox().getHeight());
            }
            Image fxImage = getCurrentFrame(0);
            if (direction == 0) {
                gc.save(); // save the current state of the GraphicsContext
                gc.scale(-1, 1); // flip the image
                gc.drawImage(fxImage, -screenX - EntitySize, screenY, EntitySize, EntitySize);
                gc.restore(); // restore the original state of the GraphicsContext
            } else {
                gc.drawImage(fxImage, screenX, screenY, EntitySize, EntitySize);
            }
        }
    }
    public void update() {
        int dx = gp.getPlayer().getWorldX() - getWorldX();
        int dy = gp.getPlayer().getWorldY() - getWorldY();
        int nextX = getWorldX();
        int nextY = getWorldY();
        if (dx >= 0){
            direction = 0;
        }
        else {
            direction = 2;
        }
        if (dy >= 0){
            directionY = 1;
        }
        else {
            directionY = 3;
        }
        gp.getCollisionChecker().checkEntity(this, gp.getEntity());
        if (!isColliding) {
            // Pathfinding
            if (Math.abs(dx) > Math.abs(dy)) {
                // Move in x direction
                if (dx > 0) {
                    nextX += getSpeed();
                } else if (dx < 0) {
                    nextX -= getSpeed();
                }
            } else {
                // Move in y direction
                if (dy > 0) {
                    nextY += getSpeed();
                } else if (dy < 0) {
                    nextY -= getSpeed();
                }
            }
            setWorldX(nextX);
            setWorldY(nextY);
        }
        isColliding = false;
    }
    public char getDirectionFromIndex(int index) {
        return switch (index) {
            case 0 -> 'a';
            case 1 -> 'd';
            case 2 -> 's';
            default -> 'w';
        };
    }

    public Image getCurrentFrame(int direction) {
        int index = (int) ((System.currentTimeMillis() - startTime) / 50) % NUM_FRAMES;
        return frames[direction][index];
    }

    public int getNUM_FRAMES() {
        return NUM_FRAMES;
    }

    public int getNUM_DIRECTIONS() {
        return NUM_DIRECTIONS;
    }

    public Image[][] getFrames() {
        return frames;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setFrames(Image[][] frames) {
        this.frames = frames;
    }

    public Boolean getColliding() {
        return isColliding;
    }

    public void setColliding(Boolean colliding) {
        isColliding = colliding;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }
}