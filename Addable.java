/**
 * This marks the classes that have the {@link #add(Crop) add} method.
 */

public interface Addable {
    
    /**
     * Adds the sustainance to the crop
     *
     * @param crop the tile to be added sustainance
     * @return true if the sustainance was successfully added, false otherwise
     */
    public boolean add(Crop crop);
}