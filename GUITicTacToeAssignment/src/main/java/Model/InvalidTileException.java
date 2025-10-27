package Model;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class InvalidTileException extends Exception {

    /**
     * Creates a new instance of <code>InvalidTileException</code> without
     * detail message.
     */
    public InvalidTileException() {
    }

    /**
     * Constructs an instance of <code>InvalidTileException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidTileException(String msg) {
        super(msg);
    }
}
