package Entity;

import Main.Collision;
import Main.GamePannel;
import Main.KeyHandler;
import Main.Objects.Door;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.NumberUp;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePannel gamePannel;
    KeyHandler keyHandler;
    BufferedImage img = null;
    public int keys=0;


    public Player(GamePannel gamePannel, KeyHandler keyHandler) {
        this.gamePannel = gamePannel;
        this.keyHandler = keyHandler;
        Xcord =100;
        Ycord =100;
        speed =4;
        getPlayerImage();
        name = "Player";

    }
    public void getPlayerImage(){
        try{
            def = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/marioDef.png")));
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/marioUp.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/marioDown.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/marioLeft.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/marioRight.png")));
            hit = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/marioHit.png")));

            setDefaultImage();
        }catch (IOException | NullPointerException e){
            System.out.println("Error!" + e.getMessage());
        }
    }

    public void setDefaultImage(){
        img = def;
    }

    public void setHitImage(){
        img = hit;
    }
    public void update(){

        if (keyHandler.GoUp){
            direction = "up";
            img = up;
        }
        if (keyHandler.GoDown) {
            direction = "down";
            img = down;
        }
        if (keyHandler.GoLeft) {
            direction = "left";
            img = left;
        }
        if (keyHandler.GoRight) {
            direction = "right";
            img = right;
        }
        collison = false;

        gamePannel.collisionCheck.checkTile(this);
        int i = gamePannel.collisionCheck.checkObject(this);
        pickObject(i);
        boolean monsterHit = gamePannel.collisionCheck.checkMonster();

        if (!collison && direction != null){
            switch (direction){
                case "up":
                    Ycord -= speed;
                    break;
                case "down":
                    Ycord += speed;
                    break;
                case "left":
                    Xcord -= speed;
                    break;
                case "right":
                    Xcord += speed;
                    break;
            }
            direction = null;
        }

        if(monsterHit){
            gamePannel.restartGame();
        }


    }

    public void draw(Graphics2D gg){
        gg.drawImage(img , Xcord,Ycord,gamePannel.tileSize,gamePannel.tileSize,null);
    }

    public void pickObject(int i){
        if (i != 999){
            String objectName = gamePannel.Objects[i].name;
            switch (objectName){
                case "Key1":
                    gamePannel.Objects[i] = null;
                    gamePannel.sound(1);
                    keys++;
                    break;
                case"Key2":
                    gamePannel.Objects[i] = null;
                    gamePannel.sound(1);
                    keys++;
                    break;
                case "Door1":
                    Door door = (Door)gamePannel.Objects[i];
                    door.doorOpen();
                    gamePannel.sound(3);
                    break;
                case "Door3":
                    if (keys >0){
                        gamePannel.Objects[i] = null;
                        gamePannel.sound(2);
                        keys--;
                    }
                case "Door4":
                    if (keys >0){
                        gamePannel.stopMusic();
                        gamePannel.Objects[i] = null;
                        gamePannel.sound(2);
                        keys--;
                        gamePannel.stopGame();
                    }


            }
        }
    }

}
