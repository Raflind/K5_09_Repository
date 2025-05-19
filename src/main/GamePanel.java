package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import Entity.Player;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
   final int initTileSize = 16;
   final int scale = 3;
   final public int tileSize = initTileSize*scale;
   final int screenWidth = 32*tileSize;
   final int screenHeight = 32*tileSize;
   final int FPS = 60;
   KeyHandler keyH = new KeyHandler();
   Thread gameThread;
   Player player = new Player(this, keyH);
   //default pos
   int x = 100;
   int y = 100;
   int speed = 5;

   public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
   }

   public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
   }

   @Override
   public void run(){
    double intervalDraw = 1000000000/FPS;
    double nextDrawTime = System.nanoTime() + intervalDraw;
    while(gameThread != null){
        update(); // update position
        repaint(); //draw layar dgn updated position
        try{
            double remainingTime = (nextDrawTime - System.nanoTime())/1000000; // in milis
            if(remainingTime<0){
                remainingTime = 0;
            }
            Thread.sleep((long) remainingTime);
            nextDrawTime+=intervalDraw;
        }
        catch (InterruptedException e){
            e.printStackTrace();;
        }
    }
   }

   public void update(){
    player.update();
   }
   public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D comp = (Graphics2D) g;
    player.draw(comp);
    comp.dispose();
   }
}
