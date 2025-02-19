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

    private Seed(SeedBuilder builder) {
        this.name = builder.name;
        this.harvestTime = builder.harvestTime;
        this.waterNeeds = builder.waterNeeds;
        this.waterBonus = builder.waterBonus;
        this.fertilizerNeeds = builder.fertilizerNeeds;
        this.fertilizerBonus = builder.fertilizerBonus;
        this.produceMin = builder.produceMin;
        this.produceMax = builder.produceMax;
        this.cost = builder.cost;
        this.sellPrice = builder.sellPrice;
        this.expYield = builder.expYield;
    }

    public String getName() { 
        return this.name; 
    }
    public int getHarvestTime() { 
        return this.harvestTime; 
    }

    public int getWaterNeeds() { return 
        this.waterNeeds; 
    }

    public int getWaterBonus() { 
        return this.waterBonus; 
    }

    public int getFertilizerNeeds() { return 
        this.fertilizerNeeds; 
    }

    public int getFertilizerBonus() { 
        return this.fertilizerBonus; 
    }

    public int getProduceMin() { 
        return this.produceMin; 
    }

    public int getProduceMax() { 
        return this.produceMax;
    }

    public double getCost() { 
        return this.cost; 
    }

    public double getSellPrice() { 
        return this.sellPrice; 
    }

    public double getExperienceYield() { 
        return this.expYield; 
    }

    public static class SeedBuilder {
        private final String name;
        private final int harvestTime;
        private int waterNeeds;
        private int waterBonus;
        private int fertilizerNeeds;
        private int fertilizerBonus;
        private int produceMin;
        private int produceMax;
        private final double cost;
        private final double sellPrice;
        private final double expYield;

        public SeedBuilder(String name, int harvestTime, double cost, double sellPrice, double expYield) {
            this.name = name;
            this.harvestTime = harvestTime;
            this.cost = cost;
            this.sellPrice = sellPrice;
            this.expYield = expYield;
        }

        public SeedBuilder waterRequirements(int needs, int bonus) {
            this.waterNeeds = needs;
            this.waterBonus = bonus;
            return this;
        }

        public SeedBuilder fertilizerRequirements(int needs, int bonus) {
            this.fertilizerNeeds = needs;
            this.fertilizerBonus = bonus;
            return this;
        }

        public SeedBuilder produceRange(int min, int max) {
            this.produceMin = min;
            this.produceMax = max;
            return this;
        }

        public SeedBuilder fixedProduce(int amount) {
            this.produceMin = amount;
            this.produceMax = amount;
            return this;
        }

        public Seed build() {
            return new Seed(this);
        }
    }
}