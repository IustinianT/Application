import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

    // SCREEN SETTING
    final int originalTileSize = 16; //16x16
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;

    // set player's defeault position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 1;


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered((true));
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){

        gameThread = new Thread();
        gameThread.start();
    }


    public void run(){

        while (gameThread != null){
            update();

            repaint();
        }

    }

    public void update(){
        
        if (keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        if (keyH.downPressed == true){
            playerY += playerSpeed;
        }
        if (keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        if (keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}
