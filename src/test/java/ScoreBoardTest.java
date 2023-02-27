import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ScoreBoardTest {
    ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void startNewGameNullOrEmptyTests() {
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.startNewGame(null, "guests"));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.startNewGame("", "guests"));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.startNewGame("home", null));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.startNewGame("home", ""));
    }

    @Test
    void startNewGameTest() {
        Assertions.assertEquals(0, scoreBoard.getGames().size());
        Game createdGame = scoreBoard.startNewGame("USA", "Canada");
        Assertions.assertEquals(0, createdGame.getHomeTeamScore());
        Assertions.assertEquals(0, createdGame.getAwayTeamScore());
        Game expectedGame = new Game("USA", "Canada", 0);
        Assertions.assertEquals(expectedGame, createdGame);
        Assertions.assertIterableEquals(Collections.singletonList(expectedGame), scoreBoard.getGames());
        scoreBoard.startNewGame("Mexico", "France");
        Assertions.assertIterableEquals(Arrays.asList(expectedGame, new Game("Mexico", "France", 0)), scoreBoard.getGames());
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.startNewGame("USA", "Canada"));
    }

    @Test
    void updateScoreTest() {
        scoreBoard.startNewGame("USA", "Canada");
        scoreBoard.updateScore("USA", "Canada", 1, 2);
        Game game = scoreBoard.getGames().get(0);
        Assertions.assertEquals(1, game.getHomeTeamScore());
        Assertions.assertEquals(2, game.getAwayTeamScore());
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.updateScore("USA", "Canada", -1, 1));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.updateScore("USA", "Canada", 1, -1));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.updateScore("US", "Canada", 1, 1));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.updateScore(null, "Canada", 1, 1));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.updateScore("USA", null, 1, 1));
    }

    @Test
    void finishGameTest() {
        scoreBoard.startNewGame("USA", "Canada");
        Game game = scoreBoard.startNewGame("Mexico", "France");
        scoreBoard.finishGame("USA", "Canada");
        Assertions.assertEquals(1, scoreBoard.getGames().size());
        Assertions.assertTrue(getGameFromScoreBoard(scoreBoard, game).isPresent());
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.finishGame("USA", "Canada"));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.finishGame("USA", null));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.finishGame(null, "Canada"));
    }

    @Test
    void summaryAsGameListTest() {
        Assertions.assertEquals(0, scoreBoard.getSummaryAsGameList().size());
        scoreBoard.startNewGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        scoreBoard.startNewGame("Spain", "Brazil");
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);
        scoreBoard.startNewGame("Germany", "France");
        scoreBoard.updateScore("Germany", "France", 2, 2);
        scoreBoard.startNewGame("Uruguay", "Italy");
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoard.startNewGame("Argentina", "Australia");
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);
        List<Game> expectedGames = Arrays.asList(new Game("Uruguay", "Italy", 6, 6, 0),
                new Game("Spain", "Brazil", 10, 2, 0),
                new Game("Mexico", "Canada", 0, 5, 0),
                new Game("Argentina", "Australia", 3, 1, 0),
                new Game("Germany", "France", 2, 2, 0)
        );
        Assertions.assertIterableEquals(expectedGames, scoreBoard.getSummaryAsGameList());
        Assertions.assertIterableEquals(Arrays.asList(
                "Uruguay 6 - Italy 6",
                "Spain 10 - Brazil 2",
                "Mexico 0 - Canada 5",
                "Argentina 3 - Australia 1",
                "Germany 2 - France 2"
        ), scoreBoard.getSummaryAsStringList());
    }

    private Optional<Game> getGameFromScoreBoard(ScoreBoard scoreBoard, Game game) {
        return scoreBoard.getGames().stream().filter(g -> g.equals(game)).findFirst();
    }
}
