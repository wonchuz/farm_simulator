/**
 * This is the Fertilizer subclass under {@link Tool}.
 *
 */

public class Fertilizer extends Tool implements Addable {
    
    /**
     * {@inheritDoc}
     *
     */
    public Fertilizer(double cost, double experience) {
        super(cost, experience);
    }

    /**
     * See {@link Addable#add(Crop)}.
     *
     */
    public boolean add(Crop crop) {
        if(crop != null) {
            crop.fertilize();
            return true;
        }
        return false;
    }
}
