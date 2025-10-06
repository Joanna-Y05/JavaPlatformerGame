package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/** creates a player
 */

public class Player extends Walker implements SensorListener {

    /** handles the states the player can take on based on key inputs
     */
    public enum PlayerState {
        //enum to handle all of the player states available
        Idle,
        Walking_left,
        Walking_right,
        Jumping,
        Attacking,
        Interacting
    }
    private static final float SPEED = 5f;
    private static final float J_SPEED = 6f;
    private boolean canJump = true; //to implement double jump
    private Sensor footSensor;
    private boolean right = true;
    private GameWorld gameWorld;

    public int items=3;

    private int health = 5;

    private Interaction interaction;
    private PlayerState currentState = PlayerState.Idle;

    //player constructor method
    /** initalises a player with a shape, image and foot sensor
     * @param world reference to the game level the player is generated in
     * @param interaction sets an interation to the player to allow it to interact with interactable objects within the game
     */
    public Player (GameWorld world, Interaction interaction) {
        super(world, new BoxShape(0.5f,1f));
        this.gameWorld = world;
        addImage(new BodyImage ("data/idle.gif",4));

        footSensor = new Sensor(this, new BoxShape(0.45f,0.05f, new Vec2(0,-1.05f)));
        footSensor.addSensorListener(this);

        this.interaction = interaction;


    }

    /** sets the state of the player if it is different to the previous state and then adds a new animation if this is true
     * @param newState passed through by the KeyMovement class to show what state the player is in
     */
    public  void setCurrentState(PlayerState newState) {
        //this is manipulated from within the KeyMovement class and depending on the key presses will update the state
        // of the player to show a new character image if the state the player is not the same as the previous
        if(this.currentState != newState){
            this.currentState = newState;
            updatePlayerImage();
        }
    }

    /** updates the player image to reflect the change in the player state
     */
    private void  updatePlayerImage(){
        // this is then used when setting the state to change the image of the player, hence working as an animator
        this.removeAllImages();

        switch (currentState){
            case Idle, Jumping:
                this.addImage(new BodyImage("data/idle.gif",4f));
                break;
            case Walking_left:
                this.addImage(new BodyImage("data/running.gif",4f)).flipHorizontal();
                break;
            case Walking_right:
                this.addImage(new BodyImage("data/running.gif",4f));
                break;
            case Attacking:
                this.addImage(new BodyImage("data/attack.gif",4f));
                break;
            case Interacting:
                this.addImage(new BodyImage("data/interact.gif",4f));
                break;
        }
    }

    /** sets the interaction so the player can interact with interactables
     */
    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

    /** allows the world to access the interaction attached to the player
     *@return returns the interaction
     */
    public Interaction getInteraction() {
        return interaction;
    }

    /** reduces the player health and if the player is at health 0, shows the game over screen
     * @param damage amount of which the player loses health
     */
    public void takeDamage(int damage){
        health -= damage;
        //here add that the change of gif to the red tint one
        if (health < 0) health = 0;
        if(health == 0){
            gameWorld.getGame().showGameOverMenu();
        }
    }

    /** allows access to the health amount of the player
     * @return health amount
     */
    public int getHealth(){
        return health;
    }

    /** increases the health amount of the player
     * @param amount amount by which the player health increases
     */
    public void heal(int amount){
        //here that the change of gif is to the green one
        health += amount;
        if (health > 5) health = 5;
    }

    //these two methods check if the player is on the ground and then sets if they can jump or not
    /** checks if the player is on the ground and sets if the player can jump or not
     */
    @Override
    public void beginContact(SensorEvent e) {
        Body contactBody = e.getContactBody();
        if(contactBody!= null){
            Vec2 contactPosition = contactBody.getPosition();
            Vec2 PlayerPos = this.getPosition();
            System.out.println("Sensor contact with: " + contactBody.getClass().getSimpleName());

            if(contactPosition.y < PlayerPos.y){
                canJump = true;
                System.out.println("can jump is true");
            }
        }
    }

    /** checks if the player is not on the ground and sets it so they cannot jump
     */
    @Override
    public void endContact(SensorEvent e) {
        canJump = false;
    }

    /** allows access to the number of items the player has collected
     * @return number of items collected
     */
    public int getItems(){
        return items;
    }

}
