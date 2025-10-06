package game;

import city.cs.engine.BodyImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//script to handle key presses for interaction with objects and the player

public class Interaction implements KeyListener {

    private Player player;
    private Interactable currentInteractable;


    public Interaction( Player player) {
        this.player = player;
    }

    public void setCurrentInteractable(Interactable interactable) {
        this.currentInteractable = interactable;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override // this handles interaction based on if the player is nearby, there is a current interactable and the space bar is pressed down
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && currentInteractable != null && currentInteractable.nearby()){
            currentInteractable.interact();
            System.out.println("interacting");
            player.setCurrentState(Player.PlayerState.Interacting);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
