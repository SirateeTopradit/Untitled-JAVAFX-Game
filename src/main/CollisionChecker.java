package main;

import entity.Entity;
import entity.Player;
import javafx.scene.shape.Rectangle;
import weapon.Weapon;

/**
 * CollisionChecker class is responsible for checking collisions between entities in the game.
 * It includes methods for checking collision of an entity with the game boundaries and other entities.
 */
public class CollisionChecker {
    GamePanel gp;
    private BackgroundSound soundEffect = new BackgroundSound();
    /**
     * Constructor for the CollisionChecker class.
     * Initializes the CollisionChecker with the game panel.
     *
     * @param gp  the game panel
     */
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    /**
     * Checks if the player entity is colliding with the game boundaries.
     * If the player is colliding with the game boundaries, it adjusts the player's position to stay within the game boundaries.
     */
    public void checkCollision() {
        int playerX = gp.player.getWorldX();
        int playerY = gp.player.getWorldY();
        double playerWidth = gp.player.getHitBoxWalk().getWidth();
        double playerHeight = gp.player.getHitBoxWalk().getHeight();
        if(playerX < 0) {
            gp.player.setWorldX(0);
        }
        if(playerX + playerWidth > gp.worldScreenWidth) {
            gp.player.setWorldX((int) (gp.worldScreenWidth - playerWidth));
        }
        if(playerY < 0) {
            gp.player.setWorldY(0);
        }
        if(playerY + playerHeight > gp.worldScreenHeight) {
            gp.player.setWorldY((int) (gp.worldScreenHeight - playerHeight));
        }
    }
    /**
     * Checks if the given entity is colliding with any entity in the target array.
     * If the entity is colliding with any entity in the target array, it sets the colliding property of the entity to true.
     *
     * @param entity  the entity to check collision for
     * @param target  the array of entities to check collision against
     */
    public void checkEntity(Entity entity, Entity[] target) {
        for (Entity me : target) {
            Rectangle entityHitBox = new Rectangle(entity.getWorldX() + entity.getHitBoxWalk().getX(), entity.getWorldY() + entity.getHitBoxWalk().getY(), entity.getHitBoxWalk().getWidth(), entity.getHitBoxWalk().getHeight());
            Weapon W = gp.getWeapons()[0];
            Rectangle weaponHitBox = new Rectangle(W.getWorldX() + W.getHitBox().getX(), W.getWorldY() + W.getHitBox().getY(), W.getHitBox().getWidth(), W.getHitBox().getHeight());
            if (entityHitBox.getBoundsInParent().intersects(weaponHitBox.getBoundsInParent())) {
                entity.setColliding(true);
            }
            gotHit(me, entity, entityHitBox);
        }
    }
    /**
     * Checks if the given entity got hit by the player.
     * If the entity got hit by the player, it reduces the player's HP by the entity's attack power, plays a sound effect, and knocks back the entity.
     *
     * @param me  the player entity
     * @param entity  the entity to check if it got hit
     * @param entityHitBox  the hitbox of the entity
     */
    public void gotHit(Entity me, Entity entity, Rectangle entityHitBox){
        if (me != entity && me instanceof Player) {
            Rectangle otherHitBox = new Rectangle(me.getWorldX() + me.getHitBoxWalk().getX(), me.getWorldY() + me.getHitBoxWalk().getY(), me.getHitBoxWalk().getWidth(), me.getHitBoxWalk().getHeight());
            if (entityHitBox.getBoundsInParent().intersects(otherHitBox.getBoundsInParent())){
                gp.getPlayer().setHp(gp.getPlayer().getHp() - entity.getAtk());
                gp.getPlayer().setAttacked(true);
                playSFX(5);
                knockBack(me, entity);
                entity.setColliding(true);
            }
        }
    }
    /**
     * Knocks back the given entity away from the player.
     *
     * @param me  the player entity
     * @param entity  the entity to knock back
     */
    public void knockBack(Entity me, Entity entity){
        double dx = entity.getWorldX() - me.getWorldX();
        double dy = entity.getWorldY() - me.getWorldY();
        double magnitude = Math.sqrt(dx * dx + dy * dy);
        dx /= magnitude;
        dy /= magnitude;
        int knockBackDistance = 50;
        entity.setWorldX(entity.getWorldX() + (int)(dx * knockBackDistance));
        entity.setWorldY(entity.getWorldY() + (int)(dy * knockBackDistance));
    }
    /**
     * Plays a sound effect.
     *
     * @param SFXIndex  the index of the sound effect to play
     */
    public void playSFX(int SFXIndex) {
        soundEffect.setFile(SFXIndex);
        soundEffect.play();
    }
}