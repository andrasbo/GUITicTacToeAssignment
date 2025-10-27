package Model;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class BoardIsFullException extends Exception {

    /**
     * Creates a new instance of <code>BoardIsFullException</code> without
     * detail message.
     */
    public BoardIsFullException() {
    }

    /**
     * Constructs an instance of <code>BoardIsFullException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BoardIsFullException(String msg) {
        super(msg);
    }
}
