package game;

import javax.swing.*;

public class GameOver {
    private JPanel GameOver;
    private JPanel mainPanel;
    private JButton retryButton;
    private JButton quitButton;
    private Game game;

    public GameOver(Game game) {
        this.game = game;


        retryButton.addActionListener(e -> {System.out.println("newGame"); game.showLevelSelectionMenu();});
        //when pressed will take the player back to the level selection screen


        quitButton.addActionListener(e -> {System.exit(0);});

    }
    public JPanel getMainPanel() {
        return mainPanel;
    }



}

