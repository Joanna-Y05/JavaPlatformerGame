package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class MovingPlatform extends DynamicBody implements StepListener {

    private float movementSpeed = 2f;
    private Vec2 startPos,endPos;
    private boolean moving = true;
    private Vec2 direction;
    private float initialY;

    public MovingPlatform(GameWorld world, Vec2 startPos, Vec2 endPos) {
        super(world,new BoxShape(3.5f,0.5f));
        this.addImage(new BodyImage("data/movingPlatform.png",2.5f)).getBodyImage();
        this.startPos = startPos;
        this.endPos = endPos;
        this.setPosition(startPos);
        this.setGravityScale(0);
        world.addStepListener(this);
        setAngleDegrees(0);
        setAngularVelocity(0);
        initialY = startPos.y;

        this.direction = new Vec2(movementSpeed,0);
    }

    @Override
    public void preStep(StepEvent e) {
        setAngleDegrees(0);
        setAngularVelocity(0);

        Vec2 fixedPos = new Vec2(getPosition().x, initialY);
        setPosition(fixedPos);
        //this logic not only moves the platform but it ensures the platform stays fixed on its depending axis
        Vec2 currentPos = getPosition();
        if(moving && currentPos.x >= endPos.x){
            moving = false;
            direction = new Vec2(-movementSpeed,0);
        }
        else if(!moving &&currentPos.x <= startPos.x){
            moving = true;
            direction = new Vec2(movementSpeed,0);

        }
        setLinearVelocity(direction);

        //the preStep function acts as a way to constantly keep the platform moving kind of like an update function
    }

    @Override
    public void postStep(StepEvent e) {

    }
}
