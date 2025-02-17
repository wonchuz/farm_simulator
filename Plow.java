/**
 * This is the Plow subclass under {@link Tool}.
 *
 */

public class Plow extends Tool {
    
    /**
     * {@inheritDoc}
     *
     */
    public Plow(double cost, double experience) {
        super(cost, experience);
    }

    /**
     * Converts an unplowed tile into a plowed tile
     *
     * @param tile the unplowed tile to be converted
     * @return true if the tile was successfully converted, otherwise false
     */
    public boolean convert(Tile tile) {
        if(tile.plowTile() == true) {
            return true;
        }
        return false;
    }
}