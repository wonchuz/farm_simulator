/**
 * This is the WateringCan subclass under {@link Tool}.
 *
 */

public class WateringCan extends Tool implements Addable {
    
    /**
     * {@inheritDoc}
     *
     */
    public WateringCan(double cost, double experience) {
        super(cost, experience);
    }

    /**
     * See {@link Addable#add(Crop)}.
     *
     */
    public boolean add(Crop crop) {
        if(crop != null) {
            crop.water();
            return true;
        }
        return false;
    }
}