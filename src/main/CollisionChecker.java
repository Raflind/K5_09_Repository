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

    public boolean checkEntity(Entity entity, Entity target) {
        int solidEntityX = entity.solidArea.x;
        int solidEntityY = entity.solidArea.y;
        int solidTargetX = target.solidArea.x;
        int solidTargetY = target.solidArea.y;
        boolean contactPlayer = false;
        if (target != null) {
            // Get entity's solid area position
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            // Get target's solid area position
            target.solidArea.x = target.worldX + target.solidArea.x;
            target.solidArea.y = target.worldY + target.solidArea.y;

            // Simulasikan pergerakan entity
            switch (entity.direction) {
                case "blkg":
                    entity.solidArea.y -= entity.speed;
                    break;
                case "depan":
                    entity.solidArea.y += entity.speed;
                    break;
                case "kiri":
                    entity.solidArea.x -= entity.speed;
                    break;
                case "kanan":
                    entity.solidArea.x += entity.speed;
                    break;
            }

            if (entity.solidArea.intersects(target.solidArea)) {
                if (target != entity) {
                    entity.collisionOn = true;
                    contactPlayer = true;
                }
            }

            // Reset posisi solid area
            entity.solidArea.x = solidEntityX;
            entity.solidArea.y = solidEntityY;
            target.solidArea.x = solidTargetX;
            target.solidArea.y = solidTargetY;
        }
        return contactPlayer;
    }

     public void checkPlayer(Entity entity) {
        int solidEntityX = entity.solidArea.x;
        int solidEntityY = entity.solidArea.y;
        int solidTargetX = gp.player.solidArea.x;
        int solidTargetY = gp.player.solidArea.y;

        // Get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // Get object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
        entity.solidArea.x = solidEntityX;
         entity.solidArea.y = solidEntityY;
        gp.player.solidArea.x = solidTargetX;
        gp.player.solidArea.y = solidTargetY;
    }
}
