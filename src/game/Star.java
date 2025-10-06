package game;

import city.cs.engine.*;
import city.cs.engine.CollisionListener;

import javax.swing.*;
import java.util.Random;

/** projectile which shoots from the player
 */

public class Star extends DynamicBody implements CollisionListener {

    //this detects collisions between the enemies and the projectiles and then decreases the enemy's health by a random value within the range 5-20
    private static final int minDamage = 5;
    private static final int maxDamage = 20;
    private Random random;

    /** initialises a star projectile which after a delay of 600 milliseconds will delete
     * @param w reference to the world it is initialised in
     * @param s reference to the shape the star projectile will take
     */
    public Star(World w, Shape s) {
        super(w, s);
        addCollisionListener(this);
        random = new Random();
        //this will then delete the star after 600 milliseconds to make sure they aren't clogging up space and making the level look messy
        Timer timer = new Timer(600, a-> {
            this.destroy();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /** checks for collisions with an enemy and if there is a collision, it will do a random amount of damage between 5 and 20
     */
    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Enemy){
            Enemy enemy = (Enemy) e.getOtherBody();

            int damage = random.nextInt(maxDamage-minDamage) + minDamage;
            enemy.takeDamage(damage);

            this.destroy();
        }
    }
}
