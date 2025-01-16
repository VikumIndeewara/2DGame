package Main.Objects;

import Main.GamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Key extends Parent{
    public Key(int no,int Xcord,int Ycord){
        name = "Key" + no;
        ObjXcord = Xcord;
        ObjYcord = Ycord;
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/ObjectSkins/key.png")));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
