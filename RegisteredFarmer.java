/**
 * This is the RegisteredFarmer subclass under {@link FarmerType}.
 * <p>
 * Additional methods include checking whether the Player is able to register
 * to the subsequent farmer type and upgrading the Player to that farmer type.
 */

public class RegisteredFarmer extends FarmerType implements Registerable {
    
    /**
     * {@inheritDoc}
     *
     */
    public RegisteredFarmer(String name, int levelReq, double bonusEarnings, int seedCostReduction, int waterBonusIncrease, int fertilizerBonusIncrease, double registrationFee) {
        super(name, levelReq, bonusEarnings, seedCostReduction, waterBonusIncrease, fertilizerBonusIncrease, registrationFee);
    }

    /**
     * See {@link Registerable#canRegister(Player)}.
     *
     */
    public boolean canRegister(Player player) {
        // Check if player meets level requirement
        if((int)(player.getExperience()/100) >= super.getLevelReq() + 5) {
            // Check if player can afford fee
            if(player.getObjectcoins() >= super.getRegistrationFee() + 100) {
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
            player.removeObjectcoins(super.getRegistrationFee() + 100);
            player.setFarmerType(new DistinguishedFarmer("Distinguished Farmer", 10, 2, 2, 1, 0, 300));
        }
    }
}
