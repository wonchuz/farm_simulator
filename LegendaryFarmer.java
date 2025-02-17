/**
 * This is the LegendaryFarmer subclass under {@link FarmerType}.
 */

public class LegendaryFarmer extends FarmerType {

    /**
     * {@inheritDoc}
     *
     */
    public LegendaryFarmer(String name, int levelReq, double bonusEarnings, int seedCostReduction, int waterBonusIncrease, int fertilizerBonusIncrease, double registrationFee) {
        super(name, levelReq, bonusEarnings, seedCostReduction, waterBonusIncrease, fertilizerBonusIncrease, registrationFee);
    }
}