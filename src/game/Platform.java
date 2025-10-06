package game;

import city.cs.engine.StaticBody;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {


    public Platform(World world, Vec2 Position) {
        super(world,new BoxShape(3.5f,0.5f));
        this.setPosition(Position);
        this.addImage(new BodyImage("data/platform.png",6f)).getBodyImage();
    }
}
