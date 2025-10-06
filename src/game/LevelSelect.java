package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelSelect {
    private JPanel mainPanel;
    private JButton level1Button;
    private JButton level2Button;
    private JButton level3Button;

    private Game game;

    public LevelSelect(Game game) {
        this.game = game;

        level1Button.addActionListener(e -> {
            game.startLevel(1);
            // uses the start level function in game to start level 1
        });

        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(game.getGameState().getUnlockedLevels() >= 2){
                    game.startLevel(2);
                    //checks if the player has unlocked level 2 and if they have will start level 2
                }
                else{
                JOptionPane.showMessageDialog(null, "level 2 is locked, complete previous levels!");
            }}
        });

       level3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(game.getGameState().getUnlockedLevels() >= 3){
                    game.startLevel(3);
                    //checks if the player has unlocked level 3 and if they have will start level 3
                }else{
                JOptionPane.showMessageDialog(null, "level 3 is locked, complete previous levels!");

            }}
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }




}

