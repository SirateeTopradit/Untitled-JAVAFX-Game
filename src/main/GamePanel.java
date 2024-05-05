package main;

import entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import entity.Player;
import javafx.scene.paint.Color;
import main.MapManager;
import utils.Goto;
import weapon.Weapon;
import java.lang.Runnable;
/**
 * GamePanel class is the main class for the game. It extends Canvas and implements Runnable.
 * It handles the game loop, updates and draws the game entities, and manages the game state.
 */
public class GamePanel extends Canvas implements Runnable {
    //Screen settings
    private static GamePanel instance;
    private final int tileSize = 20;
    private final int aspectRatioWidth = 16;
    private final int aspectRatioHeight = 9;
    private final int scale = 4;//6
    private final int screenWidth = tileSize * aspectRatioWidth * scale;
    private final int screenHeight = tileSize * aspectRatioHeight * scale;

    //World settings
    private final int worldScale = 6*2;//6
    private final KeyHandler keyH = new KeyHandler();
    private Thread gameThread;
    private final Player player = new Player(this, keyH);
    private final MapManager mapManager = new MapManager(this, player);
    private final UI ui = new UI(this);
    private final CollisionChecker collisionChecker = new CollisionChecker(this);
    private final AssetSetter assetSetter = new AssetSetter(this);
    private long  score = 0;
    private int nowStatus = 0;
    private Entity[] monster = new Entity[8];
    private Entity[] entity;
    private Weapon[] weapons = new Weapon[3];
    private int killCount = 0;
    private int stage = 1;
    private Boolean isNewStage = true;
    private double delta = 0;
    private long lastTime = System.nanoTime();
    private long timer = 0;
    private final BackgroundSound backgroundMusic = new BackgroundSound();
    private final BackgroundSound soundEffect = new BackgroundSound();
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
        this.setOnKeyPressed(keyH::keyPressed);
        this.setOnKeyReleased(keyH::keyReleased);
    }
    /**
     * Sets up the game by setting the monsters and weapons and playing the music.
     */
    public void setUpGame() {
        assetSetter.setMonster();
        assetSetter.setWeapon();
        combineArrays();
        int Death_by_Glamour = 0;
        playMusic(Death_by_Glamour);
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
            long currentTime = System.nanoTime();
            double DRAW_INTERVAL = 1000000000.0 / 60.0;
            delta += (currentTime - lastTime) / DRAW_INTERVAL;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1) {
                javafx.application.Platform.runLater(this::updateAndDrawGame);
                delta--;
            }
            long ONE_SECOND = 1000000000L;
            if(timer >= ONE_SECOND){
                timer = 0;
            }
            if (Thread.currentThread().isInterrupted()) break;
        }
    }
    /**
     * Updates and draws the game entities.
     */
    public void updateAndDrawGame() {
        GraphicsContext gc = this.getGraphicsContext2D();
        if(player.getHp() <= 0) {
            handleGameOver();
        } else {
            updateAndDrawEntities(gc);
        }
    }
    /**
     * Handles the game over state.
     */
    public void handleGameOver() {
        soundEffect.stop();
        backgroundMusic.stop();
        cancelAndClearWeapons();
        if (gameThread != null) {
            Goto.gameOver(getScore(),getKillCount());
        }
        stopGameThread();
    }
    /**
     * Cancels and clears the weapons.
     */
    public void cancelAndClearWeapons() {
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
     */
    public void updateAndDrawEntities(GraphicsContext gc) {
        player.update();
        drawMap(gc);
        updateAndDrawWeapons(gc);
        player.draw(gc);
        updateAndDrawMonsters(gc);
        ui.draw(gc);
    }
    /**
     * Draws the map.
     *
     * @param gc the GraphicsContext to draw on
     */
    public void drawMap(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());
        mapManager.drawMap(gc);
    }
    /**
     * Updates and draws the weapons.
     *
     * @param gc the GraphicsContext to draw on
     */
    public void updateAndDrawWeapons(GraphicsContext gc) {
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
    public void updateAndDrawMonsters(GraphicsContext gc) {
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
        return tileSize * aspectRatioWidth * worldScale;
    }
    public int getWorldScreenHeight() {
        return tileSize * aspectRatioHeight * worldScale;
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
        return 6;
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