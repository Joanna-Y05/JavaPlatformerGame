package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyMovement extends KeyAdapter {
    private Player player;

    public KeyMovement(Player player){
        this.player = player;
    }

    //checks for key presses and then sets the player state depending on the key press
    @Override
    public void keyPressed (KeyEvent e){
        if (player == null) return;
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                player.jump(10);
                player.setCurrentState(Player.PlayerState.Idle);
                break;
            case KeyEvent.VK_DOWN:
                //crouch
                break;
            case KeyEvent.VK_LEFT:
                player.startWalking(-5);
                player.setCurrentState(Player.PlayerState.Walking_left);
                break;
            case KeyEvent.VK_RIGHT:
                player.startWalking(5);
                player.setCurrentState(Player.PlayerState.Walking_right);
                break;
                case KeyEvent.VK_Q:
                    new Game();
                    //to quit game
                    break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (player == null ) return;

        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT:
                    player.stopWalking();
                    player.setCurrentState(Player.PlayerState.Idle);
        break;}

    }
}
