package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import entity.Player;
import javafx.scene.paint.Color;
import map.MapManeger;

public class GamePanel extends Canvas implements Runnable {
    //Screen settings
    final int tileSize = 20;
    final int aspectRatioWidth = 16;
    final int aspectRatioHeight = 9;
    final int scale = 5;//6
    final int screenWidth = tileSize * aspectRatioWidth * scale;
    final int screenHeight = tileSize * aspectRatioHeight * scale;

    //World settings
    final int worldScale = 6*4;//6
    final int worldScreenWidth = tileSize * aspectRatioWidth * worldScale;
    final int worldScreenHeight = tileSize * aspectRatioHeight * worldScale;


    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    MapManeger mapManeger = new MapManeger(this, player);

    final double DRAW_INTERVAL = 1000000000.0 / 60.0;
    final long ONE_SECOND = 1000000000L;

    public void drawGrid(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        for (int i = 0; i < screenWidth; i += tileSize) {
            for (int j = 0; j < screenHeight; j += tileSize) {
                gc.strokeRect(i, j, tileSize, tileSize);
            }
        }
    }

    public GamePanel() {
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setFocusTraversable(true);
        this.setOnKeyPressed(e -> keyH.keyPressed(e));
        this.setOnKeyReleased(e -> keyH.keyReleased(e));
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(true){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / DRAW_INTERVAL;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1){
                player.update();
                javafx.application.Platform.runLater(() -> {
                    GraphicsContext gc = this.getGraphicsContext2D();
                    gc.setFill(Color.CORAL);
                    gc.fillRect(0, 0, getWidth(), getHeight());
                    mapManeger.drawMap(gc);
//                    drawGrid(gc);
                    player.draw(gc);
                });
                delta--;
                drawCount++;
            }
            if(timer >= ONE_SECOND){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public int getAspectRatioWidth() {
        return aspectRatioWidth;
    }

    public int getAspectRatioHeight() {
        return aspectRatioHeight;
    }

    public int getScale() {
        return scale;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getTileSize() {
        return tileSize;
    }
    public int getWorldScreenWidth() {
        return worldScreenWidth;
    }

    public int getWorldScreenHeight() {
        return worldScreenHeight;
    }


}