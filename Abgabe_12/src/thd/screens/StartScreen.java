package thd.screens;

import thd.game.level.Difficulty;
import thd.game.utilities.GameView;

/**
 * Represents the start screen of the game.
 */
public class StartScreen {
    private final GameView gameView;
    private Difficulty selectedDifficulty;

    /**
     * Creates a new start screen.
     *
     * @param gameView The GameView.
     */
    public StartScreen(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Show the start screen with a preselected difficulty.
     *
     * @param preselectedDifficulty The difficulty which should be preselected.
     */
    public void showStartScreenWithPreselectedDifficulty(Difficulty preselectedDifficulty) {
        String title = "Defender";
        String description = """



                                    Safe the astronauts and protect the spaceship from the arriving alien invaders.
                                        You loose if you have no more lives or all astronauts are taken away.

                                    If you are in the trouble use a smart bomb to kill all aliens visible on screen.

                                                                    Controls:

                                                                    W -> Up
                                                                    A -> Left
                                                                    S -> Down
                                                                    D -> Right
                                                                Space -> Shoot
                                                                    B -> Detonate Smart-Bomb.
                """;
        boolean easyPreselection = preselectedDifficulty.equals(Difficulty.EASY);

        boolean difficultySetToEasy = gameView.showSimpleStartScreen(title, description, easyPreselection);
        selectedDifficulty = difficultySetToEasy ? Difficulty.EASY : Difficulty.STANDARD;
    }

    /**
     * Gets the selected difficulty.
     *
     * @return The selected difficulty.
     */
    public Difficulty getSelectedDifficulty() {
        return selectedDifficulty;
    }
}
