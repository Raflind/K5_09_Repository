package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
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
        if(gp.gameState==gp.titleState){
            if(code == KeyEvent.VK_W){
                if (gp.menu.commandNum>0){
                    gp.menu.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S){
                if (gp.menu.commandNum<2){
                    gp.menu.commandNum++;
                }
            }
            if(code==KeyEvent.VK_ENTER){
                if(gp.menu.commandNum==0){
                    gp.gameState=gp.playState;
                }
                if(gp.menu.commandNum==1){
                    gp.gameState=gp.playState;
                }
                if(gp.menu.commandNum==2){
                    System.exit(0);
                }
            }
        }
        if(gp.gameState==gp.playState){
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
