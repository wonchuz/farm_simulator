/**
 * This is the Shovel subclass under {@link Tool}.
 *
 */

public class Shovel extends Tool implements Removable {
    
    /**
     * {@inheritDoc}
     *
     */
    public Shovel(double cost, double experience) {
        super(cost, experience);
    }
    
    /**
     * See {@link Removable#remove(Tile)}.
     *
     */
    public boolean remove(Tile tile) {
        if(tile.getIsAvailable() == false && tile.getHasRock() == false) {
            tile.removeCrop();
        }
        return true;
    }
}
