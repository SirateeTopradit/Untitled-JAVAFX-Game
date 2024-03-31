package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import entity.Player;
import javafx.scene.paint.Color;

public class GamePanel extends Canvas implements Runnable {

    final int tileSize = 20;
    final int aspectRatioWidth = 14;//16;;
    final int aspectRatioHeight = 7;//9;
    final int scale = 6;
    final int screenWidth = tileSize * aspectRatioWidth * scale;
    final int screenHeight = tileSize * aspectRatioHeight * scale;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    final double DRAW_INTERVAL = 1000000000.0 / 60.0;
    final long ONE_SECOND = 1000000000L;

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

    public int getTileSize() {
        return tileSize;
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
}