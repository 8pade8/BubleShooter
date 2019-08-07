package net.spades;

import java.awt.*;

/**
 * Created by Станислав on 11.10.2015.
 */
public class Enemy {
    //fields
    private double x;
    private double dx;
    private double y;
    private double dy;

    private int r;
    private double speed;

    private Color color;

    private int type;
    private int rank;

    private double health;
    //constructor
    public Enemy(int type,int rank){
        this.type = type;
        this.rank = rank;
        switch (rank){
            case (1) :
                color = Color.green;
                switch (type){
                case (1):
                    x=Math.random()*GamePanel.width;
                    y=0;
                    speed = 2;
                    r = 7;
                    health =2;

                    double angle = Math.toRadians(Math.random()*360);
                    dx = Math.sin(angle)*speed;
                    dy= Math.cos(angle)*speed;
            }
        }

    }
    //function
    public double getX(){return x;}
    public double getY(){return y;}
    public int getR(){return r;}
    public boolean remove(){
        return health <= 0;
    }
    public void hit(){health--;}
    public void Update(){
        x +=dx;
        y+=dy;

        if (x<0&&dx<0) dx=-dx;
        if (x >GamePanel.width&&dx>0) dx=-dx;
        if (y<0&&dy<0) dy=-dy;
        if (y >GamePanel.height&&dy>0) dy=-dy;
    }
    public void Draw(Graphics2D g){
        g.setColor(color);
        g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }
}
