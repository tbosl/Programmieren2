package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

class BomberMovementPattern extends AlienInvadersRandomMovementPattern {
    private final int outerMarginToBorders;

    BomberMovementPattern() {
        outerMarginToBorders = 100;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return generateRandomPosition(UPPER_BOUNDARY + 100, GameView.WIDTH + outerMarginToBorders, LOWER_BOUNDARY - 100, -outerMarginToBorders);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        double targetXCoordinate = referencePositions[0].getX() < 0 ? GameView.WIDTH + outerMarginToBorders : -outerMarginToBorders;
        return generatePositionWithRandomY(targetXCoordinate, UPPER_BOUNDARY + 100, LOWER_BOUNDARY - 100);
    }
}
