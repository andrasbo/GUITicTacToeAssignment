package Model;


/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public enum Player {
    X, O;
    public static Player[] getPlayers() {
        return Player.values();
    }
    public static Player nextPlayer(Player p) {
        if (p != null) {
            switch (p) {
                case Player.X : return Player.O;
                case Player.O : return Player.X;
            }
        }
        return null;
    }
}
