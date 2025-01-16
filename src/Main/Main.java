package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure Time");
        window.setVisible(true);
        GamePannel gamePannel = new GamePannel();
        window.add(gamePannel);
        window.pack();

        gamePannel.requestFocusInWindow();
        gamePannel.setAssets();
        gamePannel.startGameThread();




    }
}