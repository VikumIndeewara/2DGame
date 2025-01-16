package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import Entity.Monster;
import Entity.Player;
import Main.Objects.Door;
import Main.Objects.Key;
import Main.Objects.Parent;
import Tiles.TileManager;

public class GamePannel extends JPanel implements Runnable {
    final int OriginaltileSize = 16;
    final int scale = 3;

    public final int tileSize = OriginaltileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyHandler = new KeyHandler();


    Thread gameThread;
    public Collision collisionCheck = new Collision(this);
    Player player = new Player(this,keyHandler);
    TileManager tileManager = new TileManager(this);
    public Parent[] Objects = new Parent[10];
    Monster monster = new Monster(this);
    Sounds sounds = new Sounds();
    public boolean gameOver;
    UI ui = new UI(this);

    public GamePannel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(keyHandler);
        this.gameOver = false;


    }

    public void setAssets(){
        Objects = new Parent[10];

        Objects[0] = new Key(1,7,10);
        Objects[1] = new Key(2,2,9);
        Objects[2] = new Door(1,2,7);
        Objects[3] = new Door(2,3,7);
        Objects[4] = new Door(1,10,7);
        Objects[5] = new Door(2,11,7);
        Objects[6] = new Door(3,9,4);
        Objects[7] = new Door(4,15,8);

        playMusic(0);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGame(){
        gameThread = null;
        gameOver = true;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/60;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null){
            update();
            repaint();

            try{
                double remainingTime  = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        player.update();
        monster.update();
    }

    public void restartGame() {
        sound(4);
        player.setHitImage();
        repaint();
        stopMusic();
        // Run the delay in a new thread
        new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            player.Xcord = 100;
            player.Ycord = 100;
            player.setDefaultImage();
            player.keys = 0;
            Objects[0] = new Key(1,7,10);
            Objects[1] = new Key(2,2,9);
            Objects[6] = new Door(3,9,4);
            repaint();
        }).start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gg = (Graphics2D) g;
        tileManager.draw(gg);
        drawObjects(gg);
        monster.draw(gg);
        player.draw(gg);
        if(gameOver){
            ui.drawGameOver(gg);
        }
        gg.dispose();
    }

    public void drawObjects(Graphics2D gg){
        for (Parent object : Objects) {
            if (object != null) {
                object.draw(gg, this);
            }
        }
    }
    public void playMusic(int i){
        sounds.setFile(i);
        sounds.play();
        sounds.loop();
    }
    public void stopMusic(){
        sounds.stop();
    }
    public void sound(int i){
        sounds.setFile(i);
        sounds.play();
    }
}
