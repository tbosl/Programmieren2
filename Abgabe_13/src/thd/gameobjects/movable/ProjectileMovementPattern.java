package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class ProjectileMovementPattern extends MovementPattern {
    @Override
    protected Position startPosition(Position... referencePositions) {
        return new Position(referencePositions[0].getX(), referencePositions[0].getY());
    }
}
