package weapon;

import javafx.scene.canvas.GraphicsContext;

/**
 * BaseWeapon interface defines the basic functionalities that any weapon should have.
 * This includes drawing the weapon, updating the weapon state, and starting a timer for the weapon.
 */
interface BaseWeapon {

    /**
     * Draws the weapon on the game panel.
     *
     * @param gc  the graphics context to draw on
     */
    public void draw(GraphicsContext gc);

    /**
     * Updates the state of the weapon.
     * This could include changing the position, checking for collisions, etc.
     */
    public void update();

    /**
     * Starts a timer for the weapon.
     * This could be used for things like firing rate, weapon duration, etc.
     */
    public void startTimer();
}