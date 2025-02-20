import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * This is the controller class for the MVC for the farming simulator game for MCO2.
 */
public class PlayerController {
    private Player playerModel;
    private int currentTileIndex;
    private PlayerView playerView;
    private File input;

    private Seed turnip;
    private Seed carrot;
    private Seed potato;
    private Seed rose;
    private Seed tulips;
    private Seed sunflower; 
    private Seed mango;
    private Seed apple;

    private Plow plow;
    private WateringCan wc;
    private Fertilizer f;
    private Pickaxe pickaxe;
    private Shovel shovel;
    
    // --- State Management: TileStatus Enum ---
    private enum TileStatus {
        UNPLOWED,
        PLOWED,
        ROCK,
        GROWING,
        READY,
        WITHERED
    }
    
    // Helper method to determine the current status of a tile.
    private TileStatus determineTileStatus(Tile tile) {
        if(tile.getCrop() != null) {
            if(tile.getCrop().getIsWithered()) {
                return TileStatus.WITHERED;
            } else if(tile.getCrop().isReady() && tile.getCrop().getHarvestDay() == playerModel.getCurrentDay()) {
                return TileStatus.READY;
            } else {
                return TileStatus.GROWING;
            }
        } else {
            if(tile.getHasRock()) {
                return TileStatus.ROCK;
            } else if(tile.getIsPlowed()) {
                return TileStatus.PLOWED;
            } else {
                return TileStatus.UNPLOWED;
            }
        }
    }

