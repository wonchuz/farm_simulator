/**
 * This is the Tile class for the farming simulator game for MCO2.
 * <p>
 * Methods include tile actions in the game; checking if seed can be planted,
 * adding and removing rocks, plowing the tile, planting
 * a seed, removing crop, retrieving crop, getting tile information, and getting
 * the crop of the tile.
 */
public class Tile {
    private static final int LEFT_TILE_OFFSET = 1;
    private static final int RIGHT_TILE_OFFSET = 1;
    private static final int ABOVE_TILE_OFFSET = 10;
    private static final int LEFT_CORNER_TILE_OFFSET = 9;
    private static final int RIGHT_CORNER_TILE_OFFSET = 11;
    private boolean hasRock;
    private boolean isAvailable;
    private boolean isPlowed;
    private Crop crop;
    private TileState state;

    /**
     * This is the constructor for the Tile class.
     */
    public Tile() {
        this.hasRock = false;
        this.isAvailable = true;
        this.isPlowed = false;
        this.crop = null;
        this.state = new UnplowedState();
    }

    /**
     * Checks whether a tile is plantable or not.
     *
     * @param farmLot the farmlot to be planted on
     * @param seed    the seed to be planted
     * @param index   the index of the tile to be planted on
     * @return true if the seed can be planted on the tile, otherwise false
     */
    public boolean canPlant(Tile[] farmLot, Seed seed, int index) {
        boolean result = true;

        // Check if tile is not occupied
        if (this.isAvailable == true && this.isPlowed == true) {
            String name = seed.getName();

            // Seed is a Fruit Tree
            if (name.equalsIgnoreCase("Mango") || name.equalsIgnoreCase("Apple")) {
                result = false;

                // Check if tile is on the sides or corners
                if (((index + 1) >= ABOVE_TILE_OFFSET) && (((index + 1) % ABOVE_TILE_OFFSET) > LEFT_TILE_OFFSET)
                        && ((index + 1) <= 41)) {
                    // Check if the tiles on the left and right of the tile are not occupied
                    if (farmLot[index - LEFT_TILE_OFFSET].getIsAvailable() == true
                            && farmLot[index + RIGHT_TILE_OFFSET].getIsAvailable() == true) {
                        result = true;

                        // Check if tiles around it (above and below) are occupied
                        for (int i = LEFT_CORNER_TILE_OFFSET; i <= RIGHT_CORNER_TILE_OFFSET; i++) {
                            if (farmLot[index - i].getIsAvailable() == false
                                    || farmLot[index + i].getIsAvailable() == false) {
                                result = false;
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Adds rock on tile.
     */
    public void addRock() {
        this.hasRock = true;
        this.isAvailable = false;
        this.getState().updateStatus(this);
    }

    /**
     * Removes rock on tile.
     * 
     * @return true if tile has rock, false if otherwise
     */
    public boolean removeRock() {
        if (this.hasRock == true) {
            this.hasRock = false;
            this.isAvailable = true;
            this.getState().updateStatus(this);
            return true;
        }
        return false;
    }

    /**
     * Plows the tile.
     *
     * @return true if tile is unplowed, false if otherwise
     */
    public boolean plowTile() {
        if (this.isAvailable == true && this.isPlowed == false && this.hasRock == false) {
            this.isPlowed = true;
            this.getState().updateStatus(this);
            return true;
        }
        return false;
    }

    /**
     * Adds a crop to this tile.
     * 
     * @param crop the crop to be added
     */
    public void addCrop(Crop crop) {
        this.crop = crop;
        this.isAvailable = false;
    }

    /**
     * Removes a crop on the tile.
     */
    public void removeCrop() {
        this.crop = null;
        this.isAvailable = true;
        this.isPlowed = false;
    }

    /**
     * Removes and retrieves the crop on the tile.
     * 
     * @param tile chosen tile
     * @return the crop that was removed
     */
    public Crop retrieveCrop(Tile tile) {
        Crop crop = null;
        if (this.crop != null) {
            if (this.crop.isReady() == true) {
                crop = this.crop;
                tile.removeCrop();
            } else {
                this.crop.wither();
            }
            tile.getState().updateStatus(tile);
        }
        return crop;
    }

    /**
     * Gets the current status if there is a rock on the tile.
     *
     * @return the current rock status of the tile
     */
    public boolean getHasRock() {
        return this.hasRock;
    }

    /**
     * Gets the availablity of the tile (whether there is a crop on it or not).
     *
     * @return the availability of the tile
     */
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    /**
     * Gets the plow status of the tile (whether it is plowed or not).
     *
     * @return the plowed status of the tile
     */
    public boolean getIsPlowed() {
        return this.isPlowed;
    }

    /**
     * Gets the occupying crop of the tile (if any).
     *
     * @return the occupying crop of the tile
     */
    public Crop getCrop() {
        if (this.crop == null) {
            return null;
        }
        return this.crop;
    }

    public TileState getState() {
        return this.state;
    }

    public void setCurrentState(TileState newState) {
        this.state = newState;
    }
}


