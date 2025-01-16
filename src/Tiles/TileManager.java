package Tiles;

import Main.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class TileManager {
    GamePannel gamePannel;
    public Tile[] tiles;
    public int[][] map;

    public TileManager(GamePannel gamePannel){
        this.gamePannel = gamePannel;
        tiles = new Tile[10];
        map = new int[13][17];

        getTileImage();
        loadMap("C:/Users/User/Documents/CSAssignment/2DGame/src/Main/Resources/map/map.txt");
    }

    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/TileSkins/5.png")));

            tiles[1] = new Tile();
            tiles[1].img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/TileSkins/18.png")));

            tiles[2] = new Tile();
            tiles[2].img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/Resources/TileSkins/19.png")));
            tiles[2].collision =true;


        }catch (IOException | NullPointerException e){
            System.out.println("Error!" + e.getMessage());
        }
    }

    public void loadMap(String mapPath){
        try (BufferedReader br = new BufferedReader(new FileReader(mapPath))){
            int col =0;
            int row =0;
            String line;

            while ((line = br.readLine()) != null){
                String[] nums = line.split(" ");
                for (String num : nums){
                    int no = Integer.parseInt(num);
                    map[row][col] = no;
                    col++;
                }
                row++;
                col=0;
            }

        }catch (IOException e){
            System.out.println("File not found");
        }
    }

    public void draw(Graphics2D gg) {
        int x = 0;
        int y = 0;

        for(int row = 0;row< gamePannel.maxScreenRow;row++){
            for(int col = 0;col< gamePannel.maxScreenCol;col++){
                int num= map[row][col];
                gg.drawImage(tiles[num].img, x, y, gamePannel.tileSize, gamePannel.tileSize, null);
                x += gamePannel.tileSize;
            }
            y += gamePannel.tileSize;
            x=0;
        }
    }

}
