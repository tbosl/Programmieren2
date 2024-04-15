package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

class BomberMovementPattern extends AlienInvadersRandomMovementPattern {
    private int outerMarginToBorders;

    BomberMovementPattern() {
        outerMarginToBorders = 100;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return generateRandomPosition(-outerMarginToBorders, GameView.WIDTH + outerMarginToBorders);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        double targetXCoordinate = referencePositions[0].getX() < 0 ? GameView.WIDTH + outerMarginToBorders : -outerMarginToBorders;
        return generatePositionWithRandomY(targetXCoordinate);
    }
}
