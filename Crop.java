/**
 * This is the Crop class for the farming simulator game for MCO2.
 * <p>
 * Methods include crop actions in the game; removing excess water and fertilizer
 * from the crop, watering the crop, fertilizing the crop, withering the crop,
 * computing for its harvest price, checking state of harvest,
 * getting crop information, and getting the seed of the crop.
 */
public abstract class Crop {
    private final Seed seed;
    private final int harvestDay;
    private int waterCount;
    private int fertilizerCount;
    private boolean isWithered;
    
    /**
     * This is the constructor of the crop class
     * 
     * @param seed          the seed of the crop
     * @param currentDay    current day in the farm
     */
    public Crop(Seed seed, int currentDay) {
        this.seed = seed;
        this.harvestDay = currentDay + seed.getHarvestTime();
        this.waterCount = 0;
        this.fertilizerCount = 0;
        this.isWithered = false;
    }

    /** 
     * Removes the excess water and fertilizer from this crop
     * based on the bonus limits.
     * 
     * @param farmerType    player's current farmer type
    */
    public void removeExcess(FarmerType farmerType) {
        // Get Limits (plus the farmer type bonuses)
        int waterLimit = this.seed.getWaterBonus() + farmerType.getWaterBonusIncrease();
        int fertilizerLimit = this.seed.getFertilizerBonus() + farmerType.getFertilizerBonusIncrease();

        // Bonus Limit capped
        if(this.waterCount > waterLimit) {
            this.waterCount = waterLimit;
        }

        // Bonus Limit capped
        if(this.fertilizerCount > fertilizerLimit) {
            this.fertilizerCount = fertilizerLimit;
        }
    }

    /** 
     * Increases the water count of this crop
    */
    public void water() {
        this.waterCount++;
    }

    /** 
     * Increases the fertilizer count of this crop
    */
    public void fertilize() {
        this.fertilizerCount++;
    }

    /**
     * Withers this crop
    */
    public void wither() {
        this.isWithered = true;
    }

    /**
     * Checks if this crop meets the conditions and is ready for harvest.
     * 
     * @return true if this crop is ready, otherwise false
    */
    public boolean isReady() {
        // Checks if the crop meets the conditions for harvest
        if(this.waterCount >= seed.getWaterNeeds() && this.fertilizerCount >= seed.getFertilizerNeeds() && this.isWithered == false) {
            return true;
        }
        return false;
    }

    /**
     * Computes for the crop's harvest total given the products
     * produced and bonus earnings from a player's farmer type.
     * 
     * @return this crop's harvest total
    */
    public double computeHarvestTotal(int productsProduced, double bonusEarnings) {
        return productsProduced * (this.seed.getSellPrice() + bonusEarnings);
    }
    
    /** 
     * Computes for the crop's water bonus total.
     * 
     * @return this crop's water bonus total
    */
    public double computeWaterBonus(double harvestTotal) {
        return harvestTotal * 0.2 * (this.waterCount - 1);
    }
    
    /**
     * Computes for the crop's fertilizer bonus total.
     * 
     * @return this crop's fertilizer bonus total
    */
    public double computeFertilizerBonus(double harvestTotal) {
        return harvestTotal * 0.5 * this.fertilizerCount;
    }

    /**
     * Computes for the crop's final harvest price.
     * 
     * @return this crop's final harvest price
    */
    public abstract double computeHarvestPrice(double harvestTotal, double waterBonus, double fertilizerBonus);

    /**
     * Gets the seed of the crop.
     *
     * @return the seed of this crop
    */
    public Seed getSeed() {
        return this.seed;
    }

    /**
     * Gets the harvest day of the crop.
     *
     * @return the harvest day of this crop
    */
    public int getHarvestDay() {
        return this.harvestDay;
    }

    /**
     * Gets how many times the crop was watered.
     *
     * @return the water count of this crop
    */
    public int getWaterCount() {
        return this.waterCount;
    }

    /**
     * Gets how many times the crop was fertilized.
     *
     * @return the fertilizer count of this crop
    */
    public int getFertilizerCount() {
        return this.fertilizerCount;
    }

    /**
     * Gets wither state of the crop (whether it is withered or not).
     *
     * @return the wither state of this crop
    */
    public boolean getIsWithered() {
        return this.isWithered;
    }
}
