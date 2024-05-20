package thd.gameobjects.base;

/**
 * Makes an object activatable during the game.
 *
 * @param <T> The type of the info object on which the activation depends.
 */
public interface ActivatableGameObject<T extends GameObject> {
    /**
     * States whether the implementing game object is supposed to be activated.
     *
     * @param info The object on which the activation depends
     * @return {@code true} if the condition to activate the activatable game object is met. Else {@code false}.
     */
    boolean tryToActivate(T info);
}
