/**
 * This is the Flower subclass under {@link Crop}.
 * 
 */

public class Flower extends Crop {

    /**
     * {@inheritDoc}
     *
     */
    public Flower(Seed seed, int currentDay) {
        super(seed, currentDay);
    }

    /**
     * {@inheritDoc}
     *
     */
    public double computeHarvestPrice(double harvestTotal, double waterBonus, double fertilizerBonus) {
        double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

        return finalHarvestPrice * 1.1;
    }

}
