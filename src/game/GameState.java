package game;

/** stores information on which levels are unlocked by the player
 */
public class GameState {

    // this class could be implemented to save the players progress so when the load button is pressed,
    // their previous progress is restored

    private int unlockedLevels = 1;

    /** allows access to which levels are unlocked
     * @return int value of how many levels are unlocked
     */
    public int getUnlockedLevels() {
        return unlockedLevels;
    }

    /** increments the number of levels unlocked by 1
     */
    public void unlockNextLevel() {
        //when the player interacts with the door and they have all the items, this now allows them to move to the next level
        unlockedLevels++;
    }
    public void resetUnlockedLevels() {
        //if the load feature was implemented this would have been called to reset the level progress
        unlockedLevels = 1;
    }
    public void setUnlockedLevels(int unlockedLevels) {
        //this would have been used in the loading of progress as well
        this.unlockedLevels = unlockedLevels;
    }
}
