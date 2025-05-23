package main;

import Entity.Entity;

public class CollisionChecker {
    GamePanel gp;
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
        switch(entity.direction){
            case "depan":
            entityBRow = (entityBotY+entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLCol][entityBRow];
            tileNum2 = gp.tileM.mapTileNum[entityRCol][entityBRow];
            if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                entity.collisionOn = true;
            }
            break;
            case "blkg":
            entityTRow = (entityTopY-entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLCol][entityTRow];
            tileNum2 = gp.tileM.mapTileNum[entityRCol][entityTRow];
            if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                entity.collisionOn = true;
            }
            break;
            case "kiri":
            entityLCol = (entityLeftX-entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLCol][entityTRow];
            tileNum2 = gp.tileM.mapTileNum[entityLCol][entityBRow];
            if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                entity.collisionOn = true;
            }
            break;
            case "kanan":
            entityRCol = (entityRightX+entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRCol][entityTRow];
            tileNum2 = gp.tileM.mapTileNum[entityRCol][entityBRow];
            if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                entity.collisionOn = true;
            }
            break;
        }
    }
}
