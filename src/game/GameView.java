package game;
import city.cs.engine.*;
import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

import org.jbox2d.common.Vec2;
import java.awt.Point;
import java.awt.Graphics2D;

/** creates a view centered on the player with a parallax background and initialises all the UI for the player health and enemy health
 */
public class GameView extends UserView implements StepListener{
    private Image background;
    private Image Foreground;
    private Image frontImage;
    private GameWorld gameWorld;
    private Player player;
    private Image background2;
    private Image background3;

    /** initialises a game view
     * @param world reference to the game world
     * @param width sets the width of the view
     * @param height sets the height of the view
     */
    public GameView(GameWorld world, int width, int height) {
        //this is the constructor for the game view which handles visuals such as the background in-game
        super(world, width, height);
        background = new ImageIcon("data/skybackground1.png").getImage();
        background2 = new ImageIcon("data/skybackground2.png").getImage();
        background3 = new ImageIcon("data/skybackground3.png").getImage();
        Foreground = new ImageIcon("data/ballroom.png").getImage();
        this.gameWorld = world;
        this.player = gameWorld.getPlayer();
        world.addStepListener(this);
    }

    /** paints a parallax background with two background components and a foreground, also sets up the player health UI and assigns health bars to the enemies
     * @param g graphic used to paint the background's components
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        //this is responsible for the parallax effect in the background as the player moves
        super.paintBackground(g);
        Vec2 playerPos = gameWorld.getPlayer().getPosition();
        float playerX = playerPos.x;
        float playerY = playerPos.y;

        //this section handles the speed of the movement to create an effect of different layers
        //this works by taking in the player's position on screen and multiplying it to slow it down a little bit so that the
        //movement appears to be happening relative to the players position, the background moves the slowest because its
        //the furthest away, and the foreground moves the quickest as it is closer to the player
        int bg1X = (int) (playerX *0.3);
        int bg1Y = (int) (playerY *0.3);

        int bg2X = (int) (playerX *0.5);
        int bg2Y = (int) (playerY *0.5);
        int bg3X = (int) (playerX *0.7);
        int bg3Y = (int) (playerY *0.7);

        int fgX = (int) (playerX *10);
        int fgY = (int) (playerY *10);

        g.drawImage(background, bg1X, bg1Y, 1920,1080,null);
        g.drawImage(background2, bg2X, bg2Y, 1920,1080,null);
        g.drawImage(background3, bg3X, bg3Y, 1920,1080,null);

        g.drawImage(Foreground, fgX, fgY, 1920, 1080, null);

        //this is responsible for tracking the enemies and allowing the game to draw the health bar above their bodies
        for (Body body : gameWorld.getDynamicBodies()){
            if (body instanceof Enemy){
                Enemy enemy = (Enemy) body;
                drawHealthBarEnemy(g, enemy);

            }
        }

        //this section handles the drawing of the health level of the character to show when the player takes damage
        Image fullheart = new ImageIcon("data/shiningheart.gif").getImage();
        Image emptyHeart = new ImageIcon("data/emptyheart.gif").getImage();

        int maxHearts = 5;
        int currentHealth = player.getHealth();
        int xStart = 20;
        int y = 20;

        for (int i = 0; i < maxHearts; i++) {
            if(i< currentHealth){
                g.drawImage(fullheart, xStart + (i*40), y,32,32, null);
            }else{
                g.drawImage(emptyHeart, xStart + (i*40), y,32,32, null);
            }
        }
    }

    /*this function handles the actual drawing of the health bar which can then be called within the tracking section
    * to draw this above all of their heads. it takes in the screen position and the enemy's position using the method written
    * in the enemy class. it then colours them and decreases in width based on the enemies health */
    /** draws a health bar above the enemy which updates based on their health stats
     * @param g graphic to paint the UI component of the bar
     * @param enemy reference to the enemy the health bar will be painted on top of
     */
    private void drawHealthBarEnemy(Graphics2D g, Enemy enemy) {
        Point2D screenPos = worldToView(enemy.getHealthBarPosition());

        Point intScreenPos = new Point ((int) screenPos.getX(), (int) screenPos.getY());
        int barWidth = 1;
        int barHeight = 10;
        int x = intScreenPos.x - (barWidth/2);
        int y = intScreenPos.y - (10);

        float healthPercentage = enemy.getHealth();
        int healthWidth = (int) (barWidth * healthPercentage);

       g.setColor(Color.red);
       g.fillRect(x, y, healthWidth, barHeight);

    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }
    /** centers the view on the player
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        if(player != null){
            this.setCentre(player.getPosition());
        }
    }
}
