/**
 * This is the Farmer subclass under {@link FarmerType}.
 * <p>
 * Additional methods include checking whether the Player is able to register
 * to the subsequent farmer type and upgrading the Player to that farmer type.
 */

public class Farmer extends FarmerType implements Registerable {

    /**
     * This is a constructor for the Farmer subclass
     * 
     * @param name                          the farmer type name
     * @param levelReq                      the level requirement to register for the farmer type
     * @param bonusEarnings                 the bonus earnings per produce of the farmer type
     * @param seedCostReduction             the seed cost reduction bonus of the farmer type
     * @param waterBonusIncrease            the water bonus increase of the farmer type
     * @param fertilizerBonusIncrease       the fertilizer bonus increase of the farmer type
     */
    public Farmer(String name, int levelReq, double bonusEarnings, int seedCostReduction, int waterBonusIncrease, int fertilizerBonusIncrease) {
        super(name, levelReq, bonusEarnings, seedCostReduction, waterBonusIncrease, fertilizerBonusIncrease, 0);
    }
    
    /**
     * See {@link Registerable#canRegister(Player)}.
     *
     */
    public boolean canRegister(Player player) {
        // Check if player meets level requirement
        if((int)(player.getExperience()/100) >= 5) {
            // Check if player can afford fee
            if(player.getObjectcoins() >= 200) {
                return true;
            }
        }
        return false;
    }

    /**
     * See {@link Registerable#upgrade(Player)}.
     *
     */
    public void upgrade(Player player) {
        // Player can register
        if(canRegister(player) == true) {
            player.removeObjectcoins(200);
            player.setFarmerType(new RegisteredFarmer("Registered Farmer", 5, 1, 1, 0, 0, 200));
        }
    }
}
