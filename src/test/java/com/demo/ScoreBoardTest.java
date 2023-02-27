package com.demo;

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
        scoreBoard.updateScoreByTeamNames("USA", "Canada", 1, 2);
        Game game = scoreBoard.getGames().get(0);
        Assertions.assertEquals(1, game.getHomeTeamScore());
        Assertions.assertEquals(2, game.getAwayTeamScore());
        scoreBoard.updateScoreByGameId(0, 5, 6);
        Assertions.assertEquals(5, game.getHomeTeamScore());
        Assertions.assertEquals(6, game.getAwayTeamScore());
    }

    @Test
    void finishGameTest() {
        scoreBoard.startNewGame("USA", "Canada");
        Game game = scoreBoard.startNewGame("Mexico", "France");
        scoreBoard.finishGame("USA", "Canada");
        Assertions.assertEquals(1, scoreBoard.getGames().size());
        Assertions.assertTrue(getGameFromScoreBoard(scoreBoard, game).isPresent());
    }

    @Test
    void getGameByBadTeamNamesAndIdsTest() {
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.getGameByTeamNames("USA", "Canada"));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.getGameByTeamNames("USA", null));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.getGameByTeamNames(null, "Canada"));
        Assertions.assertThrows(ScoreBoardException.class, () -> scoreBoard.getGameById(50));
    }

    @Test
    void summaryAsGameListTest() {
        Assertions.assertEquals(0, scoreBoard.getSummaryAsGameList().size());
        scoreBoard.startNewGame("Mexico", "Canada");
        scoreBoard.updateScoreByTeamNames("Mexico", "Canada", 0, 5);
        scoreBoard.startNewGame("Spain", "Brazil");
        scoreBoard.updateScoreByTeamNames("Spain", "Brazil", 10, 2);
        scoreBoard.startNewGame("Germany", "France");
        scoreBoard.updateScoreByTeamNames("Germany", "France", 2, 2);
        scoreBoard.startNewGame("Uruguay", "Italy");
        scoreBoard.updateScoreByTeamNames("Uruguay", "Italy", 6, 6);
        scoreBoard.startNewGame("Argentina", "Australia");
        scoreBoard.updateScoreByTeamNames("Argentina", "Australia", 3, 1);
        List<Game> expectedGames = Arrays.asList(new Game("Uruguay", "Italy", 0),
                new Game("Spain", "Brazil", 0),
                new Game("Mexico", "Canada", 0),
                new Game("Argentina", "Australia", 0),
                new Game("Germany", "France", 0)
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
