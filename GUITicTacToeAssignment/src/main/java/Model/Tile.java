package Model;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class Tile {
    private Player owner;

    /**
     *
     * @param p
     */
    public void setOwner(Player p) {
        owner = p;
    }

    /**
     *
     * @return
     */
    public Player getOwner() {
        return owner;
    }
}
