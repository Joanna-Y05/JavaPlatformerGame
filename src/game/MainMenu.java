package game;

import javax.swing.*;
import city.cs.engine.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private JPanel mainPanel;
    private JPanel Menu;
    private JButton Quit;
    private JButton Controls;
    private JButton loadGameButton;
    private JButton newGameButton;

    private Game game;

    public MainMenu(Game game) {
        this.game = game;

        newGameButton.addActionListener(e -> {System.out.println("newGame"); game.showCutscene();});
        // this is used to show the cutscene using the showcutscene method in game which then calls the cutscene using the
        //show cutscene method within gif player

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Loading game...");
                JOptionPane.showMessageDialog(null, "coming soon");
            }
        });

        Controls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Controls!");
                JOptionPane.showMessageDialog(null, "use arrow keys to move and the up key to jump, to shoot enemies click the screen to generate a projectile, use space when near an object to interact ");

            }
        });

        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }



}
