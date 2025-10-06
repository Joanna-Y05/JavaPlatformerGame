package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/** class to define enemies
 */
public abstract class Enemy extends Walker implements SensorListener {
    private int health;
    protected float speed;
    protected Player player;
    private int maxHealth;
    private boolean playerInRange = false;



    /** initialises enemy
     * @param world reference to world they are created in
     * @param shape reference to shape of enemy
     * @param health int value of health stat
     * @param speed float value of speed of which they can move
     * @param player reference to the player they target in world
     */
    public Enemy(World world, Shape shape, int health, float speed, Player player) {
        //constructor to create an enemy

        super(world, shape);
        //addImage(new bodyImage(,4));
        this.health = health;
        this.speed = speed;
        this.player = player;
        this.maxHealth = health;

    }

    /** moves the enemy towards to player
     */
    public void moveTowards() {
        //this is the function that moves the enemy towards to player, a future update would be to only allow this within a certain radius
        Vec2 playerPos = player.getPosition();

        Vec2 direction = playerPos.sub(this.getPosition());
        direction.normalize();
        this.setLinearVelocity(direction);
    }

    /** decreases health amount based on damage dealt by player and destroys enemy once health is 0
     * @param damage reference to damage value dealt by player in the attack method in player
     */
    public void takeDamage(int damage) {
        //this function is then called by the projectiles the player launches so that when they hit the enemy, the enemy takes damage
        health -= damage;
        System.out.println("damage took: " + damage);
        if (health <= 0) {
            System.out.println("enemy defeated");
            this.destroy();
            //then if their health hits zero, the enemy is removed from the game world
        }
    }
    /** allows access to health stat used when painting health bar in game view
     * @return int value of health
     */
    public int getHealth() {
        //this is used to update the health bar above the enemy and change depending
        return health;
    }

    /** gets position above enemy body
     * @return a Vec2 position above the enemy
     */
    public Vec2 getHealthBarPosition() {
        //this is what sets the health bar position above the enemies head
        return this.getPosition().add(new Vec2(-2f, 1.5f));
    }

    /** sets if the player is in range of the enemy
     * @param playerInRange boolean which controls wether the player is in range or not
     */
    public void setPlayerInRange(boolean playerInRange) {
        this.playerInRange = playerInRange;
    }
    /** checks if the player is in range
     * @return if player is in range
     */
    public boolean isPlayerInRange() {
        return playerInRange;
    }


    /** attack method to be implemented in enemybody
     */
    public abstract void attack();


}

