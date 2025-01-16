package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sounds {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sounds(){
        soundURL[0] = getClass().getResource("/Main/Resources/Sounds/back.wav");
        soundURL[1] = getClass().getResource("/Main/Resources/Sounds/pick.wav");
        soundURL[2] = getClass().getResource("/Main/Resources/Sounds/door.wav");
        soundURL[3] = getClass().getResource("/Main/Resources/Sounds/door1.wav");
        soundURL[4] = getClass().getResource("/Main/Resources/Sounds/hit.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            System.out.println("Sound error!" + e.getMessage());
        }

    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

}
