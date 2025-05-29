package main;

import Entity.Entity;
import Map.Tile;

public class CollisionChecker {
    GamePanel gp;
    public int colTile1, colTile2;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX= entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBotY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityLCol = entityLeftX/gp.tileSize;
        int entityRCol = entityRightX/gp.tileSize;
        int entityTRow = entityTopY/gp.tileSize;
        int entityBRow = entityBotY/gp.tileSize;
        int tileNum1, tileNum2;

        // Get the correct tile array for the current map
        Tile[] tile = gp.tileM.getActiveTileArray();

        switch(entity.direction){
            case "depan":
                entityBRow = (entityBotY+entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLCol][entityBRow];
                tileNum2 = gp.tileM.mapTileNum[entityRCol][entityBRow];
                if(tile[tileNum1].collision || tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                colTile1 = tileNum1;
                colTile2 = tileNum2;
                break;
            case "blkg":
                entityTRow = (entityTopY-entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.tileM.mapTileNum[entityRCol][entityTRow];
                if(tile[tileNum1].collision || tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                colTile1 = tileNum1;
                colTile2 = tileNum2;
                break;
            case "kiri":
                entityLCol = (entityLeftX-entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.tileM.mapTileNum[entityLCol][entityBRow];
                if(tile[tileNum1].collision || tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                colTile1 = tileNum1;
                colTile2 = tileNum2;
                break;
            case "kanan":
                entityRCol = (entityRightX+entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRCol][entityTRow];
                tileNum2 = gp.tileM.mapTileNum[entityRCol][entityBRow];
                if(tile[tileNum1].collision || tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                colTile1 = tileNum1;
                colTile2 = tileNum2;
                break;
        }
    }

}
