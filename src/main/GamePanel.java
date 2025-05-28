package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import Entity.Player;
import Map.FarmMap;
import Map.Ocean;
import Map.ForestRiver;
import Map.MountainLake;
import TimeSeasonWeather.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
   final int initTileSize = 16;
   final int scale = 3;
   final public int tileSize = initTileSize*scale;
   final public int w = 16;
   final public int h = 12;
   final public int screenWidth = w*tileSize; //16*16*3=768
   final public int screenHeight =h*tileSize; // 16*12*3 = 572
   final int FPS = 60;
   FarmMap tileM = new FarmMap(this);
   KeyHandler keyH = new KeyHandler(this);
   public UI ui = new UI(this);
   public Menu menu = new Menu(this);
   public Time time = new Time(06, 00);
   public EnvironmentStatus environmentStatus = new EnvironmentStatus(time);
   Thread gameThread;
   public CollisionChecker cChecker = new CollisionChecker(this);
   public Player player = new Player(this, keyH);
   //default pos
   int x = 100;
   int y = 100;
   int speed = 5;
   //world setting
   final public int worldwh = 32;
   public final int worldWidth = worldwh*tileSize;
   public final int worldHeight = worldwh*tileSize;
   //state
   public int gameState;
   public final int titleState = 0;
   public final int inventoryState = 1;
   public final int playState = 2;
   public final int dialogueState = 3; 
   public final int pauseState = 4;

   public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
    setupGame();
   }

   public void setupGame(){
    gameState = titleState;
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
    if(gameState==playState){
        player.update();
    }
   }
   public void paintComponent(Graphics g){
    Graphics2D comp = (Graphics2D) g;
    if(gameState==titleState){
        menu.draw(comp);
    }
    else if(gameState==playState){
        super.paintComponent(g);
        tileM.draw(comp);
        player.draw(comp);
        ui.draw(comp);
        comp.dispose();
    }
    else if(gameState==inventoryState){
        ui.draw(comp);
    }
    else if(gameState==dialogueState){
        // Draw dialogue box or other UI elements for dialogue state
        // This can be implemented later
    }
    else if(gameState==pauseState){ 
        ui.draw(comp);
        // Handle other game states if necessary
   }
}
}
