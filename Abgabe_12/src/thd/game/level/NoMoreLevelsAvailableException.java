package thd.game.level;

/**
 * Creates an exception for an undefined level.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {
    /**
     * Create the exception for an undefined level.
     *
     * @param message The message to be shown in the exception.
     */
    public NoMoreLevelsAvailableException(String message) {
        super(message);
    }
}
