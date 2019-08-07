package net.spades;

import java.awt.*;

/**
 * Created by Станислав on 11.10.2015.
 */
public class Player {
    //fields
    private double x;
    private double y;
    private int r;
    private double dx;
    private double dy;


    private Color color1;
    private Color color2;
    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean isfiring;
    private int speed;
    private double health;

    //constructor
    public Player(){
        x = GamePanel.width / 2;
        y = GamePanel.height / 2;
        r=10;
        speed = 5;
        health =3;
        color1=Color.white;
        up = false;
        down = false;
    left = false;
    right = false;
        isfiring = false;
        dx =0;
        dy=0;
    }

    //functions
    public void hit(){health--;}
    public int getR(){return r;}
    public double getX(){
        return x;
    }
    public  double getY(){return y;}
    public void Update(){
        if (up && y>r) dy = -speed;
        if (down && y< GamePanel.height -r) dy = speed;
        if (left && x>r) dx = -speed;
        if (right && x<GamePanel.width-r) dx =speed;
        if (up&&left || up&&right || down&&left||down&&right) {
            dx = dx*Math.sin(Math.toRadians(45));
            dy = dy*Math.sin(Math.toRadians(45));
        }
        x+=dx;
        y+=dy;
        dx=0;
        dy=0;
        if (isfiring) GamePanel.bullets.add(new Bullet());
    }
    public void Draw(Graphics2D g){
    g.setColor(color1);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(4));
        g.setColor(color1.darker());
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }
}
