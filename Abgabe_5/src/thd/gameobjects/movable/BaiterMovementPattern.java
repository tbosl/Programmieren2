package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

import java.util.Random;

class BaiterMovementPattern extends MovementPattern {
    @Override
    protected Position startPosition(Position... referencePositions) {
        Random random = new Random();
        return new Position(random.nextDouble(0, GameView.WIDTH), random.nextDouble(UPPER_BOUNDARY, LOWER_BOUNDARY));
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        return new Position(referencePositions[0]);
    }
}
