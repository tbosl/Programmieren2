import java.util.Objects;

public class EqualsComparisons {
    // Astronaut
    //    @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        Astronaut other = (Astronaut) o;
    //        return super.equals(other)
    //               && pickedUp == other.pickedUp
    //               && lander.equals(other.lander)
    //               && astronautMovementPatterns.equals(other.astronautMovementPatterns);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), pickedUp, lander, astronautMovementPatterns);
    //    }

    // Baiter
    //   @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        Baiter other = (Baiter) o;
    //        return super.equals(other)
    //               && movementPattern.equals(other.movementPattern)
    //               && spaceship.equals(other.spaceship);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), movementPattern, spaceship);
    //    }

    // Bomber
    //     @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        Bomber other = (Bomber) o;
    //        return super.equals(other)
    //               && movementPattern.equals(other.movementPattern)
    //               && currentBombDroppingIntervallInMilliseconds == other.currentBombDroppingIntervallInMilliseconds;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), movementPattern, currentBombDroppingIntervallInMilliseconds);
    //    }

    // CollidingGameObject
    //  @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        return super.equals(o)
    //               && hitBoxRectangle.equals(((CollidingGameObject) o).hitBoxRectangle);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), hitBoxRectangle);
    //    }

    // EnemyGameObject
    //     @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        EnemyGameObject other = (EnemyGameObject) o;
    //        return super.equals(o)
    //               && collidingGameObjectsForPathDecision.equals(other.collidingGameObjectsForPathDecision)
    //               && positionBeforeMoving.equals(other.positionBeforeMoving)
    //               && pointsOnDestruction == other.pointsOnDestruction;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), collidingGameObjectsForPathDecision, positionBeforeMoving, pointsOnDestruction);
    //    }

    // EnemyProjectile
    //    @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        EnemyProjectile other = (EnemyProjectile) o;
    //        return super.equals(other)
    //               && movementPattern.equals(other.movementPattern)
    //               && colorCycleManager.equals(other.colorCycleManager);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), movementPattern, colorCycleManager);
    //    }

    // HeaderFrame
    //  @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        HeaderFrame other = (HeaderFrame) o;
    //        return super.equals(other)
    //               && frameColor.equals(frameColor);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), frameColor);
    //    }

    // Lander
    //  @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        Lander other = (Lander) o;
    //        return super.equals(other)
    //               && landerMovementPattern.equals(other.landerMovementPattern)
    //               && grabbedAstronaut.equals(other.grabbedAstronaut)
    //               && spaceship.equals(other.spaceship)
    //               && waitTimeBeforeAttackingAstronaut == other.waitTimeBeforeAttackingAstronaut
    //               && spawnTime == other.spawnTime
    //               && attackingAllowed == other.attackingAllowed
    //               && outerMarginToSideBorders == other.outerMarginToSideBorders;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), landerMovementPattern, grabbedAstronaut, spaceship,
    //                waitTimeBeforeAttackingAstronaut, spawnTime, attackingAllowed, outerMarginToSideBorders);
    //    }

    // LaserProjectile
    //  @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        LaserProjectile other = (LaserProjectile) o;
    //        return super.equals(other);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return super.hashCode();
    //    }

    // Mutant
    //   @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        Mutant other = (Mutant) o;
    //        return super.equals(other)
    //               && movementPattern.equals(other.movementPattern)
    //               && spaceship.equals(other.spaceship)
    //               && currentDoubleShootIntervallInMilliseconds == other.currentDoubleShootIntervallInMilliseconds;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), movementPattern, spaceship, currentDoubleShootIntervallInMilliseconds);
    //    }

    // Pod
    //    @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        Pod other = (Pod) o;
    //        return super.equals(other)
    //               && randomMovementPattern.equals(other.randomMovementPattern)
    //               && spaceship.equals(other.spaceship);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), randomMovementPattern, spaceship);
    //    }

    // Projectile
    //    @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        return super.equals(o);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return super.hashCode();
    //    }

    // RemainingLive
//    @Override
//    public boolean equals(Object o) {
//        if (o == this) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        RemainingLive other = (RemainingLive) o;
//        return super.equals(other)
//               && liveIndex == other.liveIndex;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), liveIndex);
//    }

    // ScannerFrame
    //    @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        ScannerFrame other = (ScannerFrame) o;
    //        return super.equals(other)
    //               && blockImage.equals(other.blockImage);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), blockImage);
    //    }

    // Score
    //    @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        Score other = (Score) o;
    //        return super.equals(other)
    //               && scoreBlockImages.equals(other.scoreBlockImages)
    //               && colorCycleManager.equals(other.colorCycleManager);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), scoreBlockImages, colorCycleManager);
    //    }

    // SmartBomb
    //    @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        SmartBomb other = (SmartBomb) o;
    //        return super.equals(other)
    //               && smartBombIndex == other.smartBombIndex;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), smartBombIndex);
    //    }

    // Spaceship
    //   @Override
    //    public boolean equals(Object o) {
    //        if (o == this) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //        Spaceship other = (Spaceship) o;
    //        return super.equals(other)
    //               && shotDurationInMilliseconds == other.shotDurationInMilliseconds
    //               && shotAvailable == other.shotAvailable
    //               && smartBombDetonatable == other.smartBombDetonatable;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), shotDurationInMilliseconds, shotAvailable, smartBombDetonatable);
    //    }

    // Swarmer
//    @Override
//    public boolean equals(Object o) {
//        if (o == this) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        Swarmer other = (Swarmer) o;
//        return super.equals(other)
//               && movementPattern.equals(other.movementPattern)
//               && spaceship.equals(other.spaceship);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), movementPattern, spaceship);
//    }
}
