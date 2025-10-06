package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class VerticalPlatform extends DynamicBody implements StepListener {
    private float movementSpeed = 1f;
    private float startPos,endPos;
    private boolean movingUp = true;
    private float initialX;


    public VerticalPlatform(GameWorld world, float startPos, float endPos, Vec2 position) {
        super(world,new BoxShape(3.5f,0.5f));
        this.addImage(new BodyImage("data/movingPlatform.png",2.5f)).getBodyImage();
        this.startPos = startPos;
        this.endPos = endPos;
        this.setPosition(position);
        this.setGravityScale(0);
        world.addStepListener(this);
        setAngleDegrees(0);
        setAngularVelocity(0);
        this.initialX = getPosition().x;

    }

    @Override
    public void preStep(StepEvent e) {
        setAngleDegrees(0);
        setAngularVelocity(0);

        Vec2 fixedPos = new Vec2(initialX, getPosition().y);
        setPosition(fixedPos);
        //this logic not only moves the platform but it ensures the platform stays fixed on its depending axis

        Vec2 currentPos = this.getPosition();
        if(movingUp && currentPos.y >= endPos){
            movingUp = false;
        }
        else if(!movingUp &&currentPos.y <= startPos){
            movingUp = true;
        }
        if (movingUp) {
            this.setLinearVelocity(new Vec2(0,movementSpeed));
        }
        else{
            this.setLinearVelocity(new Vec2(0,-movementSpeed));
        }


        //the preStep function acts as a way to constantly keep the platform movingUp kind of like an update function
    }

    @Override
    public void postStep(StepEvent e) {

    }
}
