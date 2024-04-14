package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

class EnemyProjectileMovementPattern extends ProjectileMovementPattern {
    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        Position spaceshipPosition = referencePositions[0];
        Position enemyPosition = referencePositions[1];
        return calculateStraightEndpoint(spaceshipPosition, enemyPosition);
    }

    private Position calculateStraightEndpoint(Position spaceship, Position enemy) {
        double m = (spaceship.getY() - enemy.getY()) / (spaceship.getX() - enemy.getX());
        double t = enemy.getY() - (enemy.getX() * m);
        double targetX = spaceship.getX() < enemy.getX() ? 0 : GameView.WIDTH;
        return new Position(targetX, m * targetX + t);
    }
}
