package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level2 extends GameWorld{
    private float x = 1.5f,y = -5f;
    private Color transparent = new Color (0,0,0,0);

    public Level2(GifPlayer gifPlayer, Game game) {
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
        Platform platform1 = new Platform(this,new Vec2(-20f,-11.5f));
        Platform platform2 = new Platform(this,new Vec2(-20f,-5f));
        Platform platform3 = new Platform(this,new Vec2(-12f,-2f));
        LongPlatform longPlatform1 = new LongPlatform(this,new Vec2(4f,-2f));
        LongPlatform longPlatform2 = new LongPlatform(this,new Vec2(45f,-4f));
        Platform platform4 = new Platform(this,new Vec2(29f,-7f));
        Platform platform5 = new Platform(this,new Vec2(22f,-10f));
        Platform platform6 = new Platform(this,new Vec2(2f,10f));
        Platform platform7 = new Platform(this,new Vec2(10f,6f));
        LongPlatform longPlatform3 = new LongPlatform(this,new Vec2(25f,4f));
        Platform platform8 = new Platform(this,new Vec2(-14f,6f));
        Platform platform9 = new Platform(this,new Vec2(-24f,8f));
        LongPlatform longPlatform4 = new LongPlatform(this,new Vec2(-35f,3f));
        LongPlatform longPlatform5 = new LongPlatform(this,new Vec2(-35f,14f));




        // moving platforms
        MovingPlatform right1 = new MovingPlatform(this,new Vec2(19f,-2f), new Vec2(30f,-2f));
        VerticalPlatform up1 = new VerticalPlatform(this,-11.5f,-2f, new Vec2(-30f,-11.5f));
        VerticalPlatform up2 = new VerticalPlatform(this,0f,12f, new Vec2(-6f,0f));
        VerticalPlatform up3 = new VerticalPlatform(this,3f,18f, new Vec2(-50f,3f));


        // player
        player = new Player(this,null);
        Interaction interaction = new Interaction(player);
        player.setInteraction(interaction);

        player.setPosition(new Vec2(x, y));
        Vec2 pos = player.getPosition();

        //enemies
        Enemy enemy1 = new EnemyBody(this, player,1);
        enemy1.setPosition(new Vec2(4f,0f));
        Enemy enemy2 = new EnemyBody(this,player,2);
        enemy2.setPosition(new Vec2(45f,-2f));
        Enemy enemy3 = new EnemyBody(this,player,3);
        enemy3.setPosition(new Vec2(25f,6f));
        Enemy enemy4 = new EnemyBody(this,player,4);
        enemy4.setPosition(new Vec2(-24f,10f));



        //collectable objects
        Shape Collectible = new BoxShape(0.5f, 0.5f);
        Interactable object = new Interactable(this, Collectible,4,new Vec2(49f,-2f),gifPlayer);
        Interactable object2 = new Interactable(this, Collectible,2,new Vec2(22f,-8f),gifPlayer);
        Interactable object3 = new Interactable(this, Collectible,3,new Vec2(-20f,-3f),gifPlayer);
        Interactable object4 = new Interactable(this, Collectible,1,new Vec2(-35f,16f),gifPlayer);
        Interactable Door = new Interactable(this, Collectible,6,new Vec2(-35f,5f),gifPlayer);

        //key for door
        keyItem = new Interactable(this, Collectible,5, new Vec2(10f,8f), gifPlayer);
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

