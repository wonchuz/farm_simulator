/**
 * This marks the classes that have the {@link #canRegister(Player) canRegister} 
 * and {@link #upgrade(Player) upgrade} methods.
 */

public interface Registerable {

    /**
     * Checks whether the Player can register to the subsequent farmer type. 
     *
     * @param player the player object of the Player
     * @return true if the player can register to the subsequent farmer type, otherwise false
     */
    public boolean canRegister(Player player);

     /**
     * Upgrades the Player's farmer type to the subsequent farmer type. 
     *
     * @param player the player object of the Player
     */
    public void upgrade(Player player);
}
