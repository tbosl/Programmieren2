package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

import java.util.Random;

class AstronautMovementPatterns extends MovementPattern {
    private boolean walkingRight;

    AstronautMovementPatterns() {
        walkingRight = true;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return new Position(new Random().nextDouble(100, GameView.WIDTH - 100), GameView.HEIGHT - 50);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        if (walkingRight) {
            walkingRight = false;
            return new Position(referencePositions[0].getX() + 50, referencePositions[0].getY());
        }
        walkingRight = true;
        return new Position(referencePositions[0].getX() - 50, referencePositions[0].getY());
    }
}
