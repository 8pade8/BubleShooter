package net.spades;

import javax.swing.*;

/**
 * Created by Станислав on 11.10.2015.
 */
public class GameStart {
    public static void main(String[] args) {
        GamePanel panel = new GamePanel();
        JFrame startFrame = new JFrame("BubleShooter");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.setLocationRelativeTo(null);
        startFrame.pack();
        startFrame.setVisible(true);
        panel.start();


    }
}
