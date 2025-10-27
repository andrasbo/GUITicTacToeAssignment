package Model;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class GameEndedException extends Exception {


    /**
     * Creates a new instance of <code>BoardIsFullException</code> without
     * detail message.
     */
    public GameEndedException() {
    }

    /**
     * Constructs an instance of <code>BoardIsFullException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GameEndedException(String msg) {
        super(msg);
    }
    
}
