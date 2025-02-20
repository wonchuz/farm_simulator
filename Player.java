import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This is the Player class for the farming simulator game for MCO2.
 * <p>
 * Methods include player actions for the game; scattering rocks,
 * advancing to the next day, using the different available
 * tools, registering different farmer types, planting seeds,
 * harvesting crops, and getting/returning the class' attributes.
 */
public class Player {
    private static final int FARM_LOT_SIZE = 50;
    private static final double INITIAL_OBJECT_COINS = 100;
    private static final double SEED_COST_BASE = 5;
    private FarmerType farmerType;
    private double objectCoins;
    private double experience;

    private Tile[] farmLot;
    private boolean isRunning;
    private int currentDay;

    private int lastProductsProduced;
    private double lastHarvestTotal;

    private double lastWaterBonus;
    private double lastFertilizerBonus;
    private double lastHarvestPrice;

    /**
     * This is the constructor for the Player class.
     * 
     * @param input text file containing the rock placements
     */
    public Player(File input) {
        this.objectCoins = INITIAL_OBJECT_COINS;
        this.farmerType = FarmerType.FARMER; // Refactored
        this.experience = 0;

        this.farmLot = new Tile[FARM_LOT_SIZE];

        for (int i = 0; i < 50; i++) {
            this.farmLot[i] = new Tile();
        }

        try {
            scatterRocks(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.isRunning = true;
        this.currentDay = 1;
        this.lastProductsProduced = 0;
        this.lastHarvestTotal = 0;
        this.lastWaterBonus = 0;
        this.lastFertilizerBonus = 0;
        this.lastHarvestPrice = 0;
    }

    /**
     * Scatters rocks across the farm lot via file input.
     * 
     * @param input text file containing the rock placements
     * @throws FileNotFoundException if file is not found
     */
    public void scatterRocks(File input) throws FileNotFoundException {
        Scanner myReader = new Scanner(input);

        while (myReader.hasNextInt()) {
            this.farmLot[myReader.nextInt() - 1].addRock();
        }

        myReader.close();
    }

    /**
     * Adds specified experience points to the Player.
     * 
     * @param experience the amount of experience points to be added
     */
    public void addExperience(double experience) {
        this.experience += experience;
    }

    /**
     * Advances the game state to the following day and checks conditions to either
     * end or continue the game or change states of crops.
     */
    public void advanceDay() {
        this.currentDay++; // Advance the day
        handleCropWithering();
    }

    /**
     * For each crop in the farm, check if it should be withered
     */
    public void handleCropWithering() {
        int growingCrop = 0;
        int witheredCrop = 0;

        // Check status of each tile in the Farm
        for (Tile tile : this.farmLot) {
            Crop crop = tile.getCrop();
            // Tile has a crop
            if (crop != null) {
                growingCrop++;

                // 1. If crop's harvest requirements are not met or 
                // 2. it was not harvested on its harvest day
                if (!meetsHarvestRequirements(crop) || !wasHarvested(crop)) {
                    // Wither crop
                    crop.wither();
                }

                if (crop.getIsWithered()) {
                    witheredCrop++;
                }
            }
        }

        checkGameOver(growingCrop, witheredCrop);
    }

    /**
     * Checks for game over conditions:
     * 1. No growing crops
     * 2. All crops in the farm are Withered
     * 3. Player cannot afford to buy seeds and all their growing crops are withered
     * 
     * @param growingCrop the number of growing crops for the day
     * @param witheredCrop the number of withered crops for the day
     */
    public void checkGameOver(int growingCrop, int witheredCrop) {
        // No growing crops or All crops in farm are Withered
        if (growingCrop == 0 || witheredCrop == FARM_LOT_SIZE) {
            this.isRunning = false;
        }

        // Player cannot buy seeds
        if (!canBuySeeds()) {
            // All growing crops are withered
            if (growingCrop == witheredCrop) {
                this.isRunning = false;
            }
        }
    }

    /**
     * Returns true if the player has more coins than the minimum cost seed.
     */
    public boolean canBuySeeds() {
        return this.objectCoins >= (SEED_COST_BASE - this.farmerType.getSeedCostReduction());
    }

    /**
     * Returns true if a crop has met its requirements for a successful harvest.
     * 
     * @param crop the crop to check
     */
    public boolean meetsHarvestRequirements(Crop crop) {
        return crop.isReady() || // Crop on the tile does meets the conditions for harvest
               crop.getHarvestDay() != this.currentDay; // The current day is not the crop's harvest day
    }

    /**
     * Returns true if a crop was harvested on its harvest day.
     * 
     * @param crop the crop to check
     */
    public boolean wasHarvested(Crop crop) {
        return crop.getHarvestDay() != this.currentDay - 1;
    }

    /**
     * Removes specified Objectcoins to the Player.
     * 
     * @param Objectcoins player's amount of money that can be spent
     */
    public void removeObjectcoins(double Objectcoins) {
        this.objectCoins -= Objectcoins;
    }

    /**
     * Checks whether the Player can register to the subsequent farmer type. 
     *
     * @return true if the player can register to the subsequent farmer type, otherwise false
     */
    public boolean canRegister() {
        // Added method:
        FarmerType nextType = this.farmerType.getNextType();

        // Check if there is a next tier
        if (nextType == null) {
            return false;
        } 

        int playerLevel = (int)this.experience / 100;
        int levelReq = nextType.getLevelReq();
        double fee = nextType.getRegistrationFee();

        // Return if player meets requirements
        return playerLevel >= levelReq && this.objectCoins >= fee;
    }

    /**
     * Registers the player to the next farmer type.
     * 
     * @param player the player object of the Player
     */
    public void register() {
        // Refactored/Modified method:
        if (this.canRegister()) {
            this.setFarmerType(this.farmerType.getNextType());
        }
    }

    /**
     * Uses a tool to do certain actions on tiles and crops.
     * 
     * @param tool the tool to be used
     * @param tile the tile for the tool to be used on
     */
    public void useTool(Tool tool, Tile tile) {
        double price = tool.getCost();
        boolean result;

        if (this.objectCoins >= price) {
            if (tool instanceof Addable) {
                result = ((Addable) tool).add(tile.getCrop());
            } else if (tool instanceof Removable) {
                result = ((Removable) tool).remove(tile);
            } else {
                result = ((Plow) tool).convert(tile);
            }

            // Tool was successfully used
            if (result == true) {
                this.objectCoins -= price;
                addExperience(tool.getExperience());
            }
        }
    }

    /**
     * Finds the index of the chosen tile.
     * 
     * @param tile the tile to get the index of
     * @return index of tile
     */
    public int findTileIndex(Tile tile) {
        for (int i = 0; i < FARM_LOT_SIZE; i++) {
            if (this.farmLot[i].equals(tile)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Buys and plants a seed in the farm lot.
     * 
     * @param tile the tile to be planted on
     * @param seed the seed to be planted
     */
    public void plantSeed(Tile tile, Seed seed) {
        double price = seed.getCost() - this.farmerType.getSeedCostReduction();
        int index = findTileIndex(tile);

        // Check if seed can be planted on tile
        if (tile.canPlant(this.farmLot, seed, index) == true && this.objectCoins >= price) {
            this.objectCoins -= price; // Buys Seed
            String name = seed.getName();

            // Plants seed on tile
            if (name.equalsIgnoreCase("Mango") || name.equalsIgnoreCase("Apple")) {
                tile.addCrop(new FruitTree(seed, this.currentDay));
            } else if (name.equalsIgnoreCase("Turnip") || name.equalsIgnoreCase("Carrot")
                    || name.equalsIgnoreCase("Potato")) {
                tile.addCrop(new RootCrop(seed, this.currentDay));
            } else {
                tile.addCrop(new Flower(seed, this.currentDay));
            }
        }
    }

    /**
     * Harvests and sells crop and adds corresponding objectCoins and
     * experience to the Player.
     * 
     * @param tile the tile to be harvested
     */
    public void harvestCrop(Tile tile) {
        Crop crop = tile.retrieveCrop(tile);

        // Crop is successfully harvested and removed from tile
        if (crop != null) {
            if (crop instanceof Randomizable) {
                this.lastProductsProduced = ((Randomizable) crop).generateProduce();
            } else {
                this.lastProductsProduced = crop.getSeed().getProduceMin();
            }
            // Remove Capped Water and Fertilizer
            crop.removeExcess(this.farmerType);

            // Compute for Price
            this.lastHarvestTotal = crop.computeHarvestTotal(this.lastProductsProduced,
                    this.farmerType.getBonusEarnings());
            this.lastWaterBonus = crop.computeWaterBonus(this.lastHarvestTotal);
            this.lastFertilizerBonus = crop.computeFertilizerBonus(this.lastHarvestTotal);
            this.lastHarvestPrice = crop.computeHarvestPrice(this.lastHarvestTotal, this.lastWaterBonus,
                    this.lastFertilizerBonus);

            // Give Gained Objectcoins to Player
            this.objectCoins += this.lastHarvestPrice;
            addExperience(crop.getSeed().getExperienceYield());
        }
    }

    /**
     * Gets the tile of the farmlot based on the index.
     *
     * @param index the index of the tile to be gotten
     * @return the specified tile
     */
    public Tile getTile(int index) {
        return this.farmLot[index];
    }

    /**
     * Gets the farmer type of the Player.
     *
     * @return the farmer type
     */
    public FarmerType getFarmerType() {
        return this.farmerType;
    }

    /**
     * Sets the farmer type of the Player.
     * 
     * @param farmerType the farmer type
     */
    public void setFarmerType(FarmerType farmerType) {
        this.farmerType = farmerType;
    }

    /**
     * Gets the objectCoins of the Player.
     *
     * @return the objectCoins of the Player
     */
    public double getObjectcoins() {
        return this.objectCoins;
    }

    /**
     * Gets the experience points of the Player.
     *
     * @return the experience points of the Player
     */
    public double getExperience() {
        return this.experience;
    }

    /**
     * Gets the current FarmLot of the Player.
     *
     * @return the farmlot of the player
     */
    public Tile[] getFarmLot() {
        return this.farmLot;
    }

    /**
     * Gets the game state of the game.
     *
     * @return the game state attribute of the farm/game
     */
    public boolean getIsRunning() {
        return this.isRunning;
    }

    /**
     * Gets the current day of the game.
     *
     * @return the current day of the game
     */
    public int getCurrentDay() {
        return this.currentDay;
    }

    /**
     * Gets the last number of products produced from crop
     *
     * @return last products produced
     */
    public int getLastProductsProduced() {
        return this.lastProductsProduced;
    }

    /**
     * Gets the last computed harvest total from crop
     *
     * @return last harvest total
     */
    public double getLastHarvestTotal() {
        return this.lastHarvestTotal;
    }

    /**
     * Gets the last computed water bonus from crop
     *
     * @return last water bonus
     */
    public double getLastWaterBonus() {
        return this.lastWaterBonus;
    }

    /**
     * Gets the last computed fertilizer bonus from crop
     *
     * @return last fertilizer bonus
     */
    public double getLastFertilizerBonus() {
        return this.lastFertilizerBonus;
    }

    /**
     * Gets the last computed harvest price from crop
     *
     * @return last harvest price
     */
    public double getLastHarvestPrice() {
        return this.lastHarvestPrice;
    }
}
