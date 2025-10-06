package game;

import city.cs.engine.*;
import city.cs.engine.Sensor;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;

//this script handles the actual working of the interactable objects
/** interactable object the player can interact with
 */
public class Interactable extends StaticBody implements SensorListener {

    private boolean nearby = false;
    private int type;

    private boolean hasBeenInteracted = false;
    private Sensor itemSensor;
    private String objectName;
    private GifPlayer gifPlayer;
    private String gifPath;
    private int duration;

    //constructor method for the collectible items
    /** initialises an interactble
     * @param world reference to level it is created in
     * @param shape sets the shape of the object
     * @param type int value associated with the type of object it is and what image and gif to display as well as associated behaviour
     * @param position sets the position of the object in the game world
     * @param gifPlayer sets the gifPlayer to allow manipulation within the class
     */
    public Interactable(GameWorld world, Shape shape, int type, Vec2 position, GifPlayer gifPlayer) {
        super(world, shape);
        this.type = type;
        this.gifPlayer = gifPlayer;

        itemSensor = new Sensor (this, shape);
        itemSensor.addSensorListener(this);

        this.setPosition(position);

        //handles sorting which item it is and sets the gif and image to the correct one
        if(type == 1){
            objectName = "cake";
            this.addImage(new BodyImage("data/cake.png",3f)).getBodyImage();
            gifPath = "data/foundCake.gif";
            duration = 6600;

        }
        else if(type == 2){
            objectName = "cookie";
            this.addImage(new BodyImage("data/cookiegif.gif",3f)).getBodyImage();
            gifPath = "data/foundCookies.gif";
            duration = 6600;
        }
        else if(type == 3){
            objectName = "pudding";
            this.addImage(new BodyImage("data/puddinggif.gif",3f)).getBodyImage();
            gifPath = "data/foundPudding.gif";
            duration = 6600;

        }
        else if(type == 4){
            objectName = "candy";
            this.addImage(new BodyImage("data/candygif.gif",3f)).getBodyImage();
            gifPath = "data/foundCandy.gif";
            duration = 6600;
        }
        else if(type == 5){
            objectName = "key";
            this.addImage(new BodyImage("data/keyspin.gif",3f)).getBodyImage();
            this.setVisible(false,"data/keyspin.gif");
            gifPath = "data/doorunlocking.gif";
            duration = 8000;
        }
        else if(type == 6){
            objectName = "door";
            this.addImage(new BodyImage("data/lockedDoor.png",3f)).getBodyImage();
        }
    }

    /** allows the player to interact with an object and controls the behaviour of the interaction
     */
    public void interact(){

        if(hasBeenInteracted) return;
        GameWorld world = (GameWorld) this.getWorld();
        Player player = world.getPlayer();
        if(type == 6){
            //door logic for when there is not enough items and when there is enough items
            if (player.items < 5){
                System.out.println("door locked");
                JOptionPane.showMessageDialog(null, "you haven't unlocked the door, find a key!");
                return;
            }
            else{
                System.out.println("interacting with door");
                hasBeenInteracted = true;
                world.getGame().getGameState().unlockNextLevel();
                world.getGame().showLevelCompleteMenu();
            }
        }
        else{
            hasBeenInteracted = true;
            System.out.println("Interacting");
            if(type != 6){

                player.heal(1);
                player.items++;
                gifPlayer.showGif(gifPath,duration,world);

                if (player.getItems() == 4){
                    world.revealKey();
                }
            }
            this.destroy();
        }

    }
    //this is used by the key item to set visiblity in game so it is not seen by the player until they have the correct number of items
    /** sets visibility of an object by removing or adding image
     * @param visible controls wether you are adding or removing an image
     * @param path reference to the image file in the data folder
     */
    public void setVisible(boolean visible, String path){
        if(visible){
            this.addImage(new BodyImage(path,3f)).getBodyImage();
        }
        else{
            this.removeAllImages();
        }
    }

    /** returns if the player is in the interaction range
     * @return if the player is nearby
     */
    public boolean nearby(){
        return nearby;
    }

    //this is what will run when the player is within the interaction range
    /** checks if the player is within the interaction range and sets the current interactable to be this object
     */
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof Player){
            nearby = true;
            System.out.println("Player in interaction range");

            Player player = (Player) e.getContactBody();
            Interaction interaction  = player.getInteraction();
            interaction.setCurrentInteractable(this);

            if(interaction != null){
                interaction.setCurrentInteractable(this);
            } else{
                System.out.println("error in interaction system");
            }
        }

    }
//this is what will happen when the player leaves the interaction range
    /** checks if the player left the interaction range and sets the current interactable back to null
     */
    @Override
    public void endContact(SensorEvent e) {
        if (e.getContactBody() instanceof Player){
            nearby = false;
            System.out.println("Player left interaction range");

            Player player = (Player) e.getContactBody();
            Interaction interaction  = player.getInteraction();
            interaction.setCurrentInteractable(null);
        }

    }
}
