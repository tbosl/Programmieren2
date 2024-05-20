package thd.game.level;

import java.awt.*;

/**
 * Represents a level in the game.
 */
public class Level {

    /**
     * The name of the level.
     */
    public String name;

    /**
     * The number of the level.
     */
    public int number;

    /**
     * The world of the level.
     */
    public String world;

    /**
     * The column offset of the world of the level.
     */
    public int worldOffsetColumns;

    /**
     * The line offset of the world of the level.
     */
    public int worldOffsetLines;

    /**
     * The amount of astronauts in the beginning.
     */
    public int amountOfAstronauts;

    /**
     * The level of the enemies that are allowed in the level.
     */
    public int enemyLevel;

    /**
     * The amount of enemies that will be spawned in the beginning.
     */
    public int amountOfEnemiesAtStart;

    /**
     * The amount of enemies which will be spawned throughout the game.
     */
    public int amountOfEnemiesToSpawnDuringGame;

    /**
     * The color in which the stars in the background will appear.
     */
    public Color starColor;

    /**
     * The difficulty of the game.
     */
    public static Difficulty difficulty = Difficulty.STANDARD;

    boolean difficultyEasy() {
        return difficulty == Difficulty.EASY;
    }
}
