package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class BaiterMovementPattern extends MovementPattern {
    @Override
    protected Position startPosition(Position... referencePositions) {
        return new AlienInvadersRandomMovementPattern().generateRandomPosition(0, GameView.WIDTH);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        return new Position(referencePositions[0]);
    }
}
