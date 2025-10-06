package game;
import city.cs.engine.*;

/** level superclass where the base structure is stored
 */

public class GameWorld extends World{

    //this class is responsible for encapsulating the world itself and is the superclass for the level classes which then override the populate method to display a new level.

    protected GameView view;
    protected Player player;
    protected Interactable keyItem;
    protected GifPlayer gifPlayer;
    protected Game game;


    /** initialises a game world
     * @param gifPlayer reference to gifplayer created in the game class
     * @param game reference to the game created
     */
    public GameWorld( GifPlayer gifPlayer, Game game) {
        super();
        /* this is the constructor that is then called within the 'game' class to populate the world with all the objects
        * when the player decides to start the game post opening menu screen*/
        this.game = game;
        this.gifPlayer = gifPlayer;
        this.view = new GameView(this, 1920, 1080);
        this.addStepListener(new EnemyTracker(this, player));
        view.setGridResolution(1);
        //JFrame debugView = new DebugViewer(this, 1920, 1080);

    }
    /** sets the gifPlayer in the world to be the one from the game class
     * @param gifPlayer reference to the gifPlayer created in game
     */
    public void setGifPlayer(GifPlayer gifPlayer){
        this.gifPlayer = gifPlayer;
    }

    /** allows access to the gifPlayer through reference to the world
     * @return a gifPlayer
     */
    public GifPlayer getGifPlayer(){
        return gifPlayer;
    }

    //this is the overriden method for each level
    /** method to be overwritten by each subclass of game world to add objects in the world
     */
    protected void populateWorld(){}


    /** reveals the key item setting it visible to the player
     */
    public void revealKey(){
        //when the player has 4 items this is called to now show the key
        if(keyItem != null){
            keyItem.setVisible(true, "data/keyspin.gif");
            System.out.println("revealing key: " + (keyItem != null));
        }
    }
    /** allows access to the player through reference to the world
     * @return a player
     */
   public Player getPlayer(){
        //method to allow the game class to access the player
        return player;
   }
    /** allows access to the game through reference to the world
     * @return a game
     */
   public Game getGame(){
        return game;
   }
    /** stops and resets the world
     */
   public void cleanUp(){
        //method to reset the world when the player has a game over screen so they start again
        this.stop();
   }


}
