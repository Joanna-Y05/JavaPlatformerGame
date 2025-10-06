package game;
import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** creates a mouse handler to access mouse inputs on screen
 */
public class MouseHandler implements MouseListener {

    //this class handles mouse input and enables to player to shoot projectiles to defeat enemies

    private GameWorld world;
    private GameView view;
    private Player player;
    private Vec2 direction;
    private float movementSpeed = 30f;

    /** initialises a mouse handler
     * @param w sets the game world
     * @param v sets the game view
     * @param player sets the player
     */
    public MouseHandler(GameWorld w, GameView v, Player player) {
       this.player = player;
        this.world = w;
        this.view = v;
        //this is the constructor method
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    /** when mouse is clicked shoots a star projectle from the player towards that point in the direction of wherever the mouse was clicked
     */
    @Override
    public void mousePressed(MouseEvent e) {

        //when the mouse is pressed, this method is ran to get the mouse position and shoot a projectile from the player towards the point
        Vec2 playerPos = player.getPosition();
        player.setCurrentState(Player.PlayerState.Attacking);
        float spawnOffset = 2f;

        Point mousePoint = e.getPoint();
        Vec2 worldPoint = view.viewToWorld(mousePoint);

        float spawnX = playerPos.x + (worldPoint.x > playerPos.x ? spawnOffset : -spawnOffset);
        Vec2 spawnPos = new Vec2(spawnX, playerPos.y);

        Shape CircleShape = new CircleShape(0.5f);
        Star star = new Star(world, CircleShape);
        star.addImage(new BodyImage("data/Star.gif",2f)).getBodyImage();

        star.setPosition(spawnPos);
        star.setAngleDegrees(0);
        star.setAngularVelocity(0);
        star.setGravityScale(1);

        float directionX = (worldPoint.x > playerPos.x) ? movementSpeed : -movementSpeed;

        star.setLinearVelocity(new Vec2(directionX, 0));


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
