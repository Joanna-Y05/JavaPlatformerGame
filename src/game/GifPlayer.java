package game;

import javax.swing.*;
import java.awt.*;

/** Used to show gifs for a set duration
 */

public class GifPlayer {
    //this class handles showing gif pop ups and pausing the game to have them show

    private final JLabel gifLabel;
    private final JFrame frame;
    private GameWorld world;
    private Game game;

    /** Used to initialise a gifPlayer in the game class
     * @param frame is a reference to the game frame so that the gifPlayer can be assigned to the pop_up layer of a layered pane
     * @param game is a reference to the game itself so the gifPlayer access the showLevelSelectionMenu() method from within the game class to show the menu after the cutscene plays
     */
    public GifPlayer(JFrame frame, Game game) {

       this.frame = frame;
       this.game = game;

        gifLabel = new JLabel();
        gifLabel.setHorizontalAlignment(JLabel.CENTER);
        gifLabel.setVerticalAlignment(JLabel.CENTER);
        gifLabel.setVisible(false);

        gifLabel.setBounds(0,0,1920,1080);

        frame.getLayeredPane().add(gifLabel, JLayeredPane.POPUP_LAYER);
    }

    /** used to show a gif on the frame
     * @param gifPath relative path to the file within the data folder of where the gif gets it's image from
     * @param duration integer in milliseconds for how long the gifPlayer should show the gif and when to hide it
     * @param world reference to the current level to get it to pause while the gifPlayer is running the gif and then start it again once the duration is over
     */
    public void showGif(String gifPath, int duration, GameWorld world){

        // this will assign the gifLabel with the data path specified and will run it on a timer based on the duration given
        ImageIcon gifIcon = new ImageIcon(gifPath);
        gifLabel.setIcon(gifIcon);

        //ensures the label is centered to the screen
        gifLabel.setBounds(
                (frame.getWidth() - gifIcon.getIconWidth()) / 2,
                (frame.getHeight() - gifIcon.getIconHeight()) / 2,
                gifIcon.getIconWidth(),
                gifIcon.getIconHeight()
        );
        gifLabel.setVisible(true);
        gifLabel.repaint();
        if(world != null){
            //this will stop the game for the duration of the gif
            world.stop();
        }

        Timer timer = new Timer(duration, e-> {
            hideGif();
            if(world != null){
                //this will then start it again after the duration is complete
                world.start();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /** shows a cutscene gif and takes the player to the level selection screen once the duration is over

     */
    public void ShowCutscene(){
        //this method is specifically for handling the cutscene in the game and is called in the menu class through a
        //method in the game class to then show it when new game is pressed
        ImageIcon gifIcon = new ImageIcon("data/cutscene.gif");
        gifLabel.setIcon(new ImageIcon(gifIcon.getImage().getScaledInstance(640, 360, Image.SCALE_DEFAULT)));
        // i had to use a scaled image or the player can't see what's in the cutscene, however this is still a bit blurry but
        // because it is a gif i couldn't get a better quality in a larger size

        gifLabel.setBounds(0,0,1920, 1080);
        gifLabel.setVisible(true);
        gifLabel.repaint();


        Timer timer = new Timer(75000, e-> {
            hideGif();
            game.showLevelSelectionMenu();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /** hides the gif making it not visible to the player on the frame
     */
    public void hideGif(){
        //sets the gif invisible
        gifLabel.setVisible(false);
        gifLabel.setIcon(null);
        frame.repaint();
    }

}
