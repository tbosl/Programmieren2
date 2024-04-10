package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

import java.util.Random;

class BomberMovementPattern extends MovementPattern {
    private final Random random;

    BomberMovementPattern() {
        random = new Random();
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return new Position(GameView.WIDTH + 100, generateYCoordinate());
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        return referencePositions[0].getX() < 0 ? new Position(GameView.WIDTH + 50, generateYCoordinate()) : new Position(-50, generateYCoordinate());
    }

    private int generateYCoordinate() {
        return random.nextInt(230, GameView.HEIGHT-100);
    }
}
