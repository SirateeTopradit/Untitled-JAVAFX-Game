import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class GamePanal extends JPanel implements Runnable{

    final int originalTileSize = 16;//16*16
    final int scale = 3;

    final int tileSize = originalTileSize * scale;//48*48
    final int MaxScreenCol = 16;
    final int MaxScreenRow = 12;
    final int screenWidth = tileSize * MaxScreenCol;
    final int screenHeight = tileSize * MaxScreenRow;
    KeyHandler keyH = new KeyHandler();

    Thread gameThread;
    //set player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanal() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/60.0;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(true){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        if(keyH.upPressed){
            playerY -= playerSpeed;
        }
        if(keyH.downPressed){
            playerY += playerSpeed;
        }
        if(keyH.leftPressed){
            playerX -= playerSpeed;
        }
        if(keyH.rightPressed){
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillRect(playerX,playerY,tileSize,tileSize);
        g2d.dispose();
    }
}
