package map;

import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GamePanel;

import java.io.InputStream;

public class MapManager {
    GamePanel gp;
    Image image;
    Player player;

    public MapManager(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        getImage();
    }

    private void getImage() {
        InputStream resource = getClass().getClassLoader().getResourceAsStream("Map/bg.png");
        if (resource != null) {
            image = new Image(resource);
        } else {
            System.out.println("Resource not found: " + "Map/bg.png");
        }
    }

    public void drawMap(GraphicsContext g2d) {
        double translateX = (double) gp.getScreenWidth() / 2 - player.getWorldX();
        System.out.println(player.getWorldX());
        double translateY = (double) gp.getScreenHeight() / 2 - player.getWorldY();
        g2d.drawImage(image, translateX, translateY, gp.getWorldScreenWidth(), gp.getWorldScreenHeight());
    }
}