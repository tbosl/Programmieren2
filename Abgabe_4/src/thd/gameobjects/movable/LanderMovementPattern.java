package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class LanderMovementPattern extends MovementPattern {

    @Override
    protected Position startPosition(Position... referencePositions) {
        return new Position(GameView.WIDTH + 200, 150);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        if (referencePositions[1].getY() < GameView.HEIGHT - 50) {
            return new Position(referencePositions[0]);
        }
        return new Position(referencePositions[1].getX(), 0);
    }
}
