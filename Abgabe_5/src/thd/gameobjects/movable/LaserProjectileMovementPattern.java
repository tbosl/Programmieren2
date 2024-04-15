package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

class LaserProjectileMovementPattern extends ProjectileMovementPattern {

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        Position projectilePosition = referencePositions[0];
        return projectilePosition.getX() < 0 ? new Position(GameView.WIDTH - 50, projectilePosition.getY()) : new Position(-50, projectilePosition.getY());
    }
}
