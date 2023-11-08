package main;

import javax.swing.JPanel;
import java.awt.*;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int orginialTitleSize = 16; //16x16
    final int scale = 3;
    public final int tileSize = orginialTitleSize * scale; //48x48 tile
    public int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels

    int FPS = 60;

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    //System
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();

    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];



    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));//set the size of the panel
        this.setBackground(Color.black);//set the background color of the panel
        this.setDoubleBuffered(true); //better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);//allows the panel to receive input
    }

    public void setupGame(){
        assetSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        //this method starts the game thread
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void run(){
        double drawInterval = 1000000000/FPS; //how many nanoseconds between each frame
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta --;
            }
        }

    }
    public void update() {
        player.update();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //Tile
        tileM.draw(g2);

        //Object
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
            }
        //Player
        player.draw(g2);
        //UI
        ui.draw(g2);


        // dispose of the graphics object, this is done to save memory
        g2.dispose();

    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();

    }
    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
