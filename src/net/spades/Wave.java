package net.spades;

import java.awt.*;

/**
 * Created by Станислав on 11.10.2015.
 */
public class Wave {
    //fields
    private int waveNumber;
    private long waveTimer;
    private long waveDelay;
    private long waveTimerDiff;
    private String waveText;
    private int waveMultiplier;
    //constructor
    public Wave(){
        waveTimer =0;
        waveDelay =5000;
        waveTimerDiff=0;
        waveText = "WAVE - ";
        waveNumber =1;
        waveMultiplier =5;
    }
    //functions
    public void createEnimes(){
        int enemyCount = waveMultiplier*waveNumber;
        if (waveNumber<4){
            while (enemyCount>0){
                int type =1;
                int rank =1;
                GamePanel.enemies.add(new Enemy(type,rank));
                enemyCount -= type*rank;
            }

        }waveNumber++;
    }
    public void update(){
        if (GamePanel.enemies.size() ==0 && waveTimer==0 ){
            waveTimer = System.nanoTime();
        }
        if (waveTimer>0){
            waveTimerDiff += (System.nanoTime()- waveTimer)/1000000;
            waveTimer = System.nanoTime();
        }
        if (waveTimerDiff > waveDelay){
            createEnimes();
            waveTimer =0;
            waveTimerDiff =0;
        }
    }
    public boolean showWave(){return waveTimer!=0;}
    public void draw(Graphics2D g){
        double divider = waveDelay/180;
        double alpha = waveTimerDiff/divider;
        alpha = 255*Math.sin(Math.toRadians(alpha));
        if (alpha<0) alpha =0;
        if (alpha>255) alpha=255;
        g.setFont(new Font("consolas",Font.PLAIN,20));
        g.setColor(new Color(255, 255, 255, (int) alpha));
        String s = waveText + waveNumber;
        int length = (int) g.getFontMetrics().getStringBounds(s,g).getWidth();
        g.drawString(s,GamePanel.width/2 - length/2,GamePanel.height/2);
    }
}
