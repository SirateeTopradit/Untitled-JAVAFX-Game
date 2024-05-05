package main;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * MapManager class is responsible for managing the game map.
 * This includes loading the map image and drawing the map on the game panel.
 */
public class MapManager {
    private GamePanel gp;
    private Image image;
    private Player player;

    /**
     * Constructor for the MapManager class.
     * Initializes the MapManager with the given game panel and player.
     * Also, loads the map image.
     *
     * @param gp  the game panel instance
     * @param player  the player instance
     */
    public MapManager(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        getImage();
    }

    /**
     * Loads the map image from the resources.
     */
    public void getImage() {
        InputStream resource = getClass().getClassLoader().getResourceAsStream("Map/bg.png");
        if (resource != null) {
            image = new Image(resource);
        } else {
            System.out.println("Resource not found: " + "Map/bg.png");
        }
    }

    /**
     * Draws the map on the game panel.
     *
     * @param g2d  the graphics context to draw on
     */
    public void drawMap(GraphicsContext g2d) {
        double translateX = (double) gp.getScreenWidth() / 2 - player.getWorldX()-40;
        double translateY = (double) gp.getScreenHeight() / 2 - player.getWorldY()-10-40;
        g2d.drawImage(image, translateX, translateY, gp.getWorldScreenWidth(), gp.getWorldScreenHeight());
    }
}