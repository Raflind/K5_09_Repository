package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            if(gp.menu.titleScreenState == 0){
                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                    if (gp.menu.commandNum > 0){
                        gp.menu.commandNum--;
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                    if (gp.menu.commandNum < 2){
                        gp.menu.commandNum++;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.menu.commandNum == 0){
                        gp.menu.titleScreenState = 1;
                        gp.menu.mapSelectionNum = 0;
                    }
                    if(gp.menu.commandNum == 1){
                        System.out.println("Anda memilih CREDITS.");
                    }
                    if(gp.menu.commandNum == 2){
                        System.exit(0);
                    }
                }
            }
            else if(gp.menu.titleScreenState == 1){
                int totalMaps = gp.menu.mapList.size();
                int mapsPerRow = 5;
                int totalOptions = totalMaps + 1;

                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                    if (gp.menu.mapSelectionNum < totalMaps) {
                        if (gp.menu.mapSelectionNum >= mapsPerRow) {
                            gp.menu.mapSelectionNum -= mapsPerRow;
                        } else {
                            gp.menu.mapSelectionNum = totalMaps;
                        }
                    } else {
                        gp.menu.mapSelectionNum = totalMaps - 1;
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                    if (gp.menu.mapSelectionNum < totalMaps) {
                        if (gp.menu.mapSelectionNum < mapsPerRow) {
                            if (gp.menu.mapSelectionNum + mapsPerRow < totalMaps) {
                                gp.menu.mapSelectionNum += mapsPerRow;
                            } else {
                                gp.menu.mapSelectionNum = totalMaps;
                            }
                        } else {
                            gp.menu.mapSelectionNum = totalMaps;
                        }
                    } else {
                        gp.menu.mapSelectionNum = 0;
                    }
                }
                if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                    if (gp.menu.mapSelectionNum > 0 && gp.menu.mapSelectionNum <= totalMaps){
                        gp.menu.mapSelectionNum--;
                    } else if (gp.menu.mapSelectionNum == 0) {
                         gp.menu.mapSelectionNum = totalMaps;
                    } else if (gp.menu.mapSelectionNum == totalMaps) {
                        gp.menu.mapSelectionNum = totalMaps - 1;
                    }
                }
                if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                    if (gp.menu.mapSelectionNum < totalMaps){
                        gp.menu.mapSelectionNum++;
                    } else if (gp.menu.mapSelectionNum == totalMaps) {
                        gp.menu.mapSelectionNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.menu.mapSelectionNum < totalMaps){
                        System.out.println("Map dipilih: " + gp.menu.mapList.get(gp.menu.mapSelectionNum).name);
                        gp.gameState = gp.playState;
                    } else if (gp.menu.mapSelectionNum == totalMaps){
                        gp.menu.titleScreenState = 0;
                        gp.menu.commandNum = 0;
                    }
                }
            }
        }
        
        if(gp.gameState == gp.playState){
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
                gp.gameState = gp.titleState;
                gp.menu.titleScreenState = 0;
                gp.menu.commandNum = 0;
            }
        }
        if(gp.gameState == gp.dialogueState){
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
}