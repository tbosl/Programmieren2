package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class LaserProjectileMovementPattern extends MovementPattern {

    LaserProjectileMovementPattern() {
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return new Position(referencePositions[0].getX() + 75, referencePositions[0].getY() + 20);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        Position laserPosition = referencePositions[0];
        return laserPosition.getX() < 0 ? new Position(GameView.WIDTH - 50, laserPosition.getY()) : new Position(-50, laserPosition.getY());
    }
}
