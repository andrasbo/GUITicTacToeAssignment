package Model;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class PlayerWonException extends Exception {

    /**
     * Creates a new instance of <code>playerWonException</code> without detail
     * message.
     */
    public PlayerWonException() {
    }

    /**
     * Constructs an instance of <code>playerWonException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PlayerWonException(String msg) {
        super(msg);
    }
}
