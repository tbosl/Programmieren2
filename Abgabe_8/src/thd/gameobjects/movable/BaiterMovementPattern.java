package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class BaiterMovementPattern extends MovementPattern {
    @Override
    protected Position startPosition(Position... referencePositions) {
        int xMargin = 100;
        return new AlienInvadersRandomMovementPattern().generateRandomPosition(xMargin, GamePlayManager.ABSOLUTE_WORLD_LENGTH - xMargin);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        return new Position(referencePositions[0]);
    }
}
