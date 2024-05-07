package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

class BomberMovementPattern extends AlienInvadersRandomMovementPattern {
    private final int outerMarginToBorders;

    BomberMovementPattern() {
        outerMarginToBorders = 100;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return generateRandomPosition(UPPER_BOUNDARY + outerMarginToBorders, GamePlayManager.ABSOLUTE_WORLD_LENGTH - outerMarginToBorders, LOWER_BOUNDARY - outerMarginToBorders, outerMarginToBorders);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        int xCorrection = 10;
        double targetXCoordinate = referencePositions[0].getX() <= outerMarginToBorders + xCorrection ? GameView.WIDTH - outerMarginToBorders : outerMarginToBorders;
        return generatePositionWithRandomY(targetXCoordinate, UPPER_BOUNDARY + outerMarginToBorders, LOWER_BOUNDARY - outerMarginToBorders);
    }
}