    public PlayerController(PlayerView view, Player player, File input) {
        playerView = view;
        this.playerModel = player;
        this.input = input;
        this.currentTileIndex = -1;

        // Create seeds using the builder pattern
        turnip = new Seed.SeedBuilder("Turnip", 2, 5, 6, 5)
            .waterRequirements(1, 2)
            .fertilizerRequirements(0, 1)
            .produceRange(1, 2)
            .build();

        carrot = new Seed.SeedBuilder("Carrot", 3, 10, 9, 7.5)
            .waterRequirements(1, 2)
            .fertilizerRequirements(0, 1)
            .produceRange(1, 2)
            .build();

        potato = new Seed.SeedBuilder("Potato", 5, 20, 3, 12.5)
            .waterRequirements(3, 4)
            .fertilizerRequirements(1, 2)
            .produceRange(1, 10)
            .build();

        rose = new Seed.SeedBuilder("Rose", 1, 5, 5, 2.5)
            .waterRequirements(1, 2)
            .fertilizerRequirements(0, 1)
            .fixedProduce(1)
            .build();

        tulips = new Seed.SeedBuilder("Tulips", 2, 10, 9, 5)
            .waterRequirements(2, 3)
            .fertilizerRequirements(0, 1)
            .fixedProduce(1)
            .build();

        sunflower = new Seed.SeedBuilder("Sunflower", 3, 20, 19, 7.5)
            .waterRequirements(2, 3)
            .fertilizerRequirements(1, 2)
            .fixedProduce(1)
            .build();

        mango = new Seed.SeedBuilder("Mango", 10, 100, 8, 25)
            .waterRequirements(7, 7)
            .fertilizerRequirements(4, 4)
            .produceRange(5, 15)
            .build();

        apple = new Seed.SeedBuilder("Apple", 10, 100, 8, 25)
            .waterRequirements(7, 7)
            .fertilizerRequirements(5, 5)
            .produceRange(10, 15)
            .build();

        // Tools
        plow = new Plow(0, 0.5);
        wc = new WateringCan(0, 0.5);
        f = new Fertilizer(10, 4);
        pickaxe = new Pickaxe(50, 15);
        shovel = new Shovel(7, 2);

        updateTextPrompt();
        updateActionButtons();
        updateButtonCosts();

        playerView.setTileButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                currentTileIndex = playerView.getTileButtonIndex(e.getSource());
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setUsePickaxeButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.useTool(pickaxe, playerModel.getTile(currentTileIndex));
                updateTextPrompt();
                updateTiles();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setUsePlowButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.useTool(plow, playerModel.getTile(currentTileIndex));
                updateTextPrompt();
                updateTiles();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setUseWateringCanButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.useTool(wc, playerModel.getTile(currentTileIndex));
                updateTextPrompt();
                updateTiles();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setUseFertilizerButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.useTool(f, playerModel.getTile(currentTileIndex));
                updateTextPrompt();
                updateTiles();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setUseShovelButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.useTool(shovel, playerModel.getTile(currentTileIndex));
                updateTextPrompt();
                updateTiles();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setHarvestCropButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.harvestCrop(playerModel.getTile(currentTileIndex));

                DecimalFormat df = new DecimalFormat("0.00");
                String text = "";
                text += "Products Produced: " + playerModel.getLastProductsProduced() + "\n";
                text += "Harvest Total: " + df.format(playerModel.getLastHarvestTotal()) + "\n";
                text += "Water Bonus: " + df.format(playerModel.getLastWaterBonus()) + "\n";
                text += "Fertilizer Bonus: " + df.format(playerModel.getLastFertilizerBonus()) + "\n--------------------\n";
                text += "Total Earnings: " + df.format(playerModel.getLastHarvestPrice()) + "\n";

                playerView.popUpMessage("SUCCESSFULLY HARVEST!", text);
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setPlantTurnipButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.plantSeed(playerModel.getTile(currentTileIndex), turnip);
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setPlantCarrotButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.plantSeed(playerModel.getTile(currentTileIndex), carrot);
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setPlantPotatoButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.plantSeed(playerModel.getTile(currentTileIndex), potato);
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setPlantRoseButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.plantSeed(playerModel.getTile(currentTileIndex), rose);
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setPlantTulipsButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.plantSeed(playerModel.getTile(currentTileIndex), tulips);
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setPlantSunflowerButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.plantSeed(playerModel.getTile(currentTileIndex), sunflower);
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setPlantMangoButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.plantSeed(playerModel.getTile(currentTileIndex), mango);
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setPlantAppleButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.plantSeed(playerModel.getTile(currentTileIndex), apple);
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setRegisterButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.register(); // Modified
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                updateButtonCosts();
                checkGameState();
            }
        });

        playerView.setAdvanceDayButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel.advanceDay();
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
                checkGameState();
            }
        });

        playerView.setViewSeedToolInfoButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String text = "";
                
                text += "SEEDS\n";
                text += "[1] Cost\n[2] Water Needs(Bonus Limit)\n[3] Fertilizer Needs(Bonus Limit)\n[4] Harvest Time\n[5] Products Produced\n[6] Base Selling Per Piece\n[7] Base Experience\n\n";
                
                text += "[Root Crops]\n";
                text += "Turnip   [1] 5   [2] 1(2)   [3] 0(1)   [4] 2 Days   [5] 1-2   [6] 6   [7] 5\n";
                text += "Carrot   [1] 10   [2] 1(2)   [3] 0(1)   [4] 3 Days   [5] 1-2   [6] 9   [7] 7.5\n";
                text += "Potato   [1] 20   [2] 3(4)   [3] 1(2)   [4] 5 Days   [5] 1-10   [6] 3   [7] 12.5\n\n";

                text += "[Flowers]\n";
                text += "Rose   [1] 5   [2] 1(2)   [3] 0(1)   [4] 1 Day   [5] 1   [6] 5   [7] 2.5\n";
                text += "Tulips   [1] 10   [2] 2(3)   [3] 0(1)   [4] 2 Days   [5] 1   [6] 9   [7] 5\n";
                text += "Sunflower   [1] 20   [2] 2(3)   [3] 1(2)   [4] 3 Days   [5] 1   [6] 19   [7] 7.5\n\n";

                text += "[Fruit Trees]\n";
                text += "Mango   [1] 100   [2] 7(7)  [3] 4(4)   [4] 10 Days   [5] 5-15   [6] 8   [7] 25\n";
                text += "Apple   [1] 100   [2] 7(7)   [3] 5(5)   [4] 10 Days   [5] 10-15   [6] 5   [7] 25\n\n";
            
                text += "TOOLS\n";
                text += "[1] Cost\n[2] Experience\n\n";
                text += "Pickaxe  [1] 50  [2] 15\n";
                text += "Plow  [1] 0  [2] 0.5\n";
                text += "Watering Can  [1] 0  [2] 0.5\n";
                text += "Fertilizer  [1] 10  [2] 4\n";
                text += "Shovel  [1] 7  [2] 2\n";
                
                playerView.popUpMessage("Seed Information", text);
            }
        });

        playerView.setViewRegisterInfoButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String text = "";
                text += "[Registered Farmer]\n";
                text += "Level Requirement: 5\n";
                text += "Bonus Earnings per Produce: +1\n";
                text += "Seed Cost Reduction: -1\n";
                text += "Water Bonus Increase: 0\n";
                text += "Fertilizer Bonus Increase: 0\n";
                text += "Registration Fee: 200\n\n";

                text += "[Distinguished Farmer]\n";
                text += "Level Requirement: 10\n";
                text += "Bonus Earnings per Produce: +2\n";
                text += "Seed Cost Reduction: -2\n";
                text += "Water Bonus Increase: +1\n";
                text += "Fertilizer Bonus Increase: 0\n";
                text += "Registration Fee: 300\n\n";

                text += "[Legendary Farmer]\n";
                text += "Level Requirement: 15\n";
                text += "Bonus Earnings per Produce: +4\n";
                text += "Seed Cost Reduction: -3\n";
                text += "Water Bonus Increase: +2\n";
                text += "Fertilizer Bonus Increase: +1\n";
                text += "Registration Fee: 400\n\n";

                playerView.popUpMessage("Register Information", text);
            }
        });

        playerView.setStartNewGameButtonListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                playerModel = new Player(input);
                playerView.enableAll();
                currentTileIndex = -1;
                updateTiles();
                updateTextPrompt();
                updateActionButtons();
            }
        });

        updateTiles();
    }

    public void updateTextPrompt() {
        String text = "";

        if(currentTileIndex != -1) {
            Tile tile = playerModel.getTile(currentTileIndex);
            text += "[TILE " + (currentTileIndex + 1) + "]\n";

            if(tile.getCrop() != null) {
                text += tile.getCrop().getSeed().getName() + "\n";
                text += "Watered " + tile.getCrop().getWaterCount() + "/" + tile.getCrop().getSeed().getWaterNeeds() + " (" 
                        + (tile.getCrop().getSeed().getWaterBonus() + this.playerModel.getFarmerType().getWaterBonusIncrease()) + ") times\n";
                text += "Fertilized " + tile.getCrop().getFertilizerCount() + "/" + tile.getCrop().getSeed().getFertilizerNeeds() + " (" 
                        + (tile.getCrop().getSeed().getFertilizerBonus() + this.playerModel.getFarmerType().getFertilizerBonusIncrease()) + ") times\n";
                
                TileStatus status = determineTileStatus(tile);
                if(status == TileStatus.READY)
                    text += "READY FOR HARVEST!!\n";
                else if(status == TileStatus.GROWING)
                    text += (tile.getCrop().getHarvestDay() - this.playerModel.getCurrentDay()) + " days until harvest day...\n";
                else if(status == TileStatus.WITHERED)
                    text += "Crop has withered.\n";
            }

            text += "\n";
        }

        text += "DAY " + this.playerModel.getCurrentDay() + "\n[";
        text += "Level " + ((int) this.playerModel.getExperience() / 100) + " ";
        text += this.playerModel.getFarmerType().getName(); // Modified/Refactored

        text += "]\n" + "Objectcoins: " + String.format("%.2f",  this.playerModel.getObjectcoins()) + "\n" + "Experience: " + this.playerModel.getExperience() + "\n";

        boolean isHarvestDay = false;
        boolean cropsHaveWithered = false;

        for(int a = 0; a < 50; a++) {
            Tile t = this.playerModel.getTile(a);
            if(t.getCrop() != null && t.getCrop().isReady() && t.getCrop().getHarvestDay() == this.playerModel.getCurrentDay())
                isHarvestDay = true;
            if(t.getCrop() != null && t.getCrop().getIsWithered())
                cropsHaveWithered = true;
        }

        if(isHarvestDay)
            text += "IT'S HARVEST DAY!!\n";

        if(cropsHaveWithered)
            text += "Oh no! Crops have withered...\n" ;

        playerView.setTextPrompt(text);
    }

    public void updateTiles() {
        String text;
        int color = 0;

        for(int a = 0; a < 50; a++){
            Tile tile = this.playerModel.getTile(a);
            TileStatus status = determineTileStatus(tile);
            switch(status) {
                case ROCK:
                    text = "Rock";
                    color = 1;
                    break;
                case PLOWED:
                    text = "Plowed";
                    color = -1;
                    break;
                case UNPLOWED:
                    text = "Unplowed";
                    color = 0;
                    break;
                case GROWING:
                    text = tile.getCrop().getSeed().getName();
                    color = 2;
                    break;
                case READY:
                    text = tile.getCrop().getSeed().getName();
                    color = 3;
                    break;
                case WITHERED:
                    text = "Withered";
                    color = 4;
                    break;
                default:
                    text = "";
                    color = 0;
                    break;
            }
            playerView.setTileButton(a, text, color);
        }
    }

    public void updateButtonCosts() {
        //update costs
        double pickaxeCost = this.pickaxe.getCost();
        double plowCost = this.plow.getCost();
        double wateringCanCost = this.wc.getCost();
        double fertilizerCost = this.f.getCost();
        double shovelCost = this.shovel.getCost();

        double seedCostReduction = this.playerModel.getFarmerType().getSeedCostReduction();
        double turnipCost = this.turnip.getCost() - seedCostReduction;
        double carrotCost = this.carrot.getCost() - seedCostReduction;
        double potatoCost = this.potato.getCost() - seedCostReduction;

        double roseCost = this.rose.getCost() - seedCostReduction;
        double tulipsCost = this.tulips.getCost() - seedCostReduction;
        double sunflowerCost = this.sunflower.getCost() - seedCostReduction;

        double mangoCost = this.mango.getCost() - seedCostReduction;
        double appleCost = this.apple.getCost() - seedCostReduction;

        this.playerView.addButtonCosts(pickaxeCost, plowCost, wateringCanCost, fertilizerCost, shovelCost, 
                                       turnipCost, carrotCost, potatoCost, roseCost, tulipsCost, sunflowerCost, mangoCost, appleCost);
    }

    //UPDATE
    public void updateActionButtons() {
        if(currentTileIndex != -1) {
            Tile tile = this.playerModel.getTile(currentTileIndex);
            double seedCostReduction = this.playerModel.getFarmerType().getSeedCostReduction();
            TileStatus status = determineTileStatus(tile);

            // Tool buttons
            boolean pickaxeEnabled = (status == TileStatus.ROCK) && (this.playerModel.getObjectcoins() >= 50);
            boolean plowEnabled = (status == TileStatus.UNPLOWED);
            // watering can enabled when a crop is present and not withered (i.e., growing or ready)
            boolean wateringCanEnabled = ((status == TileStatus.GROWING || status == TileStatus.READY));
            boolean fertilizerEnabled = wateringCanEnabled && (this.playerModel.getObjectcoins() >= this.f.getCost());
            boolean shovelEnabled = (this.playerModel.getObjectcoins() >= this.shovel.getCost());
            
            this.playerView.setToolButtons(pickaxeEnabled, plowEnabled, wateringCanEnabled, fertilizerEnabled, shovelEnabled);

            // Plant buttons
            boolean harvestCropEnabled = tile.getCrop() != null && tile.getCrop().isReady() && this.playerModel.getCurrentDay() == tile.getCrop().getHarvestDay();

            boolean turnipEnabled = tile.canPlant(this.playerModel.getFarmLot(), this.turnip, currentTileIndex) && 
                                      this.playerModel.getObjectcoins() >= (this.turnip.getCost() - seedCostReduction);
            boolean carrotEnabled = tile.getCrop() == null && tile.getIsPlowed() && 
                                      this.playerModel.getObjectcoins() >= (this.carrot.getCost() - seedCostReduction);
            boolean potatoEnabled = tile.getCrop() == null && tile.getIsPlowed() && 
                                      this.playerModel.getObjectcoins() >= (this.potato.getCost() - seedCostReduction);

            boolean roseEnabled = tile.getCrop() == null && tile.getIsPlowed() && 
                                      this.playerModel.getObjectcoins() >= (this.rose.getCost() - seedCostReduction);
            boolean tulipsEnabled = tile.getCrop() == null && tile.getIsPlowed() && 
                                      this.playerModel.getObjectcoins() >= (this.tulips.getCost() - seedCostReduction);
            boolean sunflowerEnabled = tile.getCrop() == null && tile.getIsPlowed() && 
                                      this.playerModel.getObjectcoins() >= (this.sunflower.getCost() - seedCostReduction);

            boolean mangoEnabled = tile.canPlant(this.playerModel.getFarmLot(), this.mango, currentTileIndex) && 
                                      this.playerModel.getObjectcoins() >= (this.mango.getCost() - seedCostReduction);
            boolean appleEnabled = tile.canPlant(this.playerModel.getFarmLot(), this.apple, currentTileIndex) && 
                                      this.playerModel.getObjectcoins() >= (this.apple.getCost() - seedCostReduction);

            this.playerView.setPlantButtons(harvestCropEnabled, turnipEnabled, carrotEnabled, potatoEnabled, 
                                            roseEnabled, tulipsEnabled, sunflowerEnabled, mangoEnabled, appleEnabled);
        }
        else {
            this.playerView.setToolButtons(false, false, false, false, false);
            this.playerView.setPlantButtons(false, false, false, false, false, false, false, false, false);
        }

        // Farm buttons
        boolean register = this.playerModel.canRegister(); // Modified

        this.playerView.setFarmButtons(register);
    }

    public void checkGameState() {
        if(!this.playerModel.getIsRunning()) {
            this.playerView.disableAll();
            this.playerView.setTextPrompt("GAME OVER!");
            this.playerView.popUpMessage("Game Over", "Game Over! Please click on START NEW GAME if you wish to start a new game or exit the program. Thank you for playing!");
        }
    }
}
