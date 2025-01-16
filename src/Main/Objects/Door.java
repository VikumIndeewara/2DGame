package Main.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends Parent{

    public Door(int no,int Xcord,int Ycord){
        name = "Door" + no;
        ObjXcord = Xcord;
        ObjYcord = Ycord;

        try {
            if(name.equals("Door1")){
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/ObjectSkins/door.png")));
            } else if (name.equals("Door2")) {
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/ObjectSkins/door1.png")));
            }else if (name.equals("Door3")){
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/ObjectSkins/door3.png")));
            }else if (name.equals("Door4")){
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/ObjectSkins/door3.png")));
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void doorOpen()  {
        try{
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/ObjectSkins/openDoor.png")));

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
