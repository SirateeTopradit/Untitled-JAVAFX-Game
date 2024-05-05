package weapon;

import javafx.scene.canvas.GraphicsContext;

interface BaseWeapon {
    public void draw(GraphicsContext gc);
    public void update();
    public void startTimer();
}