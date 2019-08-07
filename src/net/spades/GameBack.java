package net.spades;

import java.awt.*;

/**
 * Created by Станислав on 11.10.2015.
 */
public class GameBack {
    //FIELDS
    private Color color;
    //construcror
    public GameBack(){
        color = Color.blue;
    }
    //function
    public void Update(){}
    public void Draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(0,0,GamePanel.width,GamePanel.height);
    }
}
