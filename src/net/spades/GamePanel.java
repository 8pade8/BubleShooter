package net.spades;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by —танислав on 11.10.2015.
 */
public class GamePanel extends JPanel implements Runnable {

    // пременные
    public static int width = 1024;
    public static int height = 768;

    private Thread thread;

    private BufferedImage image;
    private Graphics2D g;
    public static enum STATES{
        MENU,
        PLAY
    }
    public static STATES state = STATES.MENU;

    public static GameBack background;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies ;
    public  static Wave wave;
    public static Menu menu;
    public static int mouseX;
    public static int mouseY;
    public static boolean leftMouse;

    private int FPS;
    private long timerFPS;
    private double millisToFps  ;
    private int sleepTime;




    //constructor
    public GamePanel(){
        super();
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        addKeyListener(new Listeners());
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());

    }

    // Functions
    public void start(){
        thread = new Thread(this);
        thread.start();
    }
    public void run() {

        FPS = 30;
        millisToFps = 1000/FPS;
        sleepTime =0;

        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        g =(Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        wave = new Wave();
        menu = new Menu();
        leftMouse = false;

        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage bufferedImage = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) bufferedImage.getGraphics();
        g3.setColor(new Color(255,255,255));
        g3.drawOval(0, 0, 4, 4);
        g3.drawLine(2, 0, 2, 4);
        g3.drawLine(0, 2, 4, 2);
        Cursor cursor = kit.createCustomCursor(bufferedImage,new Point(3,3),"myCursor");
        g3.dispose();




    while (true){
        this.setCursor(cursor);
        timerFPS = System.nanoTime();
        if (state.equals(STATES.MENU)){
            background.Update();
            menu.update();
            background.Draw(g);
            menu.draw(g);
            gameDraw();

        }
        if (state.equals(STATES.PLAY)){
            gameUpdate();
            gameRender();
            gameDraw();
        }


        timerFPS = (System.nanoTime()-timerFPS)/1000000;
        if(millisToFps>timerFPS){
            sleepTime = (int) (millisToFps -timerFPS);
        }
        else sleepTime = 1;
        try {
            thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timerFPS =0;
        sleepTime =1;
    }
    }
    public void gameUpdate(){
        background.Update();
        player.Update();
        for (int i=0;i<bullets.size();i++){
            bullets.get(i).Update();
            boolean remove = bullets.get(i).remove();
            if (remove) {bullets.remove(i);
            i--;}
        }
        for (Enemy e: enemies) e.Update();

        // попадание
        for (int i=0;i<enemies.size();i++) {
            Enemy e = enemies.get(i);
            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                double dist = Math.sqrt((e.getX()-b.getX())*(e.getX()-b.getX())+(e.getY()-b.getY())*(e.getY()-b.getY()));
                if (dist <= e.getR() + b.getR()) {
                    e.hit();
                    bullets.remove(j);
                    j++;
                    boolean remove = e.remove();
                    if (remove){ enemies.remove(i);i--;break;}}

                }

            }
        // столкновение

        for (int i=0;i<enemies.size();i++){
            Enemy e = enemies.get(i);
            double dist = Math.sqrt((e.getX()-player.getX())*(e.getX()-player.getX())+(e.getY()-player.getY())*(e.getY()-player.getY()));
            if (dist <= player.getR()+e.getR()){e.hit();player.hit();
                boolean remove = e.remove();
                if (remove){ enemies.remove(i);i--;}
        }}
        wave.update();

    }





    public void gameRender(){
        background.Draw(g);
        player.Draw(g);
        for (Bullet b:bullets)b.Draw(g);
        for (Enemy e:enemies) e.Draw(g);
        if (wave.showWave()) wave.draw(g);
    }

    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0,null);
        g2.dispose();
    }
}
