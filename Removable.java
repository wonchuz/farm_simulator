/**
 * This marks the classes that have the {@link #remove(Tile) remove} method.
 */

public interface Removable {
    
    /**
     * Removes the respective obstacle on the tile
     *
     * @param tile the tile to be rid of an obstacle
     * @return true if the obstacle was successfully removed, false otherwise
     */
    public boolean remove(Tile tile);
}