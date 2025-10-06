package game;

import javax.swing.*;
import java.awt.*;

public class LevelComplete {
    private JButton backToLevelSelectionButton;
    private JPanel photo;
    private JPanel mainPanel;

    private Game game;

    public LevelComplete(Game game) {
        this.game = game;

        photo.setLayout(new BoxLayout(photo, BoxLayout.Y_AXIS));

        ImageIcon image = new ImageIcon("data/tableWithFood.gif");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(image.getImage().getScaledInstance(640, 360, Image.SCALE_DEFAULT)));
        imageLabel.setAlignmentX(imageLabel.CENTER_ALIGNMENT);
        photo.add(imageLabel);

        backToLevelSelectionButton.addActionListener(e -> {
            game.showLevelSelectionMenu();
        });
        //this will take the player back to level selection

    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
