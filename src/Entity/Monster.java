package Entity;

import Main.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Monster extends Entity{
    GamePannel gamePannel;
    BufferedImage img = null;
    int actionCounter;



    public Monster(GamePannel gamePannel) {
        this.gamePannel = gamePannel;
        name = "Monster";
        speed = 5;
        Xcord = 8 * gamePannel.tileSize;
        Ycord = 10 * gamePannel.tileSize;
        actionCounter = 0;
        direction = "up";

        getPlayerImage();

    }
    public void getPlayerImage(){
        try{
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/Monster/mons2.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/Monster/mons1.png")));
            img = up;

        }catch (IOException | NullPointerException e){
            System.out.println("Error!" + e.getMessage());
        }
    }

    public void update(){
        setAction();
        collison = false;
        gamePannel.collisionCheck.checkTile(this);
        gamePannel.collisionCheck.checkMonster();
        if (Objects.equals(direction, "up") && !collison){
            img = up;
            Ycord -= speed;
        } else if (Objects.equals(direction, "down") && !collison) {
            img = down;
            Ycord += speed;
        }
    }

    public void draw(Graphics2D gg){
        gg.drawImage(img , Xcord,Ycord,gamePannel.tileSize,gamePannel.tileSize,null);
    }

    public void setAction() {
        actionCounter++;
        if (actionCounter >= 60 && direction != null) {
            if (direction.equals("up")) {
                direction = "down";
            } else {
                direction = "up";
            }
            actionCounter = 0;
        }
    }



}
