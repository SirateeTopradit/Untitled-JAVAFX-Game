package main;

import entity.Entity;
import entity.Zomby;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import entity.Player;
import javafx.scene.paint.Color;
import map.MapManager;

public class GamePanel extends Canvas implements Runnable {
    //Screen settings
    private static GamePanel instance;
    final int tileSize = 20;
    final int aspectRatioWidth = 16;
    final int aspectRatioHeight = 9;
    final int scale = 5;//6
    final int screenWidth = tileSize * aspectRatioWidth * scale;
    final int screenHeight = tileSize * aspectRatioHeight * scale;

    //World settings
    final int worldScale = 6*2;//6
    final int worldScreenWidth = tileSize * aspectRatioWidth * worldScale;
    final int worldScreenHeight = tileSize * aspectRatioHeight * worldScale;
    private Boolean debugMode = false;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    MapManager mapManager = new MapManager(this, player);
    UI ui = new UI(this);
    BackgroundSound backgroundSound = new BackgroundSound();
    final double DRAW_INTERVAL = 1000000000.0 / 60.0;
    final long ONE_SECOND = 1000000000L;
    CollisionChecker collisionChecker = new CollisionChecker(this);
    AssetSetter assetSetter = new AssetSetter(this);

    private Entity[] monster = new Entity[100];
    private Entity[] entity;
    public void combineArrays() {
        entity = new Entity[monster.length + 1];
        System.arraycopy(monster, 0, entity, 0, monster.length);
        entity[entity.length - 1] = player;
    }



    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public void drawGrid(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        for (int i = 0; i < screenWidth; i += tileSize) {
            for (int j = 0; j < screenHeight; j += tileSize) {
                gc.strokeRect(i, j, tileSize, tileSize);
            }
        }
    }

    public GamePanel() {
        System.out.println("GamePanel created");
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setFocusTraversable(true);
        this.setOnKeyPressed(e -> keyH.keyPressed(e));
        this.setOnKeyReleased(e -> keyH.keyReleased(e));
    }
    public void setUpGame() {
        assetSetter.setMonster();
        combineArrays();
        playMusic(1);
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
        int drawCountForDisplay = 0;
        boolean wasCtrlOPressed = false;

        while(true){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / DRAW_INTERVAL;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1) {
                int finalDrawCountForDisplay = drawCountForDisplay;
                javafx.application.Platform.runLater(() -> {
                    GraphicsContext gc = this.getGraphicsContext2D();
                        player.update();
                        //Map
                        gc.setFill(Color.BLACK);
                        gc.fillRect(0, 0, getWidth(), getHeight());
                        mapManager.drawMap(gc);
                        //Player
                        player.draw(gc);
                        //Monster
                        for (int i = 0; i < monster.length; i++) {
                            if (monster[i] != null) {
                                monster[i].update();
                                monster[i].draw(gc);
                            }
                        }
                        //UI
                        if (debugMode) {
                            ui.drawDebugMode(gc, finalDrawCountForDisplay);
                        }
                });
                delta--;
                drawCount++;


            }
            if(timer >= ONE_SECOND){
                drawCountForDisplay = drawCount;
                drawCount = 0;
                timer = 0;
            }
            if (keyH.isCtrlOPressed()) {
                wasCtrlOPressed = true;
            } else if (!keyH.isCtrlOPressed() && wasCtrlOPressed) {
                debugMode = !debugMode; // Toggle debugMode
                wasCtrlOPressed = false;
            }
        }
    }
    public void playMusic(int MusicIndex) {
        backgroundSound.setFile(MusicIndex);
        backgroundSound.play();
        backgroundSound.loop();
    }
    public void stopMusic() {
        backgroundSound.stop();
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
    public Boolean getDebugMode() {
        return debugMode;
    }
    public Entity[] getMonster() {
        return monster;
    }

    public void setMonster(Entity[] monster) {
        this.monster = monster;
    }
    public Player getPlayer() {
        return player;
    }

    public Entity[] getEntity() {
        return entity;
    }
}