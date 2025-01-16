package Main;

import java.awt.*;

public class UI {
    GamePannel gamePannel;
    Font arial_40,arial_80B;
    public String message;
    public boolean gameFinished;

    public  UI(GamePannel gamePannel){
        this.gamePannel = gamePannel;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
    }

    public void drawGameOver(Graphics2D gg){
        if(gamePannel.gameOver){
            gg.setFont(arial_40);
            gg.setColor(Color.WHITE);

            String text;
            int x,y,textLength;
            text = "Congratulations!";
            textLength = (int)gg.getFontMetrics().getStringBounds(text,gg).getWidth();
            x = gamePannel.screenWidth/2 - textLength/2;
            y = gamePannel.screenHeight/2 - (gamePannel.tileSize) * 2;
            gg.drawString(text,x,y);


            gg.setFont(arial_40);
            gg.setColor(Color.CYAN);
            String text2;
            int x2,y2,textLength2;
            text2 = "You Won!";
            textLength2 = (int)gg.getFontMetrics().getStringBounds(text2,gg).getWidth();
            x2 = gamePannel.screenWidth/2 - textLength2/2;
            y2 = (gamePannel.screenHeight + gamePannel.tileSize *2)/2 - (gamePannel.tileSize) * 2;
            gg.drawString(text2,x2,y2);
        }
    }

    public void drawPause(Graphics2D gg){

    }
}
