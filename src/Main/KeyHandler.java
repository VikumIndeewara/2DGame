package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean GoUp, GoDown, GoLeft, GoRight;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            GoUp = true;
        } else if (key == KeyEvent.VK_A) {
            GoLeft = true;
        } else if (key == KeyEvent.VK_S) {
            GoDown = true;
        } else if (key == KeyEvent.VK_D) {
            GoRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            GoUp = false;
        } else if (key == KeyEvent.VK_A) {
            GoLeft = false;
        } else if (key == KeyEvent.VK_S) {
            GoDown = false;
        } else if (key == KeyEvent.VK_D) {
            GoRight = false;
        }
    }
}
