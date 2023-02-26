import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ScoreBoardTest {
    //ScoreBoard exampleScoreBoard;

    ScoreBoard scoreBoard;
    @BeforeEach
    void setUp () {
        scoreBoard = new ScoreBoard();
       /* exampleScoreBoard = new ScoreBoard();
        exampleScoreBoard.startNewGame("Mexico", "Canada");
        exampleScoreBoard.updateScore("Mexico", "Canada", 0, 5);
        exampleScoreBoard.startNewGame("Spain", "Brazil");
        exampleScoreBoard.updateScore("Spain", "Brazil", 10, 2);
        exampleScoreBoard.startNewGame("Germany", "France");
        exampleScoreBoard.updateScore("Germany", "France", 2, 2);
        exampleScoreBoard.startNewGame("Uruguay", "Italy");
        exampleScoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        exampleScoreBoard.startNewGame("Argentina", "Australia");
        exampleScoreBoard.updateScore("Argentina", "Australia", 3, 1);*/
    }

    @Test
    void startNewGameNullOrEmptyTests () {
        Assertions.assertThrows(RuntimeException.class,
                () -> scoreBoard.startNewGame(null, "guests"));
        Assertions.assertThrows(RuntimeException.class,
                () -> scoreBoard.startNewGame("", "guests"));
        Assertions.assertThrows(RuntimeException.class,
                () -> scoreBoard.startNewGame("home", null));
        Assertions.assertThrows(RuntimeException.class,
                () -> scoreBoard.startNewGame("home", ""));
    }

    @Test
    void startNewGameTest () {
        //the same game add error
        Assertions.assertEquals(0, scoreBoard.getGames().size());
        Game createdGame = scoreBoard.startNewGame("USA", "Canada");
        Assertions.assertEquals(0, createdGame.getHomeTeamScore());
        Assertions.assertEquals(0, createdGame.getAwayTeamScore());
        Game expectedGame = new Game("USA", "Canada");
        Assertions.assertEquals(expectedGame, createdGame);
        Assertions.assertIterableEquals(Collections.singletonList(expectedGame), scoreBoard.getGames());
        scoreBoard.startNewGame("Mexico", "France");
        Assertions.assertIterableEquals(Arrays.asList(expectedGame, new Game("Mexico", "France")), scoreBoard.getGames());
    }

    @Test
    void updateScoreTest () {
        scoreBoard.startNewGame("USA", "Canada");
        scoreBoard.updateScore("USA", "Canada", 1, 2);
        Game game = scoreBoard.
        Assertions.assertEquals();
    }

    private Game getGame(ScoreBoard scoreBoard, Game game) {
        return scoreBoard.getGames().stream().filter(g -> g.equals(game)).findFirst().orElse(null);
    }
}
