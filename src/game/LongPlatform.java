package game;

import city.cs.engine.*;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class LongPlatform extends StaticBody {

    private Color whiteish = new Color(222, 222, 222);
    public LongPlatform(World world, Vec2 Position) {
        super(world,new BoxShape(11f,0.5f));
        this.setPosition(Position);
        //this.addImage(new BodyImage("data/longPlatform.png",2f)).getBodyImage();
        // i couldn't make this image long enough to cover the entire platform so i just changed the colour of the platform
        this.setLineColor(whiteish);
        this.setFillColor(whiteish);
    }
}
