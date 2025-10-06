package game;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import city.cs.engine.*;


/**
 * Your main game entry point
 */
public class Game {

    private JFrame frame;
    private GameWorld world;
    private GameView view;
    private MainMenu menu;
    private GameState gameState;
    private GifPlayer gifPlayer;

    private boolean running = false;
    private SoundClip gameMusic;

    /** Initialise a new Game. */
    public Game() {
        gameState = new GameState();

        try{
            gameMusic = new SoundClip("data/music/titleMusic.wav");
            gameMusic.loop();
        }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e){System.out.println(e);}

        //this section sets the initial panel to the main menu of the game
        frame = new JFrame("City Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);

        menu = new MainMenu(this);
        frame.setContentPane(menu.getMainPanel());
        frame.setVisible(true);


    }

    /** shows the game over screen
     */
    public void showGameOverMenu(){
        //this shows the game over screen where the player can press a button to reset and rechoose a level
        GameOver gameOver = new GameOver(this);
        gameMusic.stop();
        try{
            gameMusic = new SoundClip("data/music/GameOver_music.wav");
            gameMusic.loop();
        }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e){System.out.println(e);}
        frame.getContentPane().removeAll();
        frame.setContentPane(gameOver.getMainPanel());
        frame.revalidate();
        frame.repaint();
        running = false;
    }

    /** shows the level complete screen
     */
    public void showLevelCompleteMenu(){
        //this shows the level complete menu which then allows the player to advance to the next level within the level selection menu
        LevelComplete levelComplete = new LevelComplete(this);
        gameMusic.stop();
        try{
            gameMusic = new SoundClip("data/music/victoryMusic.wav");
            gameMusic.loop();
        }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e){System.out.println(e);}
        frame.getContentPane().removeAll();
        frame.setContentPane(levelComplete.getMainPanel());
        frame.revalidate();
        frame.repaint();
        running = false;
    }
    /** used to access the game world variable
     * @return a game world
     */
    public GameWorld getGameWorld(){
        return world;
    }

    /** sets the level to the one passed in
     * @param nextLevel integer which corresponds to the level wanting to be loaded and then starts the level
     */
    public void setLevel(GameWorld nextLevel){
        //this is used to set the level when a level is selected in the level selection screen
        gameMusic.stop();
        if(this.world != null){
            this.world.cleanUp();
        }
        this.world = nextLevel;

        if(view != null){
            frame.remove(view);
        }
        view = new GameView(nextLevel,1920,1080);
        frame.setContentPane(view);


        Player player = world.getPlayer();

        for(KeyListener k1 :frame.getKeyListeners()){
            frame.removeKeyListener(k1);
        }
        for(MouseListener m1 :frame.getMouseListeners()){
            frame.removeMouseListener(m1);
        }
        try{
            gameMusic = new SoundClip("data/music/levelMusic.wav");
            gameMusic.loop();
        }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e){System.out.println(e);}

        frame.addKeyListener(new KeyMovement(player));
        frame.addKeyListener(player.getInteraction());
        frame.addMouseListener(new MouseHandler(world,view,player));
        nextLevel.start();

        view.setFocusable(true);
        view.requestFocusInWindow();
        frame.requestFocusInWindow();
        frame.revalidate();
        frame.repaint();
        running = true;
    }

    /** used to access game state
     * @return a gamestate
     */
    public GameState getGameState(){
        return gameState;
    }

    /** creates new level based on level passed through and starts it
     * @param level integer which corressponds to the chosen level
     */
    public void startLevel(int level){
        //this now loads and shows the level selected
        gifPlayer = new GifPlayer(frame,this);
        GameWorld newWorld = null;
        switch(level){
            case 1:
                newWorld = new Level1(gifPlayer, this);
                break;
            case 2:
                newWorld = new Level2(gifPlayer, this);
                break;
            case 3:
               newWorld = new Level3(gifPlayer, this);
        }
        setLevel(newWorld);
    }

    /** shows the level selection menu
     */
    public void showLevelSelectionMenu(){
        //this shows the level selection menu after the gif duration has ended
        LevelSelect levelSelect = new LevelSelect(this);
        gameMusic.stop();
        try{
            gameMusic = new SoundClip("data/music/titleMusic.wav");
            gameMusic.loop();
        }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e){System.out.println(e);}

        frame.getContentPane().removeAll();
        frame.setContentPane(levelSelect.getMainPanel());
        frame.revalidate();
        frame.repaint();
        running = false;
    }

    /** shows the cutscene using the gifplayer
     */
    public void showCutscene(){
        //this is shown when the player presses new game
        gifPlayer = new GifPlayer(frame,this);
        gifPlayer.ShowCutscene();
    }


    /** Run the game. */
    public static void main(String[] args) {
//this opens the game upon running the program
        new Game();


    }





}
