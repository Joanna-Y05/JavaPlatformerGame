package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

/** creates an enemy in the game world
 */
public class EnemyBody extends Enemy implements CollisionListener, SensorListener{

    private Sensor sensor;
    private GameWorld world;
    private Player player;

    /** initialises an enemybody
     * @param world reference to the world it is creating the enemy in
     * @param player reference to the player that it will target and follow
     * @param type sets the visual appearance of the enemy created
     */
    public EnemyBody(GameWorld world,Player player, int type) {
        //constructor method to instantiate an enemy in the game world
        super(world, new BoxShape(0.5f,1f), 100, 300,player);
        this.addCollisionListener(this);

        sensor = new Sensor (this, new CircleShape(8f));
        sensor.addSensorListener(this);
        this.world = world;
        this.player = player;


        if(type == 1){
            this.addImage(new BodyImage("data/redEnemy.gif",2f));

        }
        if(type == 2){
            this.addImage(new BodyImage("data/blueEnemy.gif",2f));

        }
        if(type == 3){
            this.addImage(new BodyImage("data/greenEnemy.gif",2f));

        }
        if(type == 4){
            this.addImage(new BodyImage("data/yellowEnemy.gif",2f));

        }


    }
    /** shoots a project towards the player which is then destroyed after a delay of 800 milliseconds
     */
    public void shootProjectile(){
        //when the player is within the range this is now called to shoot a projectile
        EnemyProjectile projectile = new EnemyProjectile(world, player, this.getPosition());
        Timer timer = new Timer(800, e-> {
            projectile.destroy();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /** decreases player health by 1
     */
    @Override
    public void attack() {
        player.takeDamage(1);
        // method to be implemented for the enemy to attack the player
    }

    /** gets the enemy to attack when in contact of the player
     */
    @Override
    public void collide(CollisionEvent e){
        //if the enemy gets close to the player and touches it, it will decrease health of the player
        if(e.getOtherBody() instanceof Player){
            attack();
        }
    }

    /** senses when the player is within attack range and shoots a projectile at them
     */
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof Player){

            System.out.println("Player in interaction range");
            setPlayerInRange(true);

            Timer timer = new Timer(200, a-> {
                shootProjectile();
            });
            timer.setRepeats(false);
            timer.start();

        }

    }
    //this is what will happen when the player leaves the interaction range
    /** senses when the player leaves attack range
     */
    @Override
    public void endContact(SensorEvent e) {
        if (e.getContactBody() instanceof Player){
            System.out.println("Player left interaction range");
            setPlayerInRange(false);

        }

    }
}
