package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class EnemyProjectile extends DynamicBody implements CollisionListener {

    private float speed = 30f;
    private Player target;
    private GameWorld world;
    public Color color = Color.white;

    public EnemyProjectile(GameWorld world, Player target, Vec2 startPos) {
        // constructor to make a projectile that shoots towards the player when within the enemy range
        super(world, new CircleShape(0.5f));

        this.target = target;
        this.world = world;

        this.setFillColor(color);
        this.setLineColor(color);

        this.setPosition(startPos);
        this.addCollisionListener(this);

        Vec2 direction = target.getPosition().sub(startPos);
        direction.normalize();
        this.setLinearVelocity(direction.mul(speed));


    }

    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() == target) {
            target.takeDamage(1);
            this.destroy();
        }
    }

}
