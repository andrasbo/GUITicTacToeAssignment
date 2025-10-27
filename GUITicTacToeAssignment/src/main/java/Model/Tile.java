package Model;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class Tile {
    private Player owner;

    public void setOwner(Player p) {
        owner = p;
    }
    public Player getOwner() {
        return owner;
    }
}
