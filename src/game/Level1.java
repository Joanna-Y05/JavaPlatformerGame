package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level1 extends GameWorld{
    private float x = 1.5f,y = -5f;
    private Color transparent = new Color (0,0,0,0);

    public Level1(GifPlayer gifPlayer, Game game) {
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
        Platform platform1 = new Platform(this,new Vec2(16f,-8f));
        LongPlatform platform2 = new LongPlatform(this,new Vec2(0f,0f));
        Platform platform3 = new Platform(this,new Vec2(-8f,-6.5f));
        Platform platform4 = new Platform(this,new Vec2(-8f,10f));
        Platform platform5 = new Platform(this,new Vec2(24f,-6f));
        platform5.setAngleDegrees(45f);
        Platform platform6 = new Platform(this,new Vec2(32f,-5f));
        Platform platform7 = new Platform(this,new Vec2(40f,-8f));
        Platform platform8 = new Platform(this,new Vec2(0f,7f));
        platform8.setAngleDegrees(-45f);
        Platform platform9 = new Platform(this,new Vec2(8f,4f));
        Platform platform10 = new Platform(this,new Vec2(28f,4f));
        LongPlatform platform11 = new LongPlatform(this,new Vec2(56f,0f));
        Platform platform12 = new Platform(this,new Vec2(56f,4f));
        Platform platform13 = new Platform(this,new Vec2(46f,8f));
        LongPlatform platform14 = new LongPlatform(this,new Vec2(-36f,-8f));
        Platform platform15 = new Platform(this,new Vec2(-43f,-4f));
        Platform platform16 = new Platform(this,new Vec2(-51f,3f));
        Platform platform17 = new Platform(this,new Vec2(-43f,5f));
        platform17.setAngleDegrees(45f);
        Platform platform18 = new Platform(this,new Vec2(-34f,8f));
        LongPlatform platform19 = new LongPlatform(this,new Vec2(-69f,0f));
        Platform platform20 = new Platform(this,new Vec2(-63f,-8f));
        Platform platform21 = new Platform(this,new Vec2(-73f,-12f));
        Platform platform22 = new Platform(this,new Vec2(-84f,-10f));
        LongPlatform platform23 = new LongPlatform(this,new Vec2(-43f,14f));
        Platform platform24 = new Platform(this,new Vec2(-48f,20f));
        Platform platform25 = new Platform(this,new Vec2(-60f,17f));
        Platform platform26 = new Platform(this,new Vec2(-53f,24f));
        Platform platform27 = new Platform(this,new Vec2(0f,14f));
        Platform platform28 = new Platform(this,new Vec2(0f,20f));
        LongPlatform platform29 = new LongPlatform(this,new Vec2(8f,28f));
        Platform platform30 = new Platform(this,new Vec2(28f,18f));
        Platform platform31 = new Platform(this,new Vec2(36f,22f));
        platform31.setAngleDegrees(45f);
        Platform platform32 = new Platform(this,new Vec2(46f,26f));
        Platform platform33 = new Platform(this,new Vec2(56f,22f));
        platform33.setAngleDegrees(-45f);
        Platform platform34 = new Platform(this,new Vec2(64f,18f));
        Platform platform35 = new Platform(this,new Vec2(14f,19f));



        // moving platforms
        MovingPlatform right1 = new MovingPlatform(this,new Vec2(16f,0f), new Vec2(40f,0f));
        VerticalPlatform up1 = new VerticalPlatform(this,-11.5f,1f, new Vec2(-16f,-11.5f));
        VerticalPlatform up2 = new VerticalPlatform(this,-4f,15f, new Vec2(-24f,-4f));
        VerticalPlatform up3 = new VerticalPlatform(this,-12f,1f, new Vec2(-51f,1f));
        VerticalPlatform up4 = new VerticalPlatform(this,14f,30f, new Vec2(-10f,14f));
        MovingPlatform right2 = new MovingPlatform(this,new Vec2(8f,14f), new Vec2(60f,14f));


        // player
        player = new Player(this,null);
        Interaction interaction = new Interaction(player);
        player.setInteraction(interaction);

        player.setPosition(new Vec2(x, y));
        Vec2 pos = player.getPosition();

        //enemies
        Enemy enemy1 = new EnemyBody(this, player,1);
        enemy1.setPosition(new Vec2(-4f, 1.5f));
        Enemy enemy2 = new EnemyBody(this,player,2);
        enemy2.setPosition(new Vec2(-36f,-7f));
        Enemy enemy3 = new EnemyBody(this,player,3);
        enemy3.setPosition(new Vec2(-69f,1f));
        Enemy enemy4 = new EnemyBody(this,player,4);
        enemy4.setPosition(new Vec2(-43f,15f));
        Enemy enemy5 = new EnemyBody(this,player,1);
        enemy5.setPosition(new Vec2(56f,1f));

        //collectable objects
        Shape Collectible = new BoxShape(0.5f, 0.5f);
        Interactable object = new Interactable(this, Collectible,4,new Vec2(56f,2f),gifPlayer);
        Interactable object2 = new Interactable(this, Collectible,2,new Vec2(-8f,12f),gifPlayer);
        Interactable object3 = new Interactable(this, Collectible,3,new Vec2(0f,22f),gifPlayer);
        Interactable object4 = new Interactable(this, Collectible,1,new Vec2(-51f,5f),gifPlayer);
        Interactable Door = new Interactable(this, Collectible,6,new Vec2(8f,30f),gifPlayer);

        //key for door
        keyItem = new Interactable(this, Collectible,5, new Vec2(0f,-10f), gifPlayer);
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
