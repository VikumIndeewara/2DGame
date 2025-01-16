package Main;

import Entity.Entity;
import Main.Objects.Parent;

import java.awt.*;
import java.util.Objects;

public class Collision {
    GamePannel gamePannel;

    public Collision(GamePannel gamePannel){
        this.gamePannel = gamePannel;
    }

    public void checkTile(Entity entity){
        int tileOne,tileTwo;

        if(entity.direction != null){
            switch (entity.direction){
                case "up":
                    int topRow = (entity.Ycord - entity.speed)/ gamePannel.tileSize;
                    if (topRow < 0 || topRow >= gamePannel.maxScreenRow) return;
                    tileOne = gamePannel.tileManager.map[topRow][entity.Xcord/ gamePannel.tileSize];
                    tileTwo = gamePannel.tileManager.map[topRow][(entity.Xcord + gamePannel.tileSize)/ gamePannel.tileSize];
                    if (gamePannel.tileManager.tiles[tileOne].collision || gamePannel.tileManager.tiles[tileTwo].collision){
                        entity.collison = true;
                    }
                    break;
                case "down":
                    int bottomRow = ((entity.Ycord + gamePannel.tileSize) + entity.speed)/ gamePannel.tileSize;
                    if (bottomRow < 0 || bottomRow >= gamePannel.maxScreenRow) return;
                    tileOne = gamePannel.tileManager.map[bottomRow][entity.Xcord/ gamePannel.tileSize];
                    tileTwo = gamePannel.tileManager.map[bottomRow][(entity.Xcord + gamePannel.tileSize)/ gamePannel.tileSize];
                    if (gamePannel.tileManager.tiles[tileOne].collision || gamePannel.tileManager.tiles[tileTwo].collision){
                        entity.collison = true;
                    }
                    break;
                case "left":
                    int leftCol = (entity.Xcord - entity.speed)/ gamePannel.tileSize;
                    if (leftCol < 0 || leftCol >= gamePannel.maxScreenCol) return;
                    tileOne = gamePannel.tileManager.map[entity.Ycord/ gamePannel.tileSize][leftCol];
                    tileTwo = gamePannel.tileManager.map[(entity.Ycord + gamePannel.tileSize)/ gamePannel.tileSize][leftCol];
                    if (gamePannel.tileManager.tiles[tileOne].collision || gamePannel.tileManager.tiles[tileTwo].collision){
                        entity.collison = true;
                    }
                    break;
                case "right":
                    int rightCol = ((entity.Xcord + gamePannel.tileSize) + entity.speed)/ gamePannel.tileSize;
                    if (rightCol < 0  || rightCol >= gamePannel.maxScreenCol) return;
                    tileOne = gamePannel.tileManager.map[entity.Ycord/ gamePannel.tileSize][rightCol];
                    tileTwo = gamePannel.tileManager.map[(entity.Ycord + gamePannel.tileSize)/ gamePannel.tileSize][rightCol];
                    if (gamePannel.tileManager.tiles[tileOne].collision || gamePannel.tileManager.tiles[tileTwo].collision){
                        entity.collison = true;
                    }
                    break;
            }
        }


    }

    public int checkObject(Entity entity){
        int index = 999;
        for (int i = 0; i < gamePannel.Objects.length; i++) {
            if (entity.direction != null && gamePannel.Objects[i] != null){
                if(Objects.equals(gamePannel.Objects[i].name, "Door3") || Objects.equals(gamePannel.Objects[i].name, "Door4")){
                    int playerCol = ((entity.Xcord + gamePannel.tileSize) + entity.speed)/ gamePannel.tileSize;
                    int playerRow = entity.Ycord/ gamePannel.tileSize;
                    int ObjRow = gamePannel.Objects[i].ObjYcord;
                    int ObjCol = gamePannel.Objects[i].ObjXcord;

                    if (playerRow == ObjRow && playerCol == ObjCol && entity.direction.equals("right")){
                        if(Objects.equals(entity.name, "Player")){
                            entity.collison = true;
                            gamePannel.Objects[i].collision = true;
                            index = i;

                        }

                    }
                }
                else {
                    int playerRow = entity.Ycord / gamePannel.tileSize;
                    int playerCol = entity.Xcord / gamePannel.tileSize;
                    int Objcol = (gamePannel.Objects[i].ObjXcord * gamePannel.tileSize + gamePannel.tileSize/2)/ gamePannel.tileSize ;
                    int ObjRow = (gamePannel.Objects[i].ObjYcord  * gamePannel.tileSize - gamePannel.tileSize/2) / gamePannel.tileSize;

                    if (playerRow == ObjRow && playerCol == Objcol){
                        if(Objects.equals(entity.name, "Player")){
                            gamePannel.Objects[i].collision = true;
                            index = i;

                        }

                    }

                }
            }
        }
        return index;

    }

    public boolean checkMonster(){
        boolean hit = false;
        Rectangle monsterRect = new Rectangle(gamePannel.monster.Xcord, gamePannel.monster.Ycord, gamePannel.tileSize, gamePannel.tileSize);
        Rectangle playerRect = new Rectangle(
                gamePannel.player.Xcord,
                gamePannel.player.Ycord,
                gamePannel.tileSize,
                gamePannel.tileSize
        );
        if (monsterRect.intersects(playerRect)){
            hit = true;
            gamePannel.monster.collison = true;
            gamePannel.player.collison = true;
        }
        return hit;
    }

}
