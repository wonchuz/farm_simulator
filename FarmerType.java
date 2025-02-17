/**
 * This is the abstract FarmerType class for the farming simulator game for MCO2.
 * <p>
 * Methods mostly include getting information on the respective farmer type,
 * including name, level requirement, bonus earnings, seed cost reduction,
 * water bonus increase, fertilizer bonus incrase, and registration fee.
 */

public abstract class FarmerType {
    private final String name;
    private final int levelReq;
    private final double bonusEarnings;
    private final double seedCostReduction;
    private final int waterBonusIncrease;
    private final int fertilizerBonusIncrease;
    private final double registrationFee;
    
   /**
     * This is a constructor for the FarmerType class
     * 
     * @param name                          the farmer type name
     * @param levelReq                      the level requirement to register for the farmer type
     * @param bonusEarnings                 the bonus earnings per produce of the farmer type
     * @param seedCostReduction             the seed cost reduction bonus of the farmer type
     * @param waterBonusIncrease            the water bonus increase of the farmer type
     * @param fertilizerBonusIncrease       the fertilizer bonus increase of the farmer type
     * @param registrationFee               the Objectcoin registration fee of the farmer type
     */
    public FarmerType(String name, int levelReq, double bonusEarnings, int seedCostReduction, int waterBonusIncrease, int fertilizerBonusIncrease, double registrationFee) {
        this.name = name;
        this.levelReq = levelReq;
        this.bonusEarnings = bonusEarnings;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusIncrease = waterBonusIncrease;
        this.fertilizerBonusIncrease = fertilizerBonusIncrease;
        this.registrationFee = registrationFee;
    }

    /**
     * Gets the name of the farmer type.
     *
     * @return the name of the farmer type
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the level requirement of the farmer type.
     *
     * @return the level requirement of the farmer type
     */
    public int getLevelReq() {
        return this.levelReq;
    }

    /**
     * Gets the bonus earnings per produce of the farmer type.
     *
     * @return the bonus earnings per produce of the farmer type
     */
    public double getBonusEarnings() {
        return this.bonusEarnings;
    }
    
    /**
     * Gets the seed cost reduction bonus of the farmer type.
     *
     * @return the seed cost reduction bonus of the farmer type
     */
    public double getSeedCostReduction() {
        return this.seedCostReduction;
    }

    /**
     * Gets the water bonus increase of the farmer type.
     *
     * @return the water bonus increase of the farmer type
     */
    public int getWaterBonusIncrease() {
        return this.waterBonusIncrease;
    }

    /**
     * Gets the fertilizer bonus increase of the farmer type.
     *
     * @return the fertilizer bonus increase of the farmer type
     */
    public int getFertilizerBonusIncrease() {
        return this.fertilizerBonusIncrease;
    }

    /**
     * Gets the Objectcoin registration fee of the farmer type.
     *
     * @return the Objectcoin registration fee of the farmer type
     */
    public double getRegistrationFee() {
        return this.registrationFee;
    }
}
