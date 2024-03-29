package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //returns the key code associated with the key pressed
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            upPressed = true;
        }
        if(keyCode == KeyEvent.VK_S){
            downPressed = true;
        }
        if(keyCode == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(keyCode == KeyEvent.VK_A){
            leftPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            upPressed = false;
        }
        if(keyCode == KeyEvent.VK_S){
            downPressed = false;
        }
        if(keyCode == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(keyCode == KeyEvent.VK_A){
            leftPressed = false;
        }

    }
}
