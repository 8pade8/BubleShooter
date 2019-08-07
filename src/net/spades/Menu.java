package net.spades;

import java.awt.*;

/**
 * Created by Станислав on 11.10.2015.
 */
public class Menu {
    //Fields
    private int buttonWidth;
    private int buttonHeigth;
    private Color color1;
    private String s;
    private int transp =0;
    //Construcrot
    public Menu(){
        buttonWidth = 120;
        buttonHeigth = 60;
        color1 = Color.white;
        s = "PLAY";
    }
    //Functions
    public void update(){
        if (GamePanel.mouseX>GamePanel.width/2-buttonWidth/2
                && GamePanel.mouseX<GamePanel.width/2+buttonWidth/2
                && GamePanel.mouseY > GamePanel.height/2-buttonHeigth/2
                && GamePanel.mouseY < GamePanel.height/2+buttonHeigth/2){
            transp = 60;
            if (GamePanel.leftMouse){GamePanel.state = GamePanel.STATES.PLAY;}
        }
        else transp = 0;
    }
    public void draw(Graphics2D g){
        g.setColor(color1);
        g.setStroke(new BasicStroke(3));
        g.drawRect(GamePanel.width / 2 - buttonWidth / 2, GamePanel.height / 2 - buttonHeigth / 2, buttonWidth, buttonHeigth);

        g.setStroke(new BasicStroke(1));
        g.setFont(new Font("Co", Font.BOLD, 40));
        long length = (int) g.getFontMetrics().getStringBounds(s,g).getWidth();
        g.drawString(s, GamePanel.width / 2 - length / 2, GamePanel.height / 2 + buttonHeigth / 4);

        g.setColor(new Color(255, 255, 255, transp));
        g.fillRect(GamePanel.width / 2 - buttonWidth / 2, GamePanel.height / 2 - buttonHeigth / 2, buttonWidth, buttonHeigth);
    }
}
