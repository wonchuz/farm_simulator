/**
 * This is the Seed class for the farming simulator game for MCO2.
 * <p>
 * Methods include seed actions in the game; getting seed information.
 */
public class Seed {
    private final String name;
    private final int harvestTime;
    private final int waterNeeds;
    private final int waterBonus;
    private final int fertilizerNeeds;
    private final int fertilizerBonus;
    private final int produceMin;
    private final int produceMax;
    private final double cost;
    private final double sellPrice;
    private final double expYield;

    /**
     * This is a constructor for the Seed class.
     *
     * @param name              the name of the seed
     * @param harvestTime       the harvest time of the seed
     * @param waterNeeds        the water needs of the seed
     * @param waterBonus        the water bonus of the seed
     * @param fertilizerNeeds   the fertilizer needs of the seed
     * @param fertilizerBonus   the fertilizer bonus of the seed
     * @param produceMin        the amount of produce of the seed 
     * @param cost              the Objectcoin cost of the seed
     * @param sellPrice         the base sell price of the crop of the seed per produce
     * @param expYield          the base experience point yield of the seed
     */
    public Seed(String name, int harvestTime, int waterNeeds, int waterBonus, int fertilizerNeeds, int fertilizerBonus, int produceMin, double cost, double sellPrice, double expYield) {
        this.name = name;
        this.harvestTime = harvestTime;
        this.waterNeeds = waterNeeds;
        this.waterBonus = waterBonus;
        this.fertilizerNeeds = fertilizerNeeds;
        this.fertilizerBonus = fertilizerBonus;
        this.produceMin = produceMin;
        this.produceMax = produceMin;
        this.cost = cost;
        this.sellPrice = sellPrice;
        this.expYield = expYield;
    }

    /**
     * This is a constructor for the Seed class.
     *
     * @param name              the name of the seed
     * @param harvestTime       the harvest time of the seed
     * @param waterNeeds        the water needs of the seed
     * @param waterBonus        the water bonus of the seed
     * @param fertilizerNeeds   the fertilizer needs of the seed
     * @param fertilizerBonus   the fertilizer bonus of the seed
     * @param produceMin        the minimum produce of the seed 
     * @param produceMax        the maximum produce of the seed
     * @param cost              the Objectcoin cost of the seed
     * @param sellPrice         the base sell price of the crop of the seed per produce
     * @param expYield          the base experience point yield of the seed
     */
    public Seed(String name, int harvestTime, int waterNeeds, int waterBonus, int fertilizerNeeds, int fertilizerBonus, int produceMin, int produceMax, double cost, double sellPrice, double expYield) {
        this.name = name;
        this.harvestTime = harvestTime;
        this.waterNeeds = waterNeeds;
        this.waterBonus = waterBonus;
        this.fertilizerNeeds = fertilizerNeeds;
        this.fertilizerBonus = fertilizerBonus;
        this.produceMin = produceMin;
        this.produceMax = produceMax;
        this.cost = cost;
        this.sellPrice = sellPrice;
        this.expYield = expYield;
    }

    /**
     * Gets the name of the seed.
     * 
     * @return name      the name of the seed
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the harvest time of the seed.
     * 
     * @return the harvest time of the seed
     */
    public int getHarvestTime() {
        return this.harvestTime;
    }
    
    /**
     * Gets the water needs of the seed.
     * 
     * @return the water needs of the seed
     */
    public int getWaterNeeds() {
        return this.waterNeeds;
    }

    /**
     * Gets the water bonus of the seed.
     * 
     * @return the water bonus of the seed
     */
    public int getWaterBonus() {
        return this.waterBonus;
    }

    /**
     * Gets the fertilizer needs of the seed.
     * 
     * @return the fertilizer needs of the seed
     */
    public int getFertilizerNeeds() {
        return this.fertilizerNeeds;
    }

    /**
     * Gets the fertilizer bonus of the seed.
     * 
     * @return the fertilizer bonus of the seed
     */
    public int getFertilizerBonus() {
        return this.fertilizerBonus;
    }

    /**
     * Gets the minimum produce of the seed.
     * 
     * @return the minimum produce of the seed
     */
    public int getProduceMin() {
        return this.produceMin;
    }

    /**
     * Gets the max produce of the seed.
     * 
     * @return the max produce of the seed
     */
    public int getProduceMax() {
        return this.produceMax;
    }

    /**
     * Gets the cost of the seed.
     * 
     * @return the Objectcoin cost of the seed
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Gets the base sell price of the seed per produce.
     * 
     * @return the base sell price of the seed per produce
     */
    public double getSellPrice() {
        return this.sellPrice;
    }

    /**
     * Gets the base experience point yield of the seed.
     * 
     * @return the base experience point yield of the seed
     */
    public double getExperienceYield() {
        return this.expYield;
    }
}
