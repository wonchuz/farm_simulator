/**
 * This is the Pickaxe subclass under {@link Tool}.
 *
 */

public class Pickaxe extends Tool implements Removable {
    
    /**
     * {@inheritDoc}
     *
     */
    public Pickaxe(double cost, double experience) {
        super(cost, experience);
    }

    /**
     * See {@link Removable#remove(Tile)}.
     *
     */
    public boolean remove(Tile tile) {
        if(tile.removeRock() == true) {
            return true;
        }
        return false;
    }
}