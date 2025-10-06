package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/** tracks enemies initialised in the world and checks if they are within range of the enemy
 */
public class EnemyTracker implements StepListener {

    private World world;
    private Player player;

    /** initialises a enemy tracker
     * @param world reference to game world the tracker is to be created in
     * @param player reference to the player the enemy is to be in range with
     */
    public EnemyTracker(World world, Player player) {
        //this is used to track the enemies in the world
        this.world = world;
        this.player = player;
    }

    /** checks if player is within the range of the enemy and if they are it will move the enemy towards them
     */
    @Override
    public void preStep(StepEvent e) {
        for (DynamicBody body : world.getDynamicBodies()){
            if (body instanceof Enemy enemy){
                //this will check if the player is within the attack range of the enemy and then will move the enemy towards them
                if(enemy.isPlayerInRange()){
                    enemy.moveTowards();

                }
                else{
                    enemy.setLinearVelocity(new Vec2(0,0));
                }
            }
        }
    }

    @Override
    public void postStep(StepEvent e) {

    }
}
