package thd.gameobjects.base;

/**
 * Allows a game object's target position to be shifted.
 */
public interface ShiftableTargetPostion {
    /**
     * Shifts the target position of the game object in the desired direction.
     * @param shiftX The amount of pixels to shift in the right direction.
     * @param shiftY The amount of pixels to shift downwards;
     */
    void shiftTargetPosition(double shiftX, double shiftY);
}
