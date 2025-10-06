package game;

import city.cs.engine.StaticBody;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/** gameobject which can be placed in a level to see if the player has fallen off world
 */
public class Boundary extends StaticBody implements CollisionListener {

    private GameWorld gameWorld;

    /** initialises a boundary
     * @param w reference to game world it is being created in
     * @param s shape of the boundary
     * @param Position sets the position of the boundary
     */
        public Boundary(GameWorld w, Shape s, Vec2 Position) {
        super(w, s);
        this.gameWorld = w;
        this.addCollisionListener(this);
        this.setPosition(Position);
    }
    /** checks for a collision with the player
     */
    public void collide(CollisionEvent e){
        //if the enemy gets close to the player and touches it, it will decrease health of the player
        if(e.getOtherBody() instanceof Player){
         gameWorld.getGame().showGameOverMenu();
        }
    }
}
