package thd.game.managers;

import thd.gameobjects.unmovable.RemainingLive;

/**
 * This exception will be thrown, when the amount of lives exceeds the
 * maximum amount of lives {@link RemainingLive#MAXIMUM_AMOUNT_OF_LIVES}.
 *
 * @see RemainingLive#MAXIMUM_AMOUNT_OF_LIVES
 */
public class TooManyRemainingLivesException extends RuntimeException {
    /**
     * Constructs a new TooManyRemainingLivesException with the specified detail message.
     *
     * @param message The detail message.
     */
    public TooManyRemainingLivesException(String message) {
        super(message);
    }
}
