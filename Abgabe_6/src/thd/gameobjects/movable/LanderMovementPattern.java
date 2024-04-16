package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

class LanderMovementPattern extends AlienInvadersRandomMovementPattern {

    @Override
    protected Position startPosition(Position... referencePositions) {
        int marginToSideBorders = 50;
        return generateRandomPosition(-marginToSideBorders, GameView.WIDTH + marginToSideBorders);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        if (referencePositions[1].getY() < LOWER_BOUNDARY) {
            return new Position(referencePositions[0]);
        }
        return new Position(referencePositions[1].getX(), UPPER_BOUNDARY);
    }
}
