/**
 * This is the abstract Tool class for the farming simulator game for MCO2.
 * <p>
 * Methods include getting tool information; cost and experience.
 */

public abstract class Tool {
    private final double cost;
    private final double experience;


    /**
      * This is a constructor for the Tool class
      * 
      * @param cost         the cost of using this tool
      * @param experience   the amount of experience gained from using this tool   
      */
    public Tool(double cost, double experience) {
        this.cost = cost;
        this.experience = experience;
    }

    /**
     * Gets the cost of using this tool.
     *
     * @return the cost of using this tool
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Gets the amount of experience gained from using this tool.
     *
     * @return the amount of experience gained from using this tool
     */
    public double getExperience() {
        return this.experience;
    }
}
