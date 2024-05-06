package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.Position;

class LanderMovementPattern extends AlienInvadersRandomMovementPattern {
    boolean astronautGrabbed;

    LanderMovementPattern() {
        astronautGrabbed = false;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        int marginToSideBorders = 50;
        return generateRandomPosition(marginToSideBorders, GamePlayManager.ABSOLUTE_WORLD_LENGTH - marginToSideBorders);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        if (!astronautGrabbed) {
            return new Position(referencePositions[0]);
        }
        return new Position(referencePositions[1].getX(), UPPER_BOUNDARY);
    }
}
