package Main.Objects;

import Main.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Parent {

    public BufferedImage img;
    public String name;
    public boolean collision;
    public int ObjXcord,ObjYcord;

    public void draw(Graphics2D gg, GamePannel gamePannel){
        if(name.equals("Door3") || name.equals("Door4")){
            gg.drawImage(img, ObjXcord * gamePannel.tileSize, ObjYcord * gamePannel.tileSize, gamePannel.tileSize, gamePannel.tileSize * 2, null);
        }
        gg.drawImage(img, ObjXcord * gamePannel.tileSize, ObjYcord * gamePannel.tileSize, gamePannel.tileSize , gamePannel.tileSize, null);

    }

}
