package thd.gameobjects.base;


import thd.game.utilities.GameView;

import java.util.HashMap;
import java.awt.Color;

/**
 * This class manages to provide a color cycle on a set of colors.
 */
public class ColorCycleManager {
    private final GameView gameView;
    private final char[] colors;
    private int colorIndex;
    private final int periodDuration;
    private final HashMap<Character, Color> colorCodeToColor;

    /**
     * Constructs a ColorCycleManager object.
     *
     * @param gameView       The GameView.
     * @param periodDuration The duration of each color period in milliseconds.
     */
    public ColorCycleManager(GameView gameView, int periodDuration) {
        this.gameView = gameView;
        this.periodDuration = periodDuration;
        colors = new char[]{'r', 'P', 'B', 'b', 'C', 'W', 'y', 'Y', 'O'};
        colorCodeToColor = new HashMap<>();
        initializeColorDictionary();
    }

    /**
     * Finds the current color code in the cycle.
     *
     * @return the current color code as char to be used
     * in {@link GameView#addBlockImageToCanvas(String, double, double, double, double)}.
     * @see GameView#addBlockImageToCanvas(String, double, double, double, double).
     */
    public char findCurrentColorCode() {
        updateColor();
        return colors[colorIndex];
    }

    /**
     * Finds the current color in the cycle.
     *
     * @return The current color as {@code Color}
     */
    public Color findCurrentColor() {
        updateColor();
        return colorCodeToColor.get(colors[colorIndex]);
    }

    private void updateColor() {
        if (gameView.timer(periodDuration, this)) {
            colorIndex = colorIndex > colors.length - 2 ? 0 : colorIndex + 1;
        }
    }

    private void initializeColorDictionary() {
        colorCodeToColor.put('r', Color.RED.brighter());
        colorCodeToColor.put('P', Color.PINK);
        colorCodeToColor.put('B', Color.BLUE);
        colorCodeToColor.put('b', Color.BLUE.brighter());
        colorCodeToColor.put('C', Color.CYAN);
        colorCodeToColor.put('W', Color.WHITE);
        colorCodeToColor.put('y', Color.YELLOW.brighter());
        colorCodeToColor.put('Y', Color.YELLOW);
        colorCodeToColor.put('O', Color.ORANGE);
    }
}
