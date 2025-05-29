package main;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entity.Player;
import Map.TileManager;

public class KeyHandler implements KeyListener {
    public boolean up, down, left, right;
    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        if(gp.gameState == gp.playState){
          playState(code);  
        }
        if(gp.gameState == gp.inventoryState){
            inventoryState(code);
        }
        if(gp.gameState == gp.dialogueState){
        }
        if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        if(gp.gameState == gp.mapSelectState){
            mapSelectState(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            up = false;
        }
        if(code == KeyEvent.VK_A){
            left = false;
        }
        if(code == KeyEvent.VK_S){
            down = false;
        }
        if(code == KeyEvent.VK_D){
            right = false;
        }
    }
    
    public void inventoryState(int code){
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_UP){
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_DOWN){
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
            }
        }
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_LEFT){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_RIGHT){
            if(gp.ui.slotRow != 3){
                gp.ui.slotRow++;
            }
        }
        if(code == KeyEvent.VK_ESCAPE){
            // gp.gameState = gp.playState;
        }
    }

    public void playState(int code){
        if(code == KeyEvent.VK_W){
            up = true;
        }
        if(code == KeyEvent.VK_A){
            left = true;
        }
        if(code == KeyEvent.VK_S){
            down = true;
        }
        if(code == KeyEvent.VK_D){
            right = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionState; 
        }
        if(code == KeyEvent.VK_I){
            gp.gameState = gp.inventoryState;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_V){
            gp.gameState = gp.mapSelectState;
        }
        if(code == KeyEvent.VK_Y && gp.ui.showVisitHousePrompt) {
            gp.tileM = gp.housePlayerMap;
            gp.player.worldX = 5*gp.tileSize;
            gp.player.worldY = 13*gp.tileSize;
            gp.ui.showVisitHousePrompt = false;
            gp.ui.inHouse = true;
        }
        if(gp.ui.inHouse && code == KeyEvent.VK_X) {
            gp.tileM = gp.farmMap;
            gp.player.worldX = 4*gp.tileSize;
            gp.player.worldY = 8*gp.tileSize;
            gp.ui.inHouse = false;
        }
        if(code == KeyEvent.VK_Z && gp.ui.showSleepPrompt) {
            gp.ui.startSleepScreen();
            gp.ui.showSleepPrompt = false;
        }
    }

    public void titleState(int code){
        if(gp.ui.titleScreenState == 0){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                if (gp.ui.commandNum > 0){
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                if (gp.ui.commandNum < 2){
                    gp.ui.commandNum++;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.ui.titleScreenState = 1;
                    gp.ui.mapSelectionNum = 0;
                }
                if(gp.ui.commandNum == 1){
                    System.out.println("Anda memilih CREDITS.");
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }
        else if(gp.ui.titleScreenState == 1){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
                gp.ui.titleScreenState = 0;
            }
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
    }
    public void mapSelectState(int code){
        int totalMaps = gp.ui.mapList.size();
            int mapsPerRow = 5;
            int totalOptions = totalMaps + 1;

            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                if (gp.ui.mapSelectionNum < totalMaps) {
                    if (gp.ui.mapSelectionNum >= mapsPerRow) {
                        gp.ui.mapSelectionNum -= mapsPerRow;
                    } else {
                        gp.ui.mapSelectionNum = totalMaps;
                    }
                } else {
                    gp.ui.mapSelectionNum = totalMaps - 1;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                if (gp.ui.mapSelectionNum < totalMaps) {
                    if (gp.ui.mapSelectionNum < mapsPerRow) {
                        if (gp.ui.mapSelectionNum + mapsPerRow < totalMaps) {
                            gp.ui.mapSelectionNum += mapsPerRow;
                        } else {
                            gp.ui.mapSelectionNum = totalMaps;
                        }
                    } else {
                        gp.ui.mapSelectionNum = totalMaps;
                    }
                } else {
                    gp.ui.mapSelectionNum = 0;
                }
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                if (gp.ui.mapSelectionNum > 0 && gp.ui.mapSelectionNum <= totalMaps){
                    gp.ui.mapSelectionNum--;
                } else if (gp.ui.mapSelectionNum == 0) {
                        gp.ui.mapSelectionNum = totalMaps;
                } else if (gp.ui.mapSelectionNum == totalMaps) {
                    gp.ui.mapSelectionNum = totalMaps - 1;
                }
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                if (gp.ui.mapSelectionNum < totalMaps){
                    gp.ui.mapSelectionNum++;
                } else if (gp.ui.mapSelectionNum == totalMaps) {
                    gp.ui.mapSelectionNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.mapSelectionNum < totalMaps){
                    System.out.println("Map dipilih: " + gp.ui.mapList.get(gp.ui.mapSelectionNum).name);
                    if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Ocean")) {
                        gp.tileM = gp.oceanMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Forest River")) {
                        gp.tileM = gp.forestMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Mountain Lake")) {
                        gp.tileM = gp.mountainMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Farm")) {
                        gp.tileM = gp.farmMap;
                    } else if(gp.ui.mapList.get(gp.ui.mapSelectionNum).name.equals("Store")) {
                        gp.tileM = gp.storeMap;
                    }
                    gp.player.worldX = gp.tileM.worldX * gp.tileSize;
                    gp.player.worldY = gp.tileM.worldY * gp.tileSize;
                    gp.player.consumeEnergy(10);
                    for(int i = 0; i<15/5; i++){
                        gp.environmentStatus.time.addFiveMinutes(); //nambah 15 minit
                    }
                    gp.gameState = gp.playState;
                } else if (gp.ui.mapSelectionNum == totalMaps){
                    gp.gameState = gp.playState;
                }
            }
    }
}