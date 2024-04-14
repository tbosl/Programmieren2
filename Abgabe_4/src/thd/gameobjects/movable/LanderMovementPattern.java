package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.AlienInvadersMovementPattern;
import thd.gameobjects.base.Position;

class LanderMovementPattern extends AlienInvadersMovementPattern {

    @Override
    protected Position startPosition(Position... referencePositions) {
        return generateRandomPosition(-50, GameView.WIDTH + 50);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        if (referencePositions[1].getY() < GameView.HEIGHT - 50) {
            return new Position(referencePositions[0]);
        }
        return new Position(referencePositions[1].getX(), 120);
    }
}
