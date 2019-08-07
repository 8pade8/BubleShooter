package net.spades;

import java.awt.*;

/**
 * Created by Станислав on 11.10.2015.
 */
public class Bullet {
    //fields
    private double x;
    private double y;
    private double bulletDX;
    private double bulletDY;
    private double distX;
    private double distY;
    private double dist;


    private int r;
    private Color color1;
    private int speed;
    // constructor
    public Bullet(){
        x=GamePanel.player.getX();
        y=GamePanel.player.getY();
        r=2;
        color1 = Color.white;
        speed = 10;
        distX = GamePanel.mouseX - x;
        distY = GamePanel.mouseY - y;
        dist = Math.sqrt(distX*distX+distY*distY);
        bulletDX = distX/dist * speed;
        bulletDY = distY/dist * speed;

    }
    //functions
    public int getR(){return r;}
    public double getX(){return x;}
    public double getY(){return y;}
    public boolean remove(){
        if (y<0 || y>GamePanel.height||x<0||x>GamePanel.width) return true;
        return false;
    }
    public void Update(){

        x+=bulletDX;
        y+=bulletDY;
    }
    public void Draw(Graphics2D g){
    g.setColor(color1);
        g.fillOval((int)x,(int)y,r,2*r);
    }
}
