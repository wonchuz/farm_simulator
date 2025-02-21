public interface TileState {
    void updateStatus(Tile tile);
    String getStatus();
    String getStatusText(Player playerModel, Tile tile);
    int getColor();
    String getText(Tile tile);
    boolean canPickaxe();
    boolean canPlow();
    boolean canWater();
    boolean canFertilize();
    boolean canShovel();
}

class UnplowedState implements TileState {
    @Override
    public void updateStatus(Tile tile) {
        if (tile.getIsPlowed()) {
            tile.setCurrentState(new PlowedState());
        } else if (tile.getHasRock()) {
            tile.setCurrentState(new RockState());
        }
    }

    @Override
    public String getStatus() {
        return "UNPLOWED";
    }

    @Override
    public String getStatusText(Player playerModel, Tile tile) {
        return "";
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public String getText(Tile tile) {
        return "Unplowed";
    }

    @Override
    public boolean canPickaxe() {
        return false;
    }

    @Override
    public boolean canPlow() {
        return true;
    }
    
    @Override
    public boolean canWater() {
        return false;
    }
    
    @Override
    public boolean canFertilize() {
        return false;
    }

    @Override
    public boolean canShovel() {
        return true;
    }
}

class PlowedState implements TileState {
    @Override
    public void updateStatus(Tile tile) {
        if (tile.getCrop() != null) {
            tile.setCurrentState(new GrowingState());
        }
    }

    @Override
    public String getStatus() {
        return "PLOWED";
    }

    @Override
    public String getStatusText(Player playerModel, Tile tile) {
        return "";
    }

    @Override
    public int getColor() {
        return -1;
    }

    @Override
    public String getText(Tile tile) {
        return "Plowed";
    }

    @Override
    public boolean canPickaxe() {
        return false;
    }

    @Override
    public boolean canPlow() {
        return false;
    }
    
    @Override
    public boolean canWater() {
        return false;
    }
    
    @Override
    public boolean canFertilize() {
        return false;
    }

    @Override
    public boolean canShovel() {
        return true;
    }
}

class GrowingState implements TileState {
    @Override
    public void updateStatus(Tile tile) {
        if (tile.getCrop().isReady()) {
            tile.setCurrentState(new ReadyState());
        } else if (tile.getCrop().getIsWithered()) {
            tile.setCurrentState(new WitheredState());
        }
    }

    @Override
    public String getStatus() {
        return "GROWING";
    }

    @Override
    public String getStatusText(Player playerModel, Tile tile) {
        String text = tile.getCrop().getSeed().getName() + "\n";
        text += "Watered " + tile.getCrop().getWaterCount() + "/" + tile.getCrop().getSeed().getWaterNeeds() + " (" 
                    + (tile.getCrop().getSeed().getWaterBonus() + playerModel.getFarmerType().getWaterBonusIncrease()) + ") times\n";
        text += "Fertilized " + tile.getCrop().getFertilizerCount() + "/" + tile.getCrop().getSeed().getFertilizerNeeds() + " (" 
                    + (tile.getCrop().getSeed().getFertilizerBonus() + playerModel.getFarmerType().getFertilizerBonusIncrease()) + ") times\n";
        text += (tile.getCrop().getHarvestDay() - playerModel.getCurrentDay()) + " days until harvest day...\n";
            
        return text;
    }

    @Override
    public int getColor() {
        return 2;
    }

    @Override
    public String getText(Tile tile) {
        return tile.getCrop().getSeed().getName();
    }

    @Override
    public boolean canPickaxe() {
        return false;
    }

    @Override
    public boolean canPlow() {
        return false;
    }
    
    @Override
    public boolean canWater() {
        return true;
    }
    
    @Override
    public boolean canFertilize() {
        return true;
    }

