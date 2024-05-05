package main;

import entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import entity.Player;
import javafx.scene.paint.Color;
import map.MapManager;
import utils.Goto;
import weapon.Weapon;
/**
 * GamePanel class is the main class for the game. It extends Canvas and implements Runnable.
 * It handles the game loop, updates and draws the game entities, and manages the game state.
 */
public class GamePanel extends Canvas implements Runnable {
    //Screen settings
    private static GamePanel instance;
    final int tileSize = 20;
    final int aspectRatioWidth = 16;
    final int aspectRatioHeight = 9;
    final int scale = 4;//6
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
    final double DRAW_INTERVAL = 1000000000.0 / 60.0;
    final long ONE_SECOND = 1000000000L;
    CollisionChecker collisionChecker = new CollisionChecker(this);
    AssetSetter assetSetter = new AssetSetter(this);
    private long  score = 0;
    int nowStatus = 0;
    final int monster_type = 6;

    private Entity[] monster = new Entity[8];
    private Entity[] entity;
    private Weapon[] weapons = new Weapon[3];
    private int killCount = 0;
    private int stage = 1;
    private Boolean isNewStage = true;
    private double delta = 0;
    private long lastTime = System.nanoTime();
    private long currentTime;
    private long timer = 0;
    private int drawCount = 0;
    private int drawCountForDisplay = 0;
    BackgroundSound backgroundMusic = new BackgroundSound();
    BackgroundSound soundEffect = new BackgroundSound();
    /**
     * Singleton pattern is used to ensure only one instance of GamePanel is created.
     *
     * @return the single instance of GamePanel
     */
    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }
    /**
     * Combines the monster and player arrays into a single entity array.
     */
    public void combineArrays() {
        entity = new Entity[monster.length + 1];
        System.arraycopy(monster, 0, entity, 0, monster.length);
        entity[entity.length - 1] = player;
    }

    /**
     * Constructor for the GamePanel class.
     * Initializes the GamePanel with the game settings and sets up the key handlers.
     */
    public GamePanel() {
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setFocusTraversable(true);
        this.setOnKeyPressed(e -> keyH.keyPressed(e));
        this.setOnKeyReleased(e -> keyH.keyReleased(e));
    }
    /**
     * Sets up the game by setting the monsters and weapons and playing the music.
     */
    public void setUpGame() {
        assetSetter.setMonster();
        assetSetter.setWeapon();
        combineArrays();
        playMusic(0);
    }
    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    /**
     * Stops the game thread.
     */
    public void stopGameThread() {
        if (gameThread == null) return;
        gameThread.interrupt();
        gameThread = null;
    }
    /**
     * The main game loop. Updates and draws the game entities at a fixed interval.
     */
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / DRAW_INTERVAL;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1) {
                int finalDrawCountForDisplay = drawCountForDisplay;
                javafx.application.Platform.runLater(() -> {
                    updateAndDrawGame(finalDrawCountForDisplay);
                });
                delta--;
                drawCount++;
            }
            if(timer >= ONE_SECOND){
                drawCountForDisplay = drawCount;
                drawCount = 0;
                timer = 0;
            }
            if (Thread.currentThread().isInterrupted()) break;
        }
    }
    /**
     * Updates and draws the game entities.
     *
     * @param finalDrawCountForDisplay the final draw count for display
     */
    private void updateAndDrawGame(int finalDrawCountForDisplay) {
        GraphicsContext gc = this.getGraphicsContext2D();
        if(player.getHp() <= 0) {
            handleGameOver();
        } else {
            updateAndDrawEntities(gc, finalDrawCountForDisplay);
        }
    }
    /**
     * Handles the game over state.
     */
    private void handleGameOver() {
        soundEffect.stop();
        backgroundMusic.stop();
        cancelAndClearWeapons();
        Goto.gameOver(getScore(),getKillCount());
        stopGameThread();
    }
    /**
     * Cancels and clears the weapons.
     */
    private void cancelAndClearWeapons() {
        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] != null){
                weapons[i].getTimer().cancel();
                weapons[i] = null;
            }
        }
    }
    /**
     * Updates and draws the entities.
     *
     * @param gc the GraphicsContext to draw on
     * @param finalDrawCountForDisplay the final draw count for display
     */
    private void updateAndDrawEntities(GraphicsContext gc, int finalDrawCountForDisplay) {
        player.update();
        drawMap(gc);
        updateAndDrawWeapons(gc);
        player.draw(gc);
        updateAndDrawMonsters(gc);
        ui.draw(gc);
        if (debugMode) {
            ui.drawDebugMode(gc, finalDrawCountForDisplay);
        }
    }
    /**
     * Draws the map.
     *
     * @param gc the GraphicsContext to draw on
     */
    private void drawMap(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());
        mapManager.drawMap(gc);
    }
    /**
     * Updates and draws the weapons.
     *
     * @param gc the GraphicsContext to draw on
     */
    private void updateAndDrawWeapons(GraphicsContext gc) {
        for (Weapon value : weapons) {
            if (value != null) {
                value.update();
                value.draw(gc);
            }
        }
    }
    /**
     * Updates and draws the monsters.
     *
     * @param gc the GraphicsContext to draw on
     */
    private void updateAndDrawMonsters(GraphicsContext gc) {
        for (int i = 0; i < monster.length; i++) {
            Entity value = monster[i];
            if (value != null) {
                value.update();
                value.draw(gc);
            } else {
                plusNowStatus();
                assetSetter.addMonster(i, randomMonsterType(), getNowStatus());
            }
        }
    }
    /**
     * Plays the music.
     *
     * @param MusicIndex the index of the music to play
     */
    public void playMusic(int MusicIndex) {
        backgroundMusic.setFile(MusicIndex);
        backgroundMusic.play();
        backgroundMusic.loop();
    }
    /**
     * Plays the sound effect.
     *
     * @param SFXIndex the index of the sound effect to play
     */
    public void playSFX(int SFXIndex) {
        soundEffect.setFile(SFXIndex);
        soundEffect.play();
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

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Weapon[] getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapon[] weapons) {
        this.weapons = weapons;
    }

    public int getNowStatus() {
        return nowStatus;
    }

    public void setNowStatus(int status) {
        this.nowStatus = (status);
    }

    public void plusNowStatus() {
        setKillCount(getKillCount() + 1);
        if (getKillCount() % 5 == 0) {
            setStage(getStage() + 1);
            setNewStage(true);
            checkLevelUp();
            playSFX(4);
        }
        setNowStatus(getNowStatus() + 1);
    }
    public void checkLevelUp() {
        if (getStage() % 4 == 0) {
            weapons[1].updateInterval();
            weapons[2].updateInterval();
        }
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public int getMonsterType() {
        return this.monster_type;
    }
    public int randomMonsterType() {
        return (int) (Math.random() * getMonsterType());
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public Boolean getNewStage() {
        return isNewStage;
    }

    public void setNewStage(Boolean newStage) {
        isNewStage = newStage;
    }
    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }
}