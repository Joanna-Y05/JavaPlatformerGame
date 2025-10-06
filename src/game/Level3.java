package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;
import city.cs.engine.*;


public class Level3 extends GameWorld{

    private float x = 1.5f,y = -5f;
    private Color transparent = new Color (0,0,0,0);

    public Level3(GifPlayer gifPlayer, Game game) {
        super(gifPlayer, game);
        populateWorld();
    }
    @Override
    public void populateWorld() {
        //boundaries

        Shape bottomBoundary = new BoxShape(100f,0.5f);
        Boundary bottom = new Boundary(this,bottomBoundary,new Vec2(0f,-16f));
        bottom.setFillColor(transparent);
        bottom.setLineColor(transparent);

        //make the ground + platforms

        LongPlatform ground = new LongPlatform(this,new Vec2(0f,-11.5f));
        LongPlatform longPlatform1 = new LongPlatform(this,new Vec2(-27f,-11.5f));
        LongPlatform longPlatform2 = new LongPlatform(this,new Vec2(-12f,-2f));
        Platform platform1 = new Platform(this,new Vec2(0f,2f));
        Platform platform2 = new Platform(this,new Vec2(8f,-2f));
        Platform platform3 = new Platform(this,new Vec2(9f,5f));
        Platform platform4 = new Platform(this,new Vec2(0f,8f));
        LongPlatform longPlatform3 = new LongPlatform(this,new Vec2(-18f,6f));
        LongPlatform longPlatform4 = new LongPlatform(this,new Vec2(-18f,15f));
        Platform platform5 = new Platform(this,new Vec2(9f,11f));
        Platform platform6 = new Platform(this,new Vec2(22f,9f));
        LongPlatform longPlatform5 = new LongPlatform(this,new Vec2(26f,15f));
        Platform platform7 = new Platform(this,new Vec2(52f,9f));
        Platform platform8 = new Platform(this,new Vec2(60f,5f));
        LongPlatform longPlatform6 = new LongPlatform(this,new Vec2(48f,1f));
        Platform platform9 = new Platform(this,new Vec2(31f,-2f));





        // moving platforms
        MovingPlatform right1 = new MovingPlatform(this,new Vec2(17,5f), new Vec2(45f,5f));
        VerticalPlatform up1 = new VerticalPlatform(this,-11.5f,2f, new Vec2(16f,-11.5f));
        VerticalPlatform up2 = new VerticalPlatform(this,6f,17f, new Vec2(-33f,6f));


        // player
        player = new Player(this,null);
        Interaction interaction = new Interaction(player);
        player.setInteraction(interaction);

        player.setPosition(new Vec2(x, y));
        Vec2 pos = player.getPosition();

        //enemies
        Enemy enemy1 = new EnemyBody(this, player,1);
        enemy1.setPosition(new Vec2(-12f,0f));
        Enemy enemy2 = new EnemyBody(this,player,2);
        enemy2.setPosition(new Vec2(-18f,17f));
        Enemy enemy3 = new EnemyBody(this,player,3);
        enemy3.setPosition(new Vec2(26f,17f));
        Enemy enemy4 = new EnemyBody(this,player,4);
        enemy4.setPosition(new Vec2(48f,3f));



        //collectable objects
        Shape Collectible = new BoxShape(0.5f, 0.5f);
        Interactable object = new Interactable(this, Collectible,4,new Vec2(0f,4f),gifPlayer);
        Interactable object2 = new Interactable(this, Collectible,2,new Vec2(-14f,17f),gifPlayer);
        Interactable object3 = new Interactable(this, Collectible,3,new Vec2(52f,11f),gifPlayer);
        Interactable object4 = new Interactable(this, Collectible,1,new Vec2(31f,0f),gifPlayer);
        Interactable Door = new Interactable(this, Collectible,6,new Vec2(-27f,-9.5f),gifPlayer);

        //key for door
        keyItem = new Interactable(this, Collectible,5, new Vec2(22f,11f), gifPlayer);
        keyItem.setFillColor(transparent);
        keyItem.setLineColor(transparent);
        keyItem.setVisible(false, "data/keyspin.gif");
        //unlocked door
        if(player.items >= 5){
            Door.addImage(new BodyImage("data/unlockedDoor.png",3f)).getBodyImage();
        }




        //3. make a view to look into the game world


        player.setGravityScale(1.2f);
    }
}