    @Override
    public boolean canShovel() {
        return true;
    }
}

class ReadyState implements TileState {
    @Override
    public void updateStatus(Tile tile) {
        if (tile.getCrop() == null) {
            tile.setCurrentState(new UnplowedState());
        }
    }

    @Override
    public String getStatus() {
        return "READY";
    }

    @Override
    public String getStatusText(Player playerModel, Tile tile) {
        String text = tile.getCrop().getSeed().getName() + "\n";
        text += "Watered " + tile.getCrop().getWaterCount() + "/" + tile.getCrop().getSeed().getWaterNeeds() + " (" 
                    + (tile.getCrop().getSeed().getWaterBonus() + playerModel.getFarmerType().getWaterBonusIncrease()) + ") times\n";
        text += "Fertilized " + tile.getCrop().getFertilizerCount() + "/" + tile.getCrop().getSeed().getFertilizerNeeds() + " (" 
                    + (tile.getCrop().getSeed().getFertilizerBonus() + playerModel.getFarmerType().getFertilizerBonusIncrease()) + ") times\n";
        text += "READY FOR HARVEST!!\n";
            
        return text;
    }

    @Override
    public int getColor() {
        return 3;
    }

    @Override
    public String getText(Tile tile) {
        return tile.getCrop().getSeed().getName();
    }

    @Override
    public boolean canPickaxe() {
        return false;
    }

    @Override
    public boolean canPlow() {
        return false;
    }
    
    @Override
    public boolean canWater() {
        return true;
    }
    
    @Override
    public boolean canFertilize() {
        return true;
    }

    @Override
    public boolean canShovel() {
        return true;
    }
}

class WitheredState implements TileState {
    @Override
    public void updateStatus(Tile tile) {
        if (tile.getCrop() == null) {
            tile.setCurrentState(new UnplowedState());
        }
    }

    @Override
    public String getStatus() {
        return "WITHERED";
    }

    @Override
    public String getStatusText(Player playerModel, Tile tile) {
        String text = tile.getCrop().getSeed().getName() + "\n";
        text += "Watered " + tile.getCrop().getWaterCount() + "/" + tile.getCrop().getSeed().getWaterNeeds() + " (" 
                    + (tile.getCrop().getSeed().getWaterBonus() + playerModel.getFarmerType().getWaterBonusIncrease()) + ") times\n";
        text += "Fertilized " + tile.getCrop().getFertilizerCount() + "/" + tile.getCrop().getSeed().getFertilizerNeeds() + " (" 
                    + (tile.getCrop().getSeed().getFertilizerBonus() + playerModel.getFarmerType().getFertilizerBonusIncrease()) + ") times\n";
        text += "Crop has withered.\n";
            
        return text;
    }

    @Override
    public int getColor() {
        return 4;
    }

    @Override
    public String getText(Tile tile) {
        return "Withered";
    }

    @Override
    public boolean canPickaxe() {
        return false;
    }

    @Override
    public boolean canPlow() {
        return false;
    }
    
    @Override
    public boolean canWater() {
        return false;
    }
    
    @Override
    public boolean canFertilize() {
        return false;
    }

    @Override
    public boolean canShovel() {
        return true;
    }
}

class RockState implements TileState {
    @Override
    public void updateStatus(Tile tile) {
        if (!tile.getHasRock()) {
            tile.setCurrentState(new UnplowedState());
        }
    }

    @Override
    public String getStatus() {
        return "ROCK";
    }

    @Override
    public String getStatusText(Player playerModel, Tile tile) {
        return "";
    }

    @Override
    public int getColor() {
        return 1;
    }

    @Override
    public String getText(Tile tile) {
        return "Rock";
    }

    @Override
    public boolean canPickaxe() {
        return true;
    }

    @Override
    public boolean canPlow() {
        return false;
    }
    
    @Override
    public boolean canWater() {
        return false;
    }
    
    @Override
    public boolean canFertilize() {
        return false;
    }

    @Override
    public boolean canShovel() {
        return true;
    }
}
