package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_40;
    Font arial_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dF = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        OBJ_Key key = new OBJ_Key();
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);
        keyImage = key.image;

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw (Graphics2D g2){

        if (gameFinished == true){
            String text;
            int textLength;
            int x;
            int y;
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x  = (gp.screenWidth / 2) - textLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize* 3;
            g2.drawString(text, x, y);

            text = "Your time was " + dF.format(playTime) + " seconds";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x  = (gp.screenWidth / 2) - textLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize* 4;
            g2.drawString(text, x, y);

            g2.setFont(arial_80);
            g2.setColor(Color.YELLOW);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x  = (gp.screenWidth / 2) - textLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize* 3;
            g2.drawString(text, x, y);

            gp.gameThread = null;

        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            //Time
            playTime += (double)1/60;

            g2.drawString("Time: " + dF.format(playTime), gp.tileSize * 11, 65);

            // message
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, 25, 110);
                messageCounter++;

                if (messageCounter > 180) {
                    messageOn = false;
                    messageCounter = 0;
                }
            }

            if (gp.player.hasKey == 3) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString("You found all the keys", 25, 150);

                messageCounter++;
                if (messageCounter > 180) {
                    messageOn = false;
                    messageCounter = 0;
                }

            }
        }




    }
}
