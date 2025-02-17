/**
 * This is the FruitTree subclass under {@link Crop}.
 * <p>
 * Additional methods include generating number of produce.
 */

public class FruitTree extends Crop implements Randomizable {

    /**
     * {@inheritDoc}
     *
     */
    public FruitTree(Seed seed, int currentDay) {
        super(seed, currentDay);
    }

    /**
     * See {@link Randomizable#generateProduce()}.
     *
     */
    public int generateProduce() {
        int min = super.getSeed().getProduceMin();
        return (int)(Math.random()*(super.getSeed().getProduceMax()-min+1)+min);
    }

    /**
     * {@inheritDoc}
     *
     */
    public double computeHarvestPrice(double harvestTotal, double waterBonus, double fertilizerBonus) {
        return harvestTotal + waterBonus + fertilizerBonus;
    }
}
